import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-configuration-form',
  standalone: true,
  imports: [],
  templateUrl: './configuration-form.component.html',
  styleUrl: './configuration-form.component.scss'
})
export class ConfigurationFormComponent {
  totalTickets: number | null = null;
  ticketReleaseRate: number | null = null;
  customerRetrievalRate: number | null = null;
  maxTicketCapacity: number | null = null;

  onSubmit() {
    console.log('Form Submitted:',{
      totalTickets: this.totalTickets,
      ticketReleaseRate: this.ticketReleaseRate,
      customerRetrievalRate: this.customerRetrievalRate,
      maxTicketCapacity: this.maxTicketCapacity
    });
  }
}
