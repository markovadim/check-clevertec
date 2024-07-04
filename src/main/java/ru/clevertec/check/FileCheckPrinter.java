package main.java.ru.clevertec.check;

import main.java.ru.clevertec.check.models.Check;
import main.java.ru.clevertec.check.services.CheckPrinter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class FileCheckPrinter implements CheckPrinter {

    @Override
    public void print(Check check) {

        File file = new File("result.csv");

        try (FileWriter writer = new FileWriter(file)) {

            writer.append(stringBuilderFormation(check));

            System.out.println("CSV file created successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public StringBuilder stringBuilderFormation(Check check) {
        StringBuilder builder = new StringBuilder();
        builder.append("Date;Time\n");
        builder.append(check.getLocalDateTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))).append(";").append(check.getLocalDateTime().format(DateTimeFormatter.ofPattern("HH:mm:ss"))).append("\n");

        return builder;
    }
}
