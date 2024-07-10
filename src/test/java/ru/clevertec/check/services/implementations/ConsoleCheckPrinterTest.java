package ru.clevertec.check.services.implementations;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.clevertec.check.models.Check;
import ru.clevertec.check.services.util.CheckTestUtil;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleCheckPrinterTest {

    private static final ByteArrayOutputStream OUTPUT_STREAM = CheckTestUtil.getByteArrayOutputStream();

    @BeforeAll
    static void setUpStreams() {
        System.setOut(new PrintStream(OUTPUT_STREAM));
    }

    @ParameterizedTest
    @ValueSource(strings = {"DISCOUNT CARD", "DISCOUNT PERCENTAGE", "TOTAL PRICE", "TOTAL WITH DISCOUNT"})
    @DisplayName("Common check printer")
    void printShouldPrintCheckPartsInConsole(String line) {
        Check check = new Check.Builder()
                .order(CheckTestUtil.getOrder())
                .localDateTime(LocalDateTime.now())
                .build();
        new ConsoleCheckPrinter().print(check);
        assertAll(
                () -> assertFalse(OUTPUT_STREAM.toString().isEmpty()),
                () -> assertTrue(OUTPUT_STREAM.toString().contains(line))
        );
    }
}
