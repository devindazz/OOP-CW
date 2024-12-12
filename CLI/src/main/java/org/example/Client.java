package org.example;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Client {
    private  static final String BASE_URL = "http://localhost:8080";

    // add endpoint to retrieve individual resources
    private HttpClient client = HttpClient.newHttpClient();

    // Method to add a vendor by sending a POST request to /vendor endpoint
    public void addVendor(int releaseInterval, int ticketsPerRelease) throws Exception {
        String payload = String.format("{\"releaseInterval\":%d,\"ticketsPerRelease\":%d}", releaseInterval, ticketsPerRelease);
        sendPostRequest("/vendor", payload);
    }

    // Method to add a customer by sending a POST request to /customer endpoint
    public void addCustomer(int retrievalInterval) throws Exception {
        String payload = String.format("{\"retrievalInterval\":%d}", retrievalInterval);
        sendPostRequest("/customer", payload);
    }

      // Method to manage configuration settings by sending a POST request to /configuration endpoint
    public void ManageConfiguration(int totalTickets, int ticketReleaseRate, int customerRetrievalRate, int maxTicketCapacity) throws Exception {
        String payload = String.format("{\"totalTickets\":%d,\"ticketReleaseRate\":%d,\"customerRetrievalRate\":%d,\"maxTicketCapacity\":%d}", totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity);
        sendPostRequest("/configuration", payload);
    }

    // Method to start the system by sending a POST request to /control-panel/start endpoint
    public void startSystem() throws Exception {
        sendPostRequest("/control-panel/start", null);
    }

     // Method to stop the system by sending a POST request to /control-panel/stop endpoint
    public void stopSystem() throws Exception {
        sendPostRequest("/control-panel/stop", null);
    }

    // Private helper method to send POST requests with a given endpoint and payload
    private void sendPostRequest(String endpoint, String payload) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + endpoint))
                .header("Content-Type", "application/json")
                .POST(payload == null ? HttpRequest.BodyPublishers.noBody() : HttpRequest.BodyPublishers.ofString(payload))
                .build();

        // Send the HTTP request and get the response
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Response: " + response.body());
    }

}


