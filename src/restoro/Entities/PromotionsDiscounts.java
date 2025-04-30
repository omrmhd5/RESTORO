/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restoro.Entities;
/**
 *
 * @author HP
 */
import restoro.Entities.PromotionDiscounts;
import java.util.HashMap;
import java.util.Map;

public class PromotionsDiscounts {
     private Map<String, PromotionDiscounts> database = new HashMap<>();

     
         private String id;
    private String description;
    private boolean active;

    public PromotionsDiscounts(String id, String description, boolean active) {
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
    public boolean authenticate(String username, String password) {
        return "admin".equals(username) && "password".equals(password);
    }

    public String fetchPromotionId() {
        // simulate one ID (could be replaced with real user selection)
        return "PROMO123";
    }

    public PromotionDiscounts getPromotion(String id) {
        return database.getOrDefault(id, new PromotionDiscounts(id, "10% OFF", true));
    }

    public boolean updatePromotion(PromotionDiscounts promo) {
        if (promo != null) {
            database.put(promo.getId(), promo);
            return true;
        }
        return false;
    }
}
