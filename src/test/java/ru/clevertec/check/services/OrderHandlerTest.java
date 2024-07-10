package ru.clevertec.check.services;

import org.junit.jupiter.api.Test;
import ru.clevertec.check.models.DebitCard;
import ru.clevertec.check.models.DiscountCard;
import ru.clevertec.check.models.Product;
import ru.clevertec.check.services.util.CheckTestUtil;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OrderHandlerTest {

    private static Map<Product, Integer> bucket = CheckTestUtil.getBucket();
    private static DiscountCard discountCard = CheckTestUtil.getDiscountCard();
    private static DebitCard debitCard = CheckTestUtil.getDebitCard();


    @Test
    void createOrderShouldReturnNotNullOrder() {
        assertNotNull(new OrderHandler().createOrder(bucket, discountCard, debitCard));
    }

    @Test
    void getTotalSumShouldEquals() {
        assertEquals(114.5, new OrderHandler().getTotalSum(bucket));
    }

    @Test
    void getTotalDiscountShouldEquals() {
        assertEquals(10.0, new OrderHandler().getTotalDiscount(bucket, discountCard));
    }

    @Test
    void getDiscountByItemShouldEquals() {
        assertEquals(5.75, OrderHandler.getDiscountByItem(
                new Product.Builder()
                        .id(1)
                        .name("Product_4")
                        .price(11.5)
                        .quantityInStock(6)
                        .isWholesale(true)
                        .build(),
                5,
                discountCard
        ));
    }
}
