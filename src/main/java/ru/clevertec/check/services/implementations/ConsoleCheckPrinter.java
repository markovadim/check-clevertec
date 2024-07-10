package ru.clevertec.check.services.implementations;

import ru.clevertec.check.models.Check;
import ru.clevertec.check.services.OrderHandler;
import ru.clevertec.check.services.interfaces.CheckPrinter;

import java.time.format.DateTimeFormatter;

public class ConsoleCheckPrinter implements CheckPrinter {

    @Override
    public void print(Check check) {
        System.out.println(getStringBuilderByCheck(check).toString());
    }

    public StringBuilder getStringBuilderByCheck(Check check) {
        StringBuilder builder = new StringBuilder();
        builder.append("=================================Check====================================")
                .append("\nDateTime: ")
                .append(check.getLocalDateTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")))
                .append("\n__________________________________________________________________________")
                .append(String.format("%n%-7s%18s%18s%18s%13s%n%n", "QTY", "DESCRIPTION", "PRICE", "DISCOUNT", "TOTAL"));

        check.getOrder().getBucket().forEach((k, v) -> {
            builder.append(String.format("%-10d%5s%18.2f%18.2f%18.2f%n",
                    v, k.getName(), k.getPrice(), OrderHandler.getDiscountByItem(k, v, check.getOrder().getDiscountCard()), k.getPrice() * v)
            );
        });
        builder.append("\n__________________________________________________________________________");
        if (check.getOrder().getDiscountCard() != null) {
            builder.append("\nDISCOUNT CARD: ")
                    .append(check.getOrder().getDiscountCard().getNumber())
                    .append("\nDISCOUNT PERCENTAGE: ")
                    .append(check.getOrder().getDiscountCard().getDiscountPercentage())
                    .append("%");
        }
        builder.append("\n__________________________________________________________________________");
        builder.append("\nTOTAL PRICE_________________________________________________________")
                .append(check.getOrder().getTotalPrice())
                .append("$")
                .append("\nTOTAL DISCOUNT______________________________________________________")
                .append(check.getOrder().getTotalDiscount())
                .append("$")
                .append("\nTOTAL WITH DISCOUNT_________________________________________________")
                .append(check.getOrder().getTotalWithDiscount())
                .append("$")
                .append("\n==========================================================================");

        return builder;
    }
}
