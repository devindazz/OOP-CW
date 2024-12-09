import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ConfigurationFormComponent } from './components/configuration-form/configuration-form.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, ConfigurationFormComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'FrontEnd';
}
