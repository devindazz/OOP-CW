import { Component } from '@angular/core';
import { BackendService } from '../../services/backend.service';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-add-customer',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './add-customer.component.html',
  styleUrl: './add-customer.component.scss'
})
export class AddCustomerComponent {
  customer = {
    retrievalInterval:0,
  };

  constructor(private backendService: BackendService, private router:Router) {}

  onSubmit() {
    const customerData = {
      retrieval_interval:this.customer.retrievalInterval,
    };

    console.log(customerData)
    this.backendService.client
      .post('/customers' , customerData)
      .then((Response) => {
        console.log('Customer added successfully' , Response);
        this.router.navigate(['/']);
      })

      .catch((error) => {
        console.error('Error adding customer', error);
      })
  }
}
