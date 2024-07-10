package ru.clevertec.check.utils;

import java.io.File;

public class FilesUtil {

    public static File resultFile;

    public static void createResultFile(String path) {
        resultFile = new File(path);
    }

    public static File getResultFile() {
        return resultFile;
    }

}
