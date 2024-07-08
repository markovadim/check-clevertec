package main.java.ru.clevertec.check.utils;

public class StringStorage {

    public static final String CARDS_PATH = "./src/main/resources/discountCards.csv";

    public static final String PATTERN_FILE_ITEMS = "\\d{1,2};.*;(true|false)$";
    public static final String PATTERN_FILE_CARDS = "\\d{4};\\d{1,2}";

    public static final String PATTERN_LINE_ARGS_ITEMS = "(\\d*-\\d*)";
    public static final String PATTERN_LINE_ARGS_DISCOUNT = "discountCard=\\d{4}";
    public static final String PATTERN_LINE_ARGS_BALANCE = "balanceDebitCard=[+-]?(?:\\d*\\.\\d+|\\d+\\.?\\d*)?";
    public static final String PATTERN_LINE_ARGS_WITH_INNER_FILES = "^(\\d+-\\d+\\s+)*((discountCard=\\d+\\s+)?)(balanceDebitCard=[+-]?(?:\\d*\\.\\d+|\\d+\\.?\\d*\\s?){1})(pathToFile=((?:[^/]*[/])*)(.*).csv\\s?)?(saveToFile=((?:[^/]*[/])*)(.*).csv\\s?)?$";

    public static final String FILE_FORMAT = "FILE";
    public static final String CONSOLE_FORMAT = "CONSOLE";

    public static final String DATE_PATTERN = "dd.MM.yyyy";
    public static final String TIME_PATTERN = "HH:mm:ss";
    public static final String CHECK_PRODUCT_HEAD = "QTY;DESCRIPTION;PRICE;DISCOUNT;TOTAL";
    public static final String CHECK_DISCOUNT_CARD_HEAD = "DISCOUNT CARD;DISCOUNT PERCENTAGE";
    public static final String CHECK_TOTALS_HEAD = "TOTAL PRICE;TOTAL DISCOUNT;TOTAL WITH DISCOUNT";

    public static final String RESULT_FILE_NAME = "result.csv";
    public static final String RESULT_FILE_BAD_REQUEST = "error_result.csv";
    public static final String BAD_REQUEST_INPUT_ARGS = "Недостаточно Вводимых Аргументов";
    public static final String BAD_REQUEST_INPUT_PATTERN = "Входные аргументы не соответствуют шаблону";
    public static final String BAD_REQUEST_INPUT_AMOUNT = "Недостаточно продуктов в магазине";
    public static final String BAD_REQUEST_INPUT_ID = "Продукт с идентификатором = %s не найден";
    public static final String ERROR = "ERROR";
}
