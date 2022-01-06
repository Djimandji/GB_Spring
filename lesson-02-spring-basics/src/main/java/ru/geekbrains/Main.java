package ru.geekbrains;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;
import ru.geekbrains.persist.ProductRepositoryImpl;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        new Main().start();
    }

    public void start() {
            ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
            CartService cartService = context.getBean("cartService", CartService.class);
            cartService.showRepository();
            cartService.showProduct();
            Scanner scanner = new Scanner(System.in);
            String command = new String();
            while (true) {
                command = scanner.next();
                if (command.equals("showProduct")) {
                    cartService.showProduct();
                }
                if (command.equals("showRepository")) {
                    cartService.showRepository();
                }
                if (command.startsWith("deleteProduct_")) {
                    String subcommand[] = command.split("_");
                    try {
                        long id = Long.valueOf(subcommand[1]);
                        cartService.deleteProduct(id);
                    } catch (NumberFormatException exception) {
                        System.out.println("Неверный формат строки");
                        exception.printStackTrace();
                    } catch (ArrayIndexOutOfBoundsException arrayEx) {
                        System.out.println("Не указано id");
                        arrayEx.printStackTrace();
                    }
                }
                if (command.startsWith("addProduct_")) {
                    String subcommand[] = command.split("_");
                    try {
                        long id = Long.valueOf(subcommand[1]);
                        Product product = cartService.getProductRepository().findById(id);
                        cartService.addProduct(product);
                    } catch (NumberFormatException exception) {
                        System.out.println("Неверный формат строки");
                        exception.printStackTrace();
                    } catch (ArrayIndexOutOfBoundsException  | NullPointerException aEx) {
                        System.out.println("Ошибка id");
                        aEx.printStackTrace();
                    }
                }
            }
    }
}
