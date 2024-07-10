package ru.clevertec.check.services.util;

import ru.clevertec.check.models.DebitCard;
import ru.clevertec.check.models.DiscountCard;
import ru.clevertec.check.models.Order;
import ru.clevertec.check.models.Product;
import ru.clevertec.check.services.implementations.CommandLineArgumentsParser;
import ru.clevertec.check.services.interfaces.Parser;
import ru.clevertec.check.utils.ParserFactory;
import ru.clevertec.check.utils.StringStorage;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class CheckTestUtil {

    private static Parser parser = ParserFactory.getParser(StringStorage.CONSOLE_FORMAT);
    private static CommandLineArgumentsParser commandLineArgumentsParser = (CommandLineArgumentsParser) parser;

    public static Parser getParser() {
        return parser;
    }

    public static CommandLineArgumentsParser getCommandLineArgumentsParser() {
        return commandLineArgumentsParser;
    }

    public static Map<Product, Integer> getBucket() {
        Map<Product, Integer> bucket = new HashMap<>();
        bucket.put(new Product.Builder()
                        .id(1)
                        .name("Product_1")
                        .price(10.0)
                        .quantityInStock(11)
                        .isWholesale(true)
                        .build(),
                1);
        bucket.put(new Product.Builder()
                        .id(1)
                        .name("Product_2")
                        .price(20.0)
                        .quantityInStock(4)
                        .isWholesale(true)
                        .build(),
                5);
        bucket.put(new Product.Builder()
                        .id(1)
                        .name("Product_3")
                        .price(4.5)
                        .quantityInStock(1)
                        .isWholesale(false)
                        .build(),
                1);
        return bucket;
    }

    public static DiscountCard getDiscountCard() {
        return new DiscountCard.Builder()
                .number(1221)
                .build();
    }

    public static DebitCard getDebitCard() {
        return new DebitCard(1000);
    }
    public static DebitCard getDebitCardWithMinusBalance() {
        return new DebitCard(-1000);
    }

    public static Order getOrder() {
        return new Order.Builder()
                .bucket(getBucket())
                .discountCard(getDiscountCard())
                .debitCard(getDebitCard())
                .totalPrice(100.00)
                .totalDiscount(10.0)
                .totalWithDiscount(90.0)
                .build();
    }

    public static Order getOrderWithDebitCardWithMinusBalance() {
        return new Order.Builder()
                .bucket(getBucket())
                .discountCard(getDiscountCard())
                .debitCard(getDebitCardWithMinusBalance())
                .totalPrice(100.00)
                .totalDiscount(10.0)
                .totalWithDiscount(90.0)
                .build();
    }

    public static ByteArrayOutputStream getByteArrayOutputStream() {
        return new ByteArrayOutputStream();
    }
}
