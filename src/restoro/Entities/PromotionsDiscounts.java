/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restoro.Entities;
/**
 *
 * @author HP
 */
import java.util.ArrayList;

public class PromotionsDiscounts {
   private static final ArrayList<PromotionsDiscounts> allPromotions = new ArrayList<>();

    private int code;
    private String description;
    private boolean active;

    public PromotionsDiscounts(int code, String description, boolean active) {
        this.code = code;
        this.description = description;
        this.active = active;
        allPromotions.add(this);
    }

    public static boolean isPromoCodeValid(int enteredCode) {
        for (PromotionsDiscounts promo : allPromotions) {
            if (promo.code == enteredCode && promo.active) {
                return true;
            }
        }
        return false;
    }

    public static boolean deletePromo(int code) {
        for (PromotionsDiscounts promo : allPromotions) {
            if (promo.code == code) {
                allPromotions.remove(promo);
                return true;
            }
        }
        return false;
    }

    public static boolean addPromo(int code) {
        for (PromotionsDiscounts promo : allPromotions) {
            if (promo.code == code) return false; // Already exists
        }
        allPromotions.add(new PromotionsDiscounts(code, "New Promo", true));
        return true;
    }

    public String getDescription() {
        return description;
    }

    public boolean isActive() {
        return active;
    }
}
