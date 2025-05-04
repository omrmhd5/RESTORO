/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package restoro.Entities;

import org.junit.Test;
import static org.junit.Assert.*;
import restoro.Entities.PromotionsDiscounts;

/**
 *
 * @author salma
 */
public class PromotionsDiscountsTest {

    @Test
    public void testApplyValidPromoCode() {
        PromotionsDiscounts promo = new PromotionsDiscounts(1111, "20% OFF", true);
        float originalTotal = 200;
        float expectedTotal = 160;

        float actualTotal = PromotionsDiscounts.applyPromo(originalTotal, 1111);

        System.out.println("Promo Code Test Passed. Total after discount = " + actualTotal);

        assertEquals("Promo should apply 20% discount", expectedTotal, actualTotal,0.01f);
    }

    @Test
    public void testApplyInvalidPromoCode() {
        float originalTotal = 200;
        float expectedTotal = 200;

        float actualTotal = PromotionsDiscounts.applyPromo(originalTotal, 9999);

        System.out.println("Invalid promo code Passed");

        assertEquals("Invalid promo code should not apply discount", expectedTotal, actualTotal,0.01f);
    }
}