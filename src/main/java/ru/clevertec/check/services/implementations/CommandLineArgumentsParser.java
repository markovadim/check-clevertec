package main.java.ru.clevertec.check.services.implementations;

import main.java.ru.clevertec.check.exceptions.BadRequestException;
import main.java.ru.clevertec.check.exceptions.ProductNotFoundException;
import main.java.ru.clevertec.check.repositories.DiscountCardRepository;
import main.java.ru.clevertec.check.repositories.ProductRepository;
import main.java.ru.clevertec.check.models.DebitCard;
import main.java.ru.clevertec.check.models.DiscountCard;
import main.java.ru.clevertec.check.models.Product;
import main.java.ru.clevertec.check.services.interfaces.Parser;
import main.java.ru.clevertec.check.utils.StringStorage;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class CommandLineArgumentsParser implements Parser {

    private Map<Product, Integer> basket = new HashMap<>();
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
        if (args.length < 2) {
            throw new BadRequestException(StringStorage.BAD_REQUEST_INPUT_ARGS);
        }
        Pattern lineArgsPattern = Pattern.compile(StringStorage.PATTERN_LINE_ARGS);
        if (!lineArgsPattern.matcher(String.join(" ", args)).matches()) {
            throw new BadRequestException(StringStorage.BAD_REQUEST_INPUT_PATTERN);
        }
        return true;
    }

    private void parseArgs(String[] args) {
        Pattern pattern1 = Pattern.compile(StringStorage.PATTERN_LINE_ARGS_ITEMS);
        Pattern pattern2 = Pattern.compile(StringStorage.PATTERN_LINE_ARGS_DISCOUNT);
        Pattern pattern3 = Pattern.compile(StringStorage.PATTERN_LINE_ARGS_BALANCE);

        for (String str : args) {
            if (pattern1.matcher(str).matches()) {
                try {
                    fillingBucket(str);
                } catch (ProductNotFoundException e) {
                    System.out.println(e.getMessage());
                }
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
        Product currentProduct = ProductRepository.getById(Integer.parseInt(productPart[0]))
                .orElseThrow(() -> new ProductNotFoundException(String.format(StringStorage.BAD_REQUEST_INPUT_ID, productPart[0])));
        if (currentProduct.getQuantityInStock() >= Integer.parseInt(productPart[1])) {
            if (basket.get(currentProduct) == null) {
                basket.put(
                        currentProduct,
                        Integer.parseInt(productPart[1])
                );
            } else {
                int currentAmount = basket.get(currentProduct);
                basket.replace(
                        currentProduct,
                        currentAmount + Integer.parseInt(productPart[1])
                );
            }
        } else {
            throw new BadRequestException(StringStorage.BAD_REQUEST_INPUT_AMOUNT);
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

    public Map<Product, Integer> getBasket() {
        return basket;
    }

    public DiscountCard getDiscountCard() {
        return discountCard;
    }

    public DebitCard getDebitCard() {
        return debitCard;
    }
}
