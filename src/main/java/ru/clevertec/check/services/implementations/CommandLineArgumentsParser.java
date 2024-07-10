package ru.clevertec.check.services.implementations;

import ru.clevertec.check.exceptions.BadRequestException;
import ru.clevertec.check.exceptions.ProductNotFoundException;
import ru.clevertec.check.repositories.DiscountCardRepository;
import ru.clevertec.check.repositories.ProductRepository;
import ru.clevertec.check.repositories.ProductRepositoryImpl;
import ru.clevertec.check.models.DebitCard;
import ru.clevertec.check.models.DiscountCard;
import ru.clevertec.check.models.Product;
import ru.clevertec.check.services.interfaces.Parser;
import ru.clevertec.check.utils.FilesUtil;
import ru.clevertec.check.utils.StringStorage;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class CommandLineArgumentsParser implements Parser {

    private Map<Product, Integer> bucket = new HashMap<>();
    private DiscountCard discountCard;
    private DebitCard debitCard;
    private ProductRepository productRepository;

    @Override
    public void parse(Object... objects) {
        String[] args = (String[]) objects;
        if (checkInputFormat(args)) {
            parseDataBaseAccessData(args);
            parseArgs(args);
        }
    }

    public boolean checkInputFormat(String[] args) {
        if (args.length < 5) {
            throw new BadRequestException(StringStorage.BAD_REQUEST_INPUT_ARGS);
        }
        Pattern lineArgsWithFilesPattern = Pattern.compile(StringStorage.PATTERN_LINE_ARGS_WITH_DATABASE);
        if (!lineArgsWithFilesPattern.matcher(String.join(" ", args)).matches()) {
            throw new BadRequestException(StringStorage.BAD_REQUEST_INPUT_PATTERN);
        }
        return true;
    }

    public void parseDataBaseAccessData(String[] args) {
        String s = String.join(" ", args);
        String url = null;
        String username = null;
        String password = null;
        if (s.contains("datasource.url=") && s.contains("datasource.username=") && s.contains("datasource.password=")) {
            for (String ss : s.split(" ")) {
                if (ss.startsWith("datasource.url=")) {
                    url = ss.split("=")[1];
                }
                if (ss.startsWith("datasource.username=")) {
                    username = ss.split("=")[1];
                }
                if (ss.startsWith("datasource.password=")) {
                    password = ss.split("=")[1];
                }
                if (ss.startsWith("saveToFile=")) {
                    FilesUtil.createResultFile(ss.split("=")[1]);
                } else {
                    FilesUtil.createResultFile(StringStorage.RESULT_FILE_NAME);
                }
            }
        } else {
            FilesUtil.createResultFile(StringStorage.RESULT_FILE_BAD_REQUEST);
            throw new BadRequestException("Invalid input arguments");
        }
        productRepository = new ProductRepositoryImpl(url, username, password);
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
        Product currentProduct = productRepository.findById(Integer.parseInt(productPart[0]))
                .orElseThrow(() -> new ProductNotFoundException(String.format(StringStorage.BAD_REQUEST_INPUT_ID, productPart[0])));
        if (currentProduct.getQuantityInStock() >= Integer.parseInt(productPart[1])) {
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

    public Map<Product, Integer> getBucket() {
        return bucket;
    }

    public DiscountCard getDiscountCard() {
        return discountCard;
    }

    public DebitCard getDebitCard() {
        return debitCard;
    }
}
