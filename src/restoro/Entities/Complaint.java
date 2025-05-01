
package restoro.Entities;

import restoro.ChainOfResponsibilty.ComplaintHandler;

public class Complaint {

    private final ComplaintHandler chain;

    public Complaint(Delivery deliveryHandler, Admin adminHandler, RestaurantAdmin restaurantAdminHandler) {
        // Set up the chain
        deliveryHandler.setNext(adminHandler);
        adminHandler.setNext(restaurantAdminHandler);

        this.chain = deliveryHandler;
    }

    public void fileComplaint(String complaintContent) {
        System.out.println("Customer has filed a complaint: " + complaintContent);
        chain.handleComplaint(complaintContent);
    }
}
