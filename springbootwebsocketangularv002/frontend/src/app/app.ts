import { Component, OnInit, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App implements OnInit{
  protected readonly title = signal('frontend');

  // Lifecycle hook
  ngOnInit(): void {
    console.log('App component initialized');
  }

  connect(){

  }

  sendMEssage(){

  }

  setAvatarColor(sender: string):string{

    return ""

  }
}
