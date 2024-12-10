import { Component } from '@angular/core';
import { BackendService } from '../../services/backend.service';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-add-vendor',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './add-vendor.component.html',
  styleUrl: './add-vendor.component.scss'
})
export class AddVendorComponent {
  vendor = {
    releaseInterval: 0, 
    ticketPerRelease: 0,  
  };

  constructor(private backendService: BackendService, private router: Router) {}

  onSubmit() {
    const vendorData = {
      release_interval: this.vendor.releaseInterval,  
      ticket_per_release: this.vendor.ticketPerRelease, 
    };

    this.backendService.client
      .post('/vendors', vendorData)  
      .then((response) => {
        console.log('Vendor added successfully', response);  
        this.router.navigate(['/']);  
      })
      .catch((error) => {
        console.error('Error adding vendor', error);  
      });
  }
}