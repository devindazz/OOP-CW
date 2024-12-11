import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { BackendService } from '../../services/backend.service';
import { FormsModule } from '@angular/forms';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-configuration-form',
  standalone: true,
  imports: [RouterLink, NgFor, FormsModule],
  templateUrl: './configuration-form.component.html',
  styleUrl: './configuration-form.component.scss'
})

export class ConfigurationFormComponent {
  configuration = {
    totalTickets: 0,
    ticketReleaseRate: 0,
    customerRetrievalRate: 0,
    maxTicketCapacity: 0
  };

  constructor(private backendService: BackendService, private router: Router) {}

  onSubmit() {
    this.backendService.client
      .post('/configuration', this.configuration)
      .then((response) => {
        console.log('Configuration updated successfully', response);
        alert('Configuration updated successfully!');
        this.router.navigate(['/']);
      })
      .catch((error) => {
        console.error('Error updating configuration', error);
        alert('Failed to update configuration. Please try again.');
      });
  }

  onCancel() {
    this.configuration.totalTickets = 0;
    this.configuration.ticketReleaseRate = 0;
    this.configuration.customerRetrievalRate = 0;
    this.configuration.maxTicketCapacity = 0;
  }
}


