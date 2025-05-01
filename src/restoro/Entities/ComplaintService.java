/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author HP
 */

import java.util.ArrayList;

public class ComplaintService {
    private HandleComplaint controller;
    private ComplaintService complaintService;
    private String loggedInUser;
    private ArrayList<Complaint> complaints;

    // Constructor
    public ComplaintService() {
        this.complaintService = this;
        this.complaints = new ArrayList<>();
        complaints.add(new Complaint("Cold food", true));
        complaints.add(new Complaint("Late delivery", false));
    }

    // Login
    public void login(String email, String password) {
        if (email.equals("admin@example.com") && password.equals("1234")) {
            loggedInUser = email;
            System.out.println("Login successful.");
        } else {
            System.out.println("Login failed.");
        }
    }

    public ArrayList<Complaint> viewComplaints() {
        if (loggedInUser != null) {
            return complaints;
        } else {
            System.out.println("Access denied. Not logged in.");
            return new ArrayList<>();
        }
    }

    public void markComplaintAsSolved(Complaint complaint) {
        if (loggedInUser != null) {
            complaint.setResolved(true);
            System.out.println("Complaint marked as Solved.");
        } else {
            System.out.println("Please login first.");
        }
    }

    public void markComplaintAsUnSolved(Complaint complaint) {
        if (loggedInUser != null) {
            complaint.setResolved(false);
            System.out.println("Complaint marked as Unsolved.");
        } else {
            System.out.println("Please login first.");
        }
    }

    // Setters and Getters
    public HandleComplaint getController() {
        return controller;
    }

    public void setController(HandleComplaint controller) {
        this.controller = controller;
    }

    public ComplaintService getComplaintService() {
        return complaintService;
    }

    public void setComplaintService(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    public String getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(String loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public ArrayList<Complaint> getComplaints() {
        return complaints;
    }

    public void setComplaints(ArrayList<Complaint> complaints) {
        this.complaints = complaints;
    }

}
