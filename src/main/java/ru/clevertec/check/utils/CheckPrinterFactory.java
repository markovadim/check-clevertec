package main.java.ru.clevertec.check.utils;

import main.java.ru.clevertec.check.services.interfaces.CheckPrinter;
import main.java.ru.clevertec.check.services.implementations.ConsoleCheckPrinter;
import main.java.ru.clevertec.check.services.implementations.FileCheckPrinter;

public class CheckPrinterFactory {

    public static CheckPrinter getParser(String format) {
        switch (format) {
            case "FILE" -> {
                return new FileCheckPrinter();
            }
            case "CONSOLE" -> {
                return new ConsoleCheckPrinter();
            }
            default -> {
                return null;
            }
        }
    }
}
