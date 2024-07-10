package ru.clevertec.check.services.implementations;

import ru.clevertec.check.repositories.DiscountCardRepository;
import ru.clevertec.check.models.DiscountCard;
import ru.clevertec.check.services.interfaces.Parser;
import ru.clevertec.check.utils.StringStorage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class CSVFileParser implements Parser {

    @Override
    public void parse(Object... objects) {
        File file = (File) objects[0];
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                try (Scanner rowScanner = new Scanner(line)) {
                    rowScanner.useDelimiter("\n");
                    while (rowScanner.hasNext()) {
                        String str = rowScanner.next();
                        Pattern patternForCard = Pattern.compile(StringStorage.PATTERN_FILE_CARDS);
                        if (patternForCard.matcher(str).matches()) {
                            fillingCardsStorage(str);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void fillingCardsStorage(String str) {
        DiscountCardRepository.cards.add(
                new DiscountCard.Builder()
                        .number(Integer.parseInt(str.split(";")[0]))
                        .discountPercentage(Integer.parseInt(str.split(";")[1]))
                        .build()
        );
    }
}
