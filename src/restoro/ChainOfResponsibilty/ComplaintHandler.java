/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restoro.ChainOfResponsibilty;
/**
 *
 * @author salma
 */
public interface ComplaintHandler {
    void setNext(ComplaintHandler next);
    void handleComplaint(String complaint);
}

