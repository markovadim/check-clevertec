package main.java.ru.clevertec.check.models;

import java.util.Map;

public class Order {

    private Map<Product, Integer> bucket;
    private DiscountCard discountCard;
    private DebitCard debitCard;

    private double totalPrice;
    private double totalDiscount;
    private double totalWithDiscount;

    public Order(Builder builder) {
        this.bucket = builder.bucket;
        this.discountCard = builder.discountCard;
        this.debitCard = builder.debitCard;
        this.totalPrice = builder.totalPrice;
        this.totalDiscount = builder.totalDiscount;
        this.totalWithDiscount = builder.totalWithDiscount;
    }

    public Map<Product, Integer> getBucket() {
        return bucket;
    }

    public DiscountCard getDiscountCard() {
        return discountCard;
    }

    public DebitCard getDebitCard() {
        return debitCard;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getTotalDiscount() {
        return totalDiscount;
    }

    public double getTotalWithDiscount() {
        return totalWithDiscount;
    }

    public static class Builder {
        private Map<Product, Integer> bucket;
        private DiscountCard discountCard;
        private DebitCard debitCard;
        private double totalPrice;
        private double totalDiscount;
        private double totalWithDiscount;

        public Builder bucket(Map<Product, Integer> bucket) {
            this.bucket = bucket;
            return this;
        }

        public Builder discountCard(DiscountCard discountCard) {
            this.discountCard = discountCard;
            return this;
        }

        public Builder debitCard(DebitCard debitCard) {
            this.debitCard = debitCard;
            return this;
        }

        public Builder totalPrice(double totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public Builder totalDiscount(double totalDiscount) {
            this.totalDiscount = totalDiscount;
            return this;
        }

        public Builder totalWithDiscount(double totalWithDiscount) {
            this.totalWithDiscount = totalWithDiscount;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
