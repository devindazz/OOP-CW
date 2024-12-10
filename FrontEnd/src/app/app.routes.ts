import { Routes } from '@angular/router';
import { AddVendorComponent } from './components/add-vendor/add-vendor.component';
import { AppComponent } from './app.component';
import { HomeComponent } from './pages/home/home.component';
import { AddCustomerComponent } from './components/add-customer/add-customer.component';
import { ConfigurationFormComponent } from './components/configuration-form/configuration-form.component';
import { ControlPanelComponent } from './components/control-panel/control-panel.component';
import { LogDisplayComponent } from './components/log-display/log-display.component';
import { TicketDisplayComponent } from './components/ticket-display/ticket-display.component';

export const routes: Routes = [
    {
        path: "",
        component: HomeComponent
    },
    {
        path: "add-vendor",
        component: AddVendorComponent
    },
    {
        path: "add-customer",
        component: AddCustomerComponent
    },
    {
        path: "configuration",
        component: ConfigurationFormComponent
    },
    {
        path: "control-panel",
        component: ControlPanelComponent
    },
    {
        path: "logs",
        component: LogDisplayComponent
    },
    {
        path: "tickets",
        component: TicketDisplayComponent
    }


];
