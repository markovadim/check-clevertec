package ru.clevertec.check.services.implementations;

import org.junit.jupiter.api.Test;
import ru.clevertec.check.exceptions.BadRequestException;
import ru.clevertec.check.services.interfaces.Parser;
import ru.clevertec.check.services.util.CheckTestUtil;

import static org.junit.jupiter.api.Assertions.*;


class CommandLineArgumentsParserTest {

    private static Parser parser = CheckTestUtil.getParser();
    private static CommandLineArgumentsParser commandLineArgumentsParser = CheckTestUtil.getCommandLineArgumentsParser();

    @Test
    void parseDoesNotThrows() {
        assertDoesNotThrow(() -> parser.parse(new String[]{"1-1", "2-1", "3-1", "1-4", "discountCard=1133", "balanceDebitCard=12344", "saveToFile=test-result.csv", "datasource.url=jdbc:postgresql://localhost:5432/check", "datasource.username=postgres", "datasource.password=postgres"}));
    }

    @Test
    void checkInputFormatShouldThrowException() {
        assertThrows(BadRequestException.class, () -> parser.parse(new String[]{"1-1", "balanceDebitCard=1212"}));
    }

    @Test
    void checkInputFormatShouldReturnTrue() {
        assertTrue(commandLineArgumentsParser.checkInputFormat(new String[]{"1-1", "2-1", "3-1", "1-4", "discountCard=1133", "balanceDebitCard=12344", "saveToFile=test-result.csv", "datasource.url=jdbc:postgresql://localhost:5432/check", "datasource.username=postgres", "datasource.password=postgres"}));
    }

    @Test
    void parseDataBaseAccessDataShouldThrowException() {
        assertThrows(BadRequestException.class, () -> parser.parse(new String[]{"1-1", "balanceDebitCard=1212"}));
    }
}
