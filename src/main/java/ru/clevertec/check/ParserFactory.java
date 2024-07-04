package main.java.ru.clevertec.check;

import main.java.ru.clevertec.check.services.CSVFileParser;
import main.java.ru.clevertec.check.services.CommandLineArgumentsParser;
import main.java.ru.clevertec.check.services.Parser;


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
