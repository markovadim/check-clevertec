package main.java.ru.clevertec.check.services;

import main.java.ru.clevertec.check.exceptions.NotEnoughMoneyException;
import main.java.ru.clevertec.check.models.Check;
import main.java.ru.clevertec.check.models.Order;

import java.time.LocalDateTime;

public class PaymentService {

    public Check pay(Order order) {
        if (checkDebitCardBalance(order)) {
            return new Check.Builder()
                    .order(order)
                    .localDateTime(LocalDateTime.now())
                    .build();
        } else {
            throw new NotEnoughMoneyException();
        }
    }

    private boolean checkDebitCardBalance(Order order) {
        if ((order.getDebitCard().getBalance() <= 0) || (order.getDebitCard().getBalance() < order.getTotalWithDiscount())) {
            return false;
        }
        return true;
    }

}
