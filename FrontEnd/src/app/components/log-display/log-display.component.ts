import { NgFor, NgIf } from '@angular/common';
import { Component } from '@angular/core';


interface log {
  id: number;
  vendorId: number;


}

@Component({
  selector: 'app-log-display',
  standalone: true,
  imports: [NgIf, NgFor],
  templateUrl: './log-display.component.html',
  styleUrl: './log-display.component.scss',
})
export class LogDisplayComponent {
  logs: log[] = [];
}
