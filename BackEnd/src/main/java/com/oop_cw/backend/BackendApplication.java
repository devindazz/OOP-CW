package com.oop_cw.backend;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		//SpringApplication.run(BackendApplication.class, args);

		Customer customer = new Customer(2000);
		Vendor vendor = new Vendor(1000);
		Vendor vendor1 = new Vendor(1000);


		Thread vendoThread = new Thread(vendor,"vendorThread");
		Thread vendoThread1 = new Thread(vendor1,"vendorThread");
		Thread customerThread = new Thread(customer,"customerThread");


		vendoThread.start();
		vendoThread1.start();
		customerThread.start();
		
	}

}
