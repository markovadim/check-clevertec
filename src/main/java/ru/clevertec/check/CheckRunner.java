package main.java.ru.clevertec.check;

import main.java.ru.clevertec.check.models.Check;
import main.java.ru.clevertec.check.models.Order;
import main.java.ru.clevertec.check.services.*;
import main.java.ru.clevertec.check.utils.StringStorage;

import java.io.File;

public class CheckRunner {

    public static void main(String[] args) {
        File productsFile = new File(StringStorage.PRODUCTS_PATH);
        File cardsFile = new File(StringStorage.CARDS_PATH);

        // ������ ����� �����
        ParserFactory.getParser(StringStorage.FILE_FORMAT).parse(productsFile);
        ParserFactory.getParser(StringStorage.FILE_FORMAT).parse(cardsFile);

        // ������ ��������� �������
        Parser parser = ParserFactory.getParser(StringStorage.CONSOLE_FORMAT);
        parser.parse(args);
        CommandLineArgumentsParser commandLineArgumentsParser = (CommandLineArgumentsParser) parser;

        // ������� ����� �������� � �������� �����
        OrderHandler orderHandler = new OrderHandler();
        Order order = orderHandler.createOrder(commandLineArgumentsParser.getBucket(), commandLineArgumentsParser.getDiscountCard(), commandLineArgumentsParser.getDebitCard());

        // ������� ������-������� � �������� ���
        PaymentService paymentService = new PaymentService();
        Check check = paymentService.pay(order);

        // �������� ���
        CheckPrinter printer = CheckPrinterFactory.getParser(StringStorage.FILE_FORMAT);
        printer.print(check);
    }
}
