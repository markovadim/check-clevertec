package main.java.ru.clevertec.check.repositories;

import main.java.ru.clevertec.check.models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepository {

    public static final List<Product> products = new ArrayList<>();

    public static Optional<Product> getById(int id) {
        return products.stream()
                .filter(p -> p.getId() == id)
                .findFirst();
    }
}
