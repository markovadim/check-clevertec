package main.java.ru.clevertec.check.services;

import main.java.ru.clevertec.check.repositories.DiscountCardRepository;
import main.java.ru.clevertec.check.repositories.ProductRepository;
import main.java.ru.clevertec.check.models.DebitCard;
import main.java.ru.clevertec.check.models.DiscountCard;
import main.java.ru.clevertec.check.models.Product;
import main.java.ru.clevertec.check.utils.StringStorage;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class CommandLineArgumentsParser implements Parser {

    private Map<Product, Integer> bucket = new HashMap<>();
    private DiscountCard discountCard;
    private DebitCard debitCard;

    @Override
    public void parse(Object... objects) {
        String[] args = (String[]) objects;
        if (checkInputFormat(args)) {
            parseArgs(args);
        }
    }

    public boolean checkInputFormat(String[] args) {
        if (args.length < 3) {
            System.out.println("Недостаточно Вводимых аргументов.");
            return false;
        }
        Pattern lineArgsPattern = Pattern.compile(StringStorage.PATTERN_LINE_ARGS);
        if (!lineArgsPattern.matcher(String.join(" ", args)).matches()) {
            System.out.println("Не Соответствует шаблону!");
            return false;
        }
        return true;
    }

    private void parseArgs(String[] args) {
        Pattern pattern1 = Pattern.compile(StringStorage.PATTERN_LINE_ARGS_ITEMS);
        Pattern pattern2 = Pattern.compile(StringStorage.PATTERN_LINE_ARGS_DISCOUNT);
        Pattern pattern3 = Pattern.compile(StringStorage.PATTERN_LINE_ARGS_BALANCE);

        for (String str : args) {
            System.out.println(str);
            if (pattern1.matcher(str).matches()) {
                fillingBucket(str);
            }
            if (pattern2.matcher(str).matches()) {
                findDiscountCard(str);
            }
            if (pattern3.matcher(str).matches()) {
                formationDebitCard(str);
            }
        }
    }

    private void fillingBucket(String str) {
        String[] productPart = str.split("-");
        Product currentProduct = ProductRepository.getById(Integer.parseInt(productPart[0])).orElseThrow();
        if (bucket.get(currentProduct) == null) {
            bucket.put(
                    currentProduct,
                    Integer.parseInt(productPart[1])
            );
        } else {
            int currentAmount = bucket.get(currentProduct);
            bucket.replace(
                    currentProduct,
                    currentAmount + Integer.parseInt(productPart[1])
            );
        }
    }

    private void findDiscountCard(String str) {
        String[] discountCardPart = str.split("=");
        discountCard = DiscountCardRepository.getByNumber(Integer.parseInt(discountCardPart[1]))
                .orElse(new DiscountCard.Builder()
                        .number(Integer.parseInt(discountCardPart[1]))
                        .discountPercentage(2)
                        .build()
                );
    }

    private void formationDebitCard(String str) {
        String[] debitCardPart = str.split("=");
        debitCard = new DebitCard(Double.parseDouble(debitCardPart[1]));
    }

    public Map<Product, Integer> getBucket() {
        return bucket;
    }

    public DiscountCard getDiscountCard() {
        return discountCard;
    }

    public DebitCard getDebitCard() {
        return debitCard;
    }


    @Override
    public String toString() {
        return "CommandLineArgumentsParser{" +
                "bucket=" + bucket +
                ", discountCard=" + discountCard +
                ", debitCard=" + debitCard +
                '}';
    }
}
