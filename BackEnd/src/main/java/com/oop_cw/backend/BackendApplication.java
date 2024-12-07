package com.oop_cw.backend;

import java.util.HashMap;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@PostMapping("/vendors")
	@ResponseStatus(code = HttpStatus.CREATED)
	public String addVendor(@RequestBody HashMap<String, Object> request) {
		int releaseInterval;
		int ticketPerRelease;
		if(request.containsKey("release_interval")) {
			releaseInterval = (int) request.get("release_interval");
		}
		else {return "Please provide a value for release interval";}

		if(request.containsKey("ticket_per_release")) {
			ticketPerRelease = (int) request.get("ticket_per_release");
		}
		else {return "Please provide a value for ticket per release";}
		
		Vendor vendor = new Vendor(ticketPerRelease, releaseInterval);
		Thread vendorThread = new Thread(vendor, "vendorThread");
		System.out.println("Vendor created with release interval: " + releaseInterval);
		vendorThread.start();
		return "vendor has been created successfully ";
	}

	@PostMapping("/customers")
	@ResponseStatus(code = HttpStatus.CREATED)
	public String addCustomer(@RequestBody HashMap<String, Object> request) {
		int retrievalInterval;
		if(request.containsKey("retrieval_interval")) {
			 retrievalInterval = (int) request.get("retrieval_interval");
		}
		else {return "please provide a value for retrieval interval "; }
		
		Customer customer = new Customer(retrievalInterval);
		Thread customerThread = new Thread(customer, "customerThread");
		System.out.println("Customer created with retrieval interval: " + retrievalInterval);
		customerThread.start();
		return "Customer has been bought a ticket successfully";
	}

	@GetMapping("/tickets")
	public List<Ticket> getTicket() {
		return TicketPool.getInstance().getTickets();
	}

	@PostMapping("/configuration")
	public HashMap<String, Object> setConfiguration(@RequestBody HashMap<String, Object> body) {
		if(body.containsKey("status")) {
			Configuration.getInstance().setIsRunning((boolean) body.get("status"));
		}
		if(body.containsKey("totalTickets")){
			Configuration.getInstance().setTotalTickets((int) body.get("totalTickets")) ;
		}
		if(body.containsKey("ticketReleaseRate")){
			Configuration.getInstance().setTicketReleaseRate((int) body.get("ticketReleaseRate")) ;
		}
		if(body.containsKey("customerRetrievalRate")){
			Configuration.getInstance().setCustomerRetrievalRate((int) body.get("customerRetrievalRate")) ;
		}
		if(body.containsKey("maxTicketCapacity")){
			Configuration.getInstance().setMaxTicketCapacity((int) body.get("maxTicketCapacity")) ;
		}
		return body;

	}
}
