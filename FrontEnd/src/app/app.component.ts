import { Component, NgModule } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ConfigurationFormComponent } from './components/configuration-form/configuration-form.component';
import { TicketDisplayComponent } from './components/ticket-display/ticket-display.component';
import { ControlPanelComponent } from './components/control-panel/control-panel.component';
import { LogDisplayComponent } from './components/log-display/log-display.component';
import { AddVendorComponent } from './components/add-vendor/add-vendor.component';
import { AddCustomerComponent } from './components/add-customer/add-customer.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterOutlet,
    ConfigurationFormComponent,
    TicketDisplayComponent,
    ControlPanelComponent,
    LogDisplayComponent,
    AddVendorComponent,
    AddCustomerComponent,
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
})
export class AppComponent {
  title = 'FrontEnd';
}
