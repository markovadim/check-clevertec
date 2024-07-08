package main.java.ru.clevertec.check.utils;

import main.java.ru.clevertec.check.services.implementations.CSVFileParser;
import main.java.ru.clevertec.check.services.implementations.CommandLineArgumentsParser;
import main.java.ru.clevertec.check.services.interfaces.Parser;


public class ParserFactory {

    public static Parser getParser(String format) {
        switch (format) {
            case "FILE" -> {
                return new CSVFileParser();
            }
            case "CONSOLE" -> {
                return new CommandLineArgumentsParser();
            }
            default -> {
                return null;
            }
        }
    }
}
