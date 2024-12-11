import { NgFor, NgIf } from '@angular/common';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { BackendService } from '../../services/backend.service';

@Component({
  selector: 'app-log-display',
  standalone: true,
  imports: [NgIf, NgFor],
  templateUrl: './log-display.component.html',
  styleUrl: './log-display.component.scss',
})
export class LogDisplayComponent implements OnInit, OnDestroy {
  logs: String[] = [];
  pollInterval: any = null;

  constructor(private backendService: BackendService) {}

  ngOnInit(): void {
      this.fetchLogs();

      this.pollInterval = setInterval(() => this.fetchLogs(), 100);
  }

  ngOnDestroy(): void {
      clearInterval(this.pollInterval);
  }

  async fetchLogs(): Promise<void> {
    try {
      const response = await this.backendService.client.get<string[]>('/logs');
      this.logs = response.data;
    } catch (error) {
      console.error('Failed to fetch logs', error);
      this.logs = []; 
    }
  }

}
