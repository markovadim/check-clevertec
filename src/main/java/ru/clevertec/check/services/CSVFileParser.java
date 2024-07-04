package main.java.ru.clevertec.check.services;

import main.java.ru.clevertec.check.repositories.DiscountCardRepository;
import main.java.ru.clevertec.check.repositories.ProductRepository;
import main.java.ru.clevertec.check.models.DiscountCard;
import main.java.ru.clevertec.check.models.Product;
import main.java.ru.clevertec.check.utils.StringStorage;

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
                        Pattern patternForItems = Pattern.compile(StringStorage.PATTERN_FILE_ITEMS);
                        Pattern patternForCard = Pattern.compile(StringStorage.PATTERN_FILE_CARDS);
                        if (patternForItems.matcher(str).matches()) {
                            fillingStorage(Product.class, str);
                        }
                        if (patternForCard.matcher(str).matches()) {
                            fillingStorage(DiscountCard.class, str);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void fillingStorage(Class<?> tClass, String str) {
        if (tClass.equals(Product.class)) {
            ProductRepository.products.add(
                    new Product.Builder()
                            .id(Integer.parseInt(str.split(";")[0]))
                            .name(str.split(";")[1])
                            .price(Double.parseDouble(str.split(";")[2].replaceAll(",", ".")))
                            .quantityInStock(Integer.parseInt(str.split(";")[3]))
                            .isWholesale(Boolean.parseBoolean(str.split(";")[4]))
                            .build()
            );
        }
        if (tClass.equals(DiscountCard.class)) {
            DiscountCardRepository.cards.add(
                    new DiscountCard.Builder()
                            .number(Integer.parseInt(str.split(";")[0]))
                            .discountPercentage(Integer.parseInt(str.split(";")[1]))
                            .build()
            );
        }
    }
}
