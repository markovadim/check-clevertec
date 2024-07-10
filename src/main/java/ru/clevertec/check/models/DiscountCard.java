package ru.clevertec.check.models;

public class DiscountCard {

    private int number;
    private int discountPercentage;

    public DiscountCard(Builder builder) {
        this.number = builder.number;
        this.discountPercentage = builder.discountPercentage;
    }

    public int getNumber() {
        return number;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public static class Builder {
        private int number;
        private int discountPercentage;


        public Builder number(int number) {
            this.number = number;
            return this;
        }

        public Builder discountPercentage(int discountPercentage) {
            this.discountPercentage = discountPercentage;
            return this;
        }

        public DiscountCard build() {
            return new DiscountCard(this);
        }
    }
}
