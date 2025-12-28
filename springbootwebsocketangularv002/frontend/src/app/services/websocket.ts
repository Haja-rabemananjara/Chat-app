import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';


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
  
}
