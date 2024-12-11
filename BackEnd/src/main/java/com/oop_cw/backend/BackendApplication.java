package com.oop_cw.backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;


@SpringBootApplication
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class BackendApplication {

	private final Map<Integer, Vendor> vendors = new HashMap<>();
	private final Map<Integer, Customer> customers = new HashMap<>();

    public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);

		Logger log = LoggerFactory.getLogger(BackendApplication.class);
		log.info("Starting Backend Application");
	}

	
	@GetMapping("/logs")
    @ResponseStatus(code = HttpStatus.OK)
    public List<String> getLogs() {
        return LogManager.getInstance().getLogs();
    }
	
	@GetMapping("/vendors")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Vendor> getVendors() {
		return new ArrayList<>(vendors.values());
	}

	@PostMapping("/vendors")
	@ResponseStatus(code = HttpStatus.CREATED)
	public String addVendor(@RequestBody HashMap<String, Object> request) {
		int releaseInterval;
		int ticketPerRelease;
		if (request.containsKey("release_interval")) {
			releaseInterval = (int) request.get("release_interval");
		} else {
			return "Please provide a value for release interval";
		}

		if (request.containsKey("ticket_per_release")) {
			ticketPerRelease = (int) request.get("ticket_per_release");
		} else {
			return "Please provide a value for ticket per release";
		}

		Vendor vendor = new Vendor(ticketPerRelease, releaseInterval);

		vendors.put(vendor.getId(), vendor);

		Thread vendorThread = new Thread(vendor, "vendorThread");
		System.out.println("Vendor created with release interval: " + releaseInterval);
		vendorThread.start();
		return "vendor has been created successfully ";
	}

	@GetMapping("/customers")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Customer> getCustomers() {
		return new ArrayList<>(customers.values());
	}
	
	@PostMapping("/customers")
	@ResponseStatus(code = HttpStatus.CREATED)
	public String addCustomer(@RequestBody HashMap<String, Object> request) {
		int retrievalInterval;
		if (request.containsKey("retrieval_interval")) {
			retrievalInterval = (int) request.get("retrieval_interval");
		} else {
			return "please provide a value for retrieval interval ";
		}

		Customer customer = new Customer(retrievalInterval);

		customers.put(customer.getId(), customer);
		Thread customerThread = new Thread(customer, "customerThread");
		System.out.println("Customer created with retrieval interval: " + retrievalInterval);
		customerThread.start();
		return "Customer has been bought a ticket successfully";
	}

	@GetMapping("/tickets")
	public List<Ticket> getTicket() {
		return TicketPool.getInstance().getTickets();
	}

	@GetMapping("/configuration")
	@ResponseStatus(code = HttpStatus.OK)
	public Configuration configuration() {
		return Configuration.getInstance();
	}


	@PostMapping("/configuration")
	public HashMap<String, Object> setConfiguration(@RequestBody HashMap<String, Object> body) {
		if (body.containsKey("status")) {
			Configuration.getInstance().setIsRunning((boolean) body.get("status"));
		}
		if (body.containsKey("totalTickets")) {
			Configuration.getInstance().setTotalTickets((int) body.get("totalTickets"));
		}
		if (body.containsKey("ticketReleaseRate")) {
			Configuration.getInstance().setTicketReleaseRate((int) body.get("ticketReleaseRate"));
		}
		if (body.containsKey("customerRetrievalRate")) {
			Configuration.getInstance().setCustomerRetrievalRate((int) body.get("customerRetrievalRate"));
		}
		if (body.containsKey("maxTicketCapacity")) {
			Configuration.getInstance().setMaxTicketCapacity((int) body.get("maxTicketCapacity"));
		}
		return body;
	}
}
