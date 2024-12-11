import { Component } from '@angular/core';
import { BackendService } from '../../services/backend.service';

@Component({
  selector: 'app-control-panel',
  standalone: true,
  imports: [],
  templateUrl: './control-panel.component.html',
  styleUrl: './control-panel.component.scss'
})
export class ControlPanelComponent {
  statusMessage: string = '';

  constructor(private backendService: BackendService) {}

  onStart() {
    this.backendService.client
    .post('/configuration', {status: true})
    .then(() => {
      this.statusMessage = 'System started successfully !';
    })
    .catch((error) => {
      console.error('Error starting system:', error);
      this.statusMessage = 'Failed to start the system';
    });
  }

  onStop() {
    this.backendService.client
      .post('/configuration', { status: false })
      .then(() => {
        this.statusMessage = 'System stopped successfully!';
      })
      .catch((error) => {
        console.error('Error stopping system:', error);
        this.statusMessage = 'Failed to stop the system.';
      });
  }

}
