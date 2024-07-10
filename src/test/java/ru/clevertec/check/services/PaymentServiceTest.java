package ru.clevertec.check.services;

import org.junit.jupiter.api.Test;
import ru.clevertec.check.exceptions.NotEnoughMoneyException;
import ru.clevertec.check.services.util.CheckTestUtil;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PaymentServiceTest {

    @Test
    void payShouldReturnNotNullCheck() {
        assertNotNull(new PaymentService().pay(CheckTestUtil.getOrder()));
    }

    @Test
    void payShouldThrow() {
        assertThrows(NotEnoughMoneyException.class, () -> new PaymentService().pay(CheckTestUtil.getOrderWithDebitCardWithMinusBalance()));
    }
}
