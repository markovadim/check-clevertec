package ru.clevertec.check.exceptions;

public class NotEnoughMoneyException extends RuntimeException {

    public NotEnoughMoneyException() {
        super("NOT ENOUGH MONEY");
    }
}
