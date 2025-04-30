/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restoro;
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

    public Promotion getPromotionDetails(String id) {
        return promoCtrl.getPromotionDetails(id);
    }

    public Promotion modifyPromotionDetails(Promotion promo) {
        // simulate modification
        promo.setDescription(promo.getDescription() + " [Updated]");
        return promo;
    }

    public boolean updatePromotion(Promotion updatedPromo) {
        return promoCtrl.updatePromotion(updatedPromo);
    }
}
