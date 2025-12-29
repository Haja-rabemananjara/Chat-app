import { Component, OnInit} from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Websocket } from './services/websocket';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

interface Message {
  type: 'CHAT' | 'JOIN' | 'LEAVE';
  sender: string;
  content: string;
  timestamp?: string;
  id?: string;
}

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet,CommonModule, FormsModule],
  templateUrl: './app.html',
  styleUrl: './app.css'
})

export class App implements OnInit{

  title = 'frontend';
  username: string = ''; // User's name
  message: string = ''; // Current message input
  messages: Message[] = []; // Array to hold chat messages
  isConnected: boolean = false; // Connection status
  connectingMessage: string = 'Connecting to WebSocket...'; // Message shown while connecting

  constructor( private websocketService: Websocket) {
    console.log('App component constructed');
  }

  // Lifecycle hook
  ngOnInit(): void {
    console.log('App component initialized');

    // Subscribe to incoming messages observable from Websocket service
    this.websocketService.messages$.subscribe((message: Message) => {
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

  sendMessage(){
    if (this.message) {
      this.websocketService.sendMessage(this.username, this.message); // Send message via Websocket service
      this.message = ''; // Clear input field after sending
    }

  }

  getAvatarColor(sender: string):string{

    // Array of colors to choose from
    const colors = [
      '#2196F3', '#32c787', '#00BCD4', '#ff5652',
      '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
    ];
    let hash = 0;
    for (let i = 0; i < sender.length; i++) {
      // Compute hash value based on sender's name. Hash : a function that converts input data into a fixed-size string of characters, which is typically a sequence of numbers and letters.
      hash = 31 * hash + sender.charCodeAt(i);
    }

    // Return color based on hash value
    return colors[Math.abs(hash % colors.length)];

  }
}
