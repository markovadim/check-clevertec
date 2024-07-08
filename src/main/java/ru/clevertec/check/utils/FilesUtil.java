package main.java.ru.clevertec.check.utils;

import java.io.File;

public class FilesUtil {

    public static File resultFile;

    public static void parseProductsAndCardsFiles(String productsPath) {
        ParserFactory.getParser(StringStorage.FILE_FORMAT).parse(new File(productsPath));
        ParserFactory.getParser(StringStorage.FILE_FORMAT).parse(new File(StringStorage.CARDS_PATH));
    }

    public static void createResultFile(String path) {
        resultFile = new File(path);
    }

    public static File getResultFile() {
        return resultFile;
    }

}
