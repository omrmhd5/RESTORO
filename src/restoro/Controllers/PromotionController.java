/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restoro.Controllers;

import restoro.Entities.PromotionsDiscounts;

/**
 *
 * @author HP
 */
public class PromotionController {
    private PromotionsDiscounts model;

    public PromotionController(PromotionsDiscounts model) {
        this.model = model;
    }

    public boolean login(String username, String password) {
        return model.authenticate(username, password);
    }

    public String selectPromotionToModify() {
        return model.fetchPromotionId();
    }

    public PromotionsDiscounts getPromotionDetails(String id) {
        return model.getPromotion(id);
    }

    public boolean updatePromotion(PromotionsDiscounts promo) {
        return model.updatePromotion(promo);
    }
}
