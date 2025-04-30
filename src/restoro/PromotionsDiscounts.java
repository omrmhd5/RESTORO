/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restoro;
/**
 *
 * @author HP
 */
import java.util.HashMap;
import java.util.Map;

public class PromotionsDiscounts {
     private Map<String, Promotion> database = new HashMap<>();

    public boolean authenticate(String username, String password) {
        return "admin".equals(username) && "password".equals(password);
    }

    public String fetchPromotionId() {
        // simulate one ID (could be replaced with real user selection)
        return "PROMO123";
    }

    public Promotion getPromotion(String id) {
        return database.getOrDefault(id, new Promotion(id, "10% OFF", true));
    }

    public boolean updatePromotion(Promotion promo) {
        if (promo != null) {
            database.put(promo.getId(), promo);
            return true;
        }
        return false;
    }
}
