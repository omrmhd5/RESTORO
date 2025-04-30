/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restoro.Entities;

import restoro.ChainOfResponsibilty.ComplaintHandler;

/**
 *
 * @author salma
 */
public class Delivery implements ComplaintHandler {
    private ComplaintHandler next;

    @Override
    public void setNext(ComplaintHandler next) {
        this.next = next;
    }

    @Override
    public void handleComplaint(String complaint) {
        if (complaint.contains("delivery")) {
            System.out.println("Delivery department handling complaint: " + complaint);
        } else if (next != null) {
            next.handleComplaint(complaint);
        } else {
            System.out.println("Complaint not handled: " + complaint);
        }
    }
}
