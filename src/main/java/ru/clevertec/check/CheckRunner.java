package ru.clevertec.check;

import ru.clevertec.check.exceptions.BadRequestException;
import ru.clevertec.check.exceptions.NotEnoughMoneyException;
import ru.clevertec.check.exceptions.ProductNotFoundException;
import ru.clevertec.check.models.Check;
import ru.clevertec.check.models.Order;
import ru.clevertec.check.services.OrderHandler;
import ru.clevertec.check.services.PaymentService;
import ru.clevertec.check.services.implementations.CommandLineArgumentsParser;
import ru.clevertec.check.services.interfaces.CheckPrinter;
import ru.clevertec.check.services.interfaces.Parser;
import ru.clevertec.check.utils.CheckPrinterFactory;
import ru.clevertec.check.utils.ParserFactory;
import ru.clevertec.check.utils.StringStorage;

import java.io.File;

public class CheckRunner {

    public static void main(String[] args) {
        CheckPrinter filePrinter = CheckPrinterFactory.getParser(StringStorage.FILE_FORMAT);
        CheckPrinter consolePrinter = CheckPrinterFactory.getParser(StringStorage.CONSOLE_FORMAT);

        try {
            ParserFactory.getParser(StringStorage.FILE_FORMAT).parse(new File(StringStorage.CARDS_PATH));
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
