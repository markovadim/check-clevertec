package main.java.ru.clevertec.check.services.implementations;

import main.java.ru.clevertec.check.models.Check;
import main.java.ru.clevertec.check.services.OrderHandler;
import main.java.ru.clevertec.check.services.interfaces.CheckPrinter;
import main.java.ru.clevertec.check.utils.StringStorage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class FileCheckPrinter implements CheckPrinter {

    @Override
    public void print(Check check) {
        File file = new File(StringStorage.RESULT_FILE_NAME);

        try (FileWriter writer = new FileWriter(file)) {
            writer.append(check.getExceptionMessage() == null ? stringBuilderFormation(check) : String.format("%s\n%s", StringStorage.ERROR, check.getExceptionMessage()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public StringBuilder stringBuilderFormation(Check check) {
        StringBuilder builder = new StringBuilder();
        builder.append("Date;Time\n");
        builder.append(check.getLocalDateTime().format(DateTimeFormatter.ofPattern(StringStorage.DATE_PATTERN)))
                .append(";")
                .append(check.getLocalDateTime().format(DateTimeFormatter.ofPattern(StringStorage.TIME_PATTERN)))
                .append("\n\n");

        builder.append(StringStorage.CHECK_PRODUCT_HEAD)
                .append("\n");

        check.getOrder().getBucket().forEach((k, v) -> {
            builder.append(v)
                    .append(";")
                    .append(k.getName())
                    .append(";")
                    .append(String.format("%.2f", k.getPrice()))
                    .append("$;")
                    .append(String.format("%.2f", OrderHandler.getDiscountByItem(k, v, check.getOrder().getDiscountCard())))
                    .append("$;")
                    .append(String.format("%.2f", k.getPrice() * v))
                    .append("$\n");
        });
        if (check.getOrder().getDiscountCard() != null) {
            builder.append("\n")
                    .append(StringStorage.CHECK_DISCOUNT_CARD_HEAD)
                    .append("\n")
                    .append(check.getOrder().getDiscountCard().getNumber())
                    .append(";")
                    .append(check.getOrder().getDiscountCard().getDiscountPercentage())
                    .append("%\n");
        }
        builder.append("\n")
                .append(StringStorage.CHECK_TOTALS_HEAD)
                .append("\n")
                .append(String.format("%.2f", check.getOrder().getTotalPrice()))
                .append("$;")
                .append(String.format("%.2f", check.getOrder().getTotalDiscount()))
                .append("$;")
                .append(String.format("%.2f", check.getOrder().getTotalWithDiscount()))
                .append("$");
        return builder;
    }
}
