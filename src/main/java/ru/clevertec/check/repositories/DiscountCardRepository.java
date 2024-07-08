package main.java.ru.clevertec.check.repositories;

import main.java.ru.clevertec.check.models.DiscountCard;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DiscountCardRepository {

    public static final List<DiscountCard> cards = new ArrayList<>();

    public static Optional<DiscountCard> getByNumber(int number) {
        return cards.stream()
                .filter(c -> c.getNumber() == number)
                .findFirst();
    }
}
