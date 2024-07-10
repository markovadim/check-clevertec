package ru.clevertec.check.repositories;

import ru.clevertec.check.models.Product;

import java.util.Optional;

public interface ProductRepository {

    Optional<Product> findById(int id);
}
