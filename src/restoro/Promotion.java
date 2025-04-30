/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restoro;
/**
 *
 * @author HP
 */
public class Promotion {
    private String id;
    private String description;
    private boolean active;

    public Promotion(String id, String description, boolean active) {
        this.id = id;
        this.description = description;
        this.active = active;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }
}
