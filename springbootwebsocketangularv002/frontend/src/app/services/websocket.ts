import { Injectable } from '@angular/core';
import { Client, Message } from '@stomp/stompjs';
import { BehaviorSubject } from 'rxjs';
import SockJS from 'sockjs-client';


@Injectable({
  providedIn: 'root',
})
export class Websocket {

  stompClient: Client | null = null; // Declare stompClient property

  // Message tracking
  private messageSubject = new BehaviorSubject<any>(null);
  public messages$ = this.messageSubject.asObservable(); // Observable for messages

  // Connection status tracking
  private connectionSubject = new BehaviorSubject<boolean>(false);
  public connectionStatus$ = this.connectionSubject.asObservable(); // Observable for connection status
  

  connect(username: string){
    
    const socket = new SockJS('http://localhost:8080/ws'); // Adjust the URL as needed

    // Initialize STOMP client
    this.stompClient = new Client({
      webSocketFactory: () => socket, // Use SockJS
      reconnectDelay: 5000, // Auto-reconnect delay 5 seconds
      debug: (str) => { console.log(str); } // Log STOMP debug messages
    });

    // On successful connection
    this.stompClient.onConnect = (frame) => {
      console.log('Connected to WebSocket server');
      this.connectionSubject.next(true); // Update connection status

      // Subscribe to the topic for receiving messages
      this.stompClient?.subscribe('/topic/public', (message: Message) => {
        this.messageSubject.next(JSON.parse(message.body)); // Update messages
      });

      // Notify server of new user
      this.stompClient?.publish({ // Send JOIN message to server
        destination: '/app/chat.addUser', // Adjust the destination as needed
        body: JSON.stringify({ sender: username, type: 'JOIN' }), // Send username and join event type
      });
    };

    // Handle connection errors
    this.stompClient.onStompError = (frame) => {
      console.error('STOMP error:', frame.headers['message']); // Log error message
      console.error('Details:', frame.body); // Log error details
    };
    
    // Activate the STOMP client to establish the connection
    this.stompClient?.activate();
  }

  sendMessage(message: string, username: string){

  }

  disconnect(){
    if (this.stompClient) {
      this.stompClient.deactivate();
    }
  }
}
