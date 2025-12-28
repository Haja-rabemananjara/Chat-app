import { Message } from '@stomp/stompjs';
import { Component, OnInit, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Websocket } from './services/websocket';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App implements OnInit{

  title = 'frontend';
  username: string = ''; // User's name
  message: string = ''; // Current message input
  messages: any[] = []; // Array to hold chat messages
  isConnected: boolean = false; // Connection status
  connectingMessage: string = 'Connecting to WebSocket...'; // Message shown while connecting

  constructor( private websocketService: Websocket) {
    console.log('App component constructed');
  }

  // Lifecycle hook
  ngOnInit(): void {
    console.log('App component initialized');

    // Subscribe to incoming messages observable from Websocket service
    this.websocketService.messages$.subscribe(message => {
      if (message) {
        // Log the received message and update local state as needed
        console.log(`New message received from ${message.sender}: ${message.content}`);
        this.messages.push(message);
      }
    });

    // Subscribe to connection status observable from Websocket service
    this.websocketService.connectionStatus$.subscribe(connected => {
      this.isConnected = connected; // Update local connection status
      if (connected) {
        this.connectingMessage = ''; // Clear connecting message on successful connection
        console.log('WebSocket connection established');
      }
    });
  }

  connect(){

    console.log(`Attempting to connect to WebSocket at http://localhost:8080/ws as user: ${this.username}`);
    this.websocketService.connect(this.username); // Call connect method in Websocket service
  }

  sendMEssage(){
    if (this.message) {
      this.websocketService.sendMessage(this.username, this.message); // Send message via Websocket service
      this.message = ''; // Clear input field after sending
    }

  }

  setAvatarColor(sender: string):string{

    return ""

  }
}
