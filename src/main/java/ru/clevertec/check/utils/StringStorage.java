package main.java.ru.clevertec.check.utils;

public class StringStorage {

    public static final String PRODUCTS_PATH = "src\\main\\resources\\products.csv";
    public static final String CARDS_PATH = "src\\main\\resources\\discountCards.csv";

    public static final String PATTERN_FILE_ITEMS = "\\d{1,2};.*;(true|false)$";
    public static final String PATTERN_FILE_CARDS = "\\d{4};\\d{1,2}";

    public static final String PATTERN_LINE_ARGS_ITEMS = "(\\d*-\\d*)";
    public static final String PATTERN_LINE_ARGS_DISCOUNT = "discountCard=\\d{4}";
    public static final String PATTERN_LINE_ARGS_BALANCE = "balanceDebitCard=[+-]?(?:\\d*\\.\\d+|\\d+\\.?\\d*)?";
    public static final String PATTERN_LINE_ARGS = "^(\\d+-\\d+\\s+)*(discountCard=\\d+\\s+balanceDebitCard=[+-]?(?:\\d*\\.\\d+|\\d+\\.?\\d*))?$";

    public static final String FILE_FORMAT = "FILE";
    public static final String CONSOLE_FORMAT = "CONSOLE";
}
