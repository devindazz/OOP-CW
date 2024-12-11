import { Component, OnDestroy, OnInit } from '@angular/core';
import { BackendService } from '../../services/backend.service';
import { NgFor, NgIf } from '@angular/common';

@Component({
  selector: 'app-ticket-display',
  standalone: true,
  imports: [NgFor,NgIf],
  templateUrl: './ticket-display.component.html',
  styleUrl: './ticket-display.component.scss'
})
export class TicketDisplayComponent implements OnInit, OnDestroy {
  tickets: any[] = [];

  pollInterval: any = null;

  constructor(private backendService: BackendService) {}

  ngOnInit(): void {
    this.fetchTickets();

    this.pollInterval = setInterval(() => this.fetchTickets(), 100);
  }

  ngOnDestroy(): void {
    clearInterval(this.pollInterval);
  }

  async fetchTickets() {
    try {
      const response = await this.backendService.client.get("/tickets");
      this.tickets = response.data;
    } catch (error) {
      console.error("Failed to fecth tickets", error)
      this.tickets = [];
    }
  }

  getTickets() {
    return JSON.stringify(this.tickets, null, 2);
  }
}
