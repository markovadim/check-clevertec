package main.java.ru.clevertec.check;

import main.java.ru.clevertec.check.services.CheckPrinter;

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
