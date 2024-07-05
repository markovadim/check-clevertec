package main.java.ru.clevertec.check.services;

import main.java.ru.clevertec.check.models.DebitCard;
import main.java.ru.clevertec.check.models.DiscountCard;
import main.java.ru.clevertec.check.models.Order;
import main.java.ru.clevertec.check.models.Product;

import java.math.BigDecimal;
import java.util.Map;

public class OrderHandler {

    public Order createOrder(Map<Product, Integer> basket, DiscountCard discountCard, DebitCard debitCard) {
        double totalPrice = getTotalSum(basket);
        double totalDiscount = getTotalDiscount(basket, discountCard);
        double totalWithDiscount = totalPrice - totalDiscount;

        return new Order.Builder()
                .bucket(basket)
                .discountCard(discountCard)
                .debitCard(debitCard)
                .totalPrice(totalPrice)
                .totalDiscount(totalDiscount)
                .totalWithDiscount(totalWithDiscount)
                .build();
    }

    public double getTotalSum(Map<Product, Integer> basket) {
        return basket.entrySet()
                .stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    public double getTotalDiscount(Map<Product, Integer> basket, DiscountCard discountCard) {
        double totalWholesaleProductsDiscount = basket.entrySet()
                .stream()
                .filter(entry -> (entry.getKey().isWholesale() && entry.getValue() >= 5))
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue() * 0.1)
                .sum();

        double totalDiscountByCard = 0.0;
        if (discountCard != null) {
            totalDiscountByCard = basket.entrySet()
                    .stream()
                    .filter(entry -> !(entry.getKey().isWholesale() && entry.getValue() >= 5))
                    .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue() * discountCard.getDiscountPercentage() / 100.0)
                    .sum();
        }

        BigDecimal bd = new BigDecimal(totalWholesaleProductsDiscount + totalDiscountByCard);
        bd = bd.setScale(2, BigDecimal.ROUND_HALF_EVEN);
        return bd.doubleValue();
    }

    public static double getDiscountByItem(Product product, int amount, DiscountCard card) {
        if ((amount >= 5) && (product.isWholesale())) {
            return product.getPrice() * amount * 0.1;
        } else {
            return card == null ? product.getPrice() * amount * 0 / 100.0 : product.getPrice() * amount * card.getDiscountPercentage() / 100.0;
        }
    }
}
