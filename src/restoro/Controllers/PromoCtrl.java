/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restoro.Controllers;

import restoro.Entities.PromotionsDiscounts;
import restoro.Entities.PromotionDiscounts;

/**
 *
 * @author HP
 */
public class PromoCtrl {
    private PromotionsDiscounts model;

    public PromoCtrl(PromotionsDiscounts model) {
        this.model = model;
    }

    public boolean login(String username, String password) {
        return model.authenticate(username, password);
    }

    public String selectPromotionToModify() {
        return model.fetchPromotionId();
    }

    public PromotionDiscounts getPromotionDetails(String id) {
        return model.getPromotion(id);
    }

    public boolean updatePromotion(PromotionDiscounts promo) {
        return model.updatePromotion(promo);
    }
}
