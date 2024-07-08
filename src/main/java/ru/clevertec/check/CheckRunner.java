package main.java.ru.clevertec.check;

import main.java.ru.clevertec.check.exceptions.BadRequestException;
import main.java.ru.clevertec.check.exceptions.NotEnoughMoneyException;
import main.java.ru.clevertec.check.exceptions.ProductNotFoundException;
import main.java.ru.clevertec.check.models.Check;
import main.java.ru.clevertec.check.models.Order;
import main.java.ru.clevertec.check.services.OrderHandler;
import main.java.ru.clevertec.check.services.PaymentService;
import main.java.ru.clevertec.check.services.implementations.CommandLineArgumentsParser;
import main.java.ru.clevertec.check.services.interfaces.CheckPrinter;
import main.java.ru.clevertec.check.services.interfaces.Parser;
import main.java.ru.clevertec.check.utils.CheckPrinterFactory;
import main.java.ru.clevertec.check.utils.ParserFactory;
import main.java.ru.clevertec.check.utils.StringStorage;

public class CheckRunner {

    public static void main(String[] args) {
        CheckPrinter filePrinter = CheckPrinterFactory.getParser(StringStorage.FILE_FORMAT);
        CheckPrinter consolePrinter = CheckPrinterFactory.getParser(StringStorage.CONSOLE_FORMAT);

        try {
            Parser parser = ParserFactory.getParser(StringStorage.CONSOLE_FORMAT);
            parser.parse(args);
            CommandLineArgumentsParser commandLineArgumentsParser = (CommandLineArgumentsParser) parser;

            OrderHandler orderHandler = new OrderHandler();
            Order order = orderHandler.createOrder(commandLineArgumentsParser.getBucket(), commandLineArgumentsParser.getDiscountCard(), commandLineArgumentsParser.getDebitCard());

            PaymentService paymentService = new PaymentService();
            Check check = paymentService.pay(order);

            filePrinter.print(check);
            consolePrinter.print(check);

        } catch (BadRequestException | ProductNotFoundException | NotEnoughMoneyException e) {
            filePrinter.print(new Check(e.getMessage()));
            System.out.println(e.getMessage());
        }
    }
}
