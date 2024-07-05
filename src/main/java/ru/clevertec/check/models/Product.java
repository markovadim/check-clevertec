package main.java.ru.clevertec.check.models;

public class Product {

    private int id;
    private String name;
    private double price;
    private int quantityInStock;
    private boolean isWholesale;

    public Product(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.price = builder.price;
        this.quantityInStock = builder.quantityInStock;
        this.isWholesale = builder.isWholesale;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public boolean isWholesale() {
        return isWholesale;
    }

    public static class Builder {
        private int id;
        private String name;
        private double price;
        private int quantityInStock;
        private boolean isWholesale;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder price(double price) {
            this.price = price;
            return this;
        }

        public Builder quantityInStock(int quantityInStock){
            this.quantityInStock = quantityInStock;
            return this;
        }

        public Builder isWholesale(boolean isWholesale) {
            this.isWholesale = isWholesale;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
