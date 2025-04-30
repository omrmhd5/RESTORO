/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restoro.ReadOnly;

import restoro.Controllers.PromoCtrl;
import restoro.Entities.PromotionDiscounts;

/**
 *
 * @author HP
 */
public class DiscountAppUI {
     private PromoCtrl promoCtrl;

    public DiscountAppUI(PromoCtrl promoCtrl) {
        this.promoCtrl = promoCtrl;
    }

    public boolean login(String username, String password) {
        return promoCtrl.login(username, password);
    }

    public boolean hasMorePromotionsToAdd() {
        // Simulate a loop condition (this could be user input or a queue)
        return false; // or true, for testing
    }

    public String selectPromotionToModify() {
        return promoCtrl.selectPromotionToModify();
    }

    public PromotionDiscounts getPromotionDetails(String id) {
        return promoCtrl.getPromotionDetails(id);
    }

    public PromotionDiscounts modifyPromotionDetails(PromotionDiscounts promo) {
        // simulate modification
        promo.setDescription(promo.getDescription() + " [Updated]");
        return promo;
    }

    public boolean updatePromotion(PromotionDiscounts updatedPromo) {
        return promoCtrl.updatePromotion(updatedPromo);
    }
}
