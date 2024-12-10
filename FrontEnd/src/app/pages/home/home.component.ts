import { Component } from '@angular/core';
import { AddVendorComponent } from '../../components/add-vendor/add-vendor.component';
import { AddCustomerComponent } from '../../components/add-customer/add-customer.component';
import { ConfigurationFormComponent } from '../../components/configuration-form/configuration-form.component';
import { ControlPanelComponent } from '../../components/control-panel/control-panel.component';
import { LogDisplayComponent } from '../../components/log-display/log-display.component';
import { TicketDisplayComponent } from '../../components/ticket-display/ticket-display.component';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [AddVendorComponent, AddCustomerComponent, ConfigurationFormComponent, ControlPanelComponent, LogDisplayComponent, TicketDisplayComponent, RouterLink],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {

}
