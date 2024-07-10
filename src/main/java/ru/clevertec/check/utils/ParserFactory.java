package ru.clevertec.check.utils;

import ru.clevertec.check.services.implementations.CSVFileParser;
import ru.clevertec.check.services.implementations.CommandLineArgumentsParser;
import ru.clevertec.check.services.interfaces.Parser;


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
