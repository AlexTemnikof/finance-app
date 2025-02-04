package ru.temnikov.finance.command;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import ru.temnikov.finance.analyze.AnalyzeService;
import ru.temnikov.finance.finance.service.FinanceService;
import ru.temnikov.finance.user.service.UserService;

import java.util.Scanner;

@Service
public class CommandService {

    private final AnalyzeService analyzeService;

    private final FinanceService financeService;

    private final UserService userService;

    public CommandService(final AnalyzeService analyzeService, final FinanceService financeService, final UserService userService) {
        this.analyzeService = analyzeService;
        this.financeService = financeService;
        this.userService = userService;
    }

    @PostConstruct
    public void start() {
        try(final Scanner scanner = new Scanner(System.in)) {

            boolean isAuthenticated = false;

            while (!isAuthenticated) {
                System.out.println("1. Register");
                System.out.println("2. Login");
                final String authCommand = scanner.nextLine();
                System.out.println("Enter username:");
                String inputUsername = scanner.nextLine();

                System.out.println("Enter password:");
                String inputPassword = scanner.nextLine();

                switch(authCommand) {
                    case "1":
                        isAuthenticated = userService.register(inputUsername, inputPassword);
                        break;
                    case "2":
                        isAuthenticated = userService.login(inputUsername, inputPassword);
                        break;
                    default:
                        System.out.println("Wrong command");
                }
            }

            boolean exit = false;
            while (!exit) {
                System.out.println("\nMenu:");
                System.out.println("1. Create wallet");
                System.out.println("2. Add category");
                System.out.println("3. Add expense");
                System.out.println("4. Add income");
                System.out.println("5. Get expenses statistics");
                System.out.println("6. Get incomes statistics");
                System.out.println("7. Get expenses statistics by category");
                System.out.println("8. Get incomes statistics by category");

                System.out.println("9. Exit");
                System.out.print("Choose command: ");

                String command = scanner.nextLine();

                switch (command) {
                    case "1":
                        createWallet();
                        break;
                    case "2":
                        addCategory(scanner);
                        break;
                    case "3":
                        addExpense(scanner);
                        break;
                    case "4":
                        addIncome(scanner);
                        break;
                    case "5":
                        getExpensesStat();
                        break;
                    case "6":
                        getIncomesStat();
                        break;
                    case "7":
                        getExpensesStatByCategory(scanner);
                        break;
                    case "8":
                        getIncomesStatByCategory(scanner);
                        break;
                    case "9":
                        System.out.println("exited");
                        exit = true;
                        break;
                    default:
                        System.out.println("Неверная команда, попробуйте снова.");
                }
            }
        } catch (RuntimeException e) {
            System.out.println("Wrong command");
        }
    }

    private void createWallet() {
        financeService.createWallet();
        System.out.println("Wallet created successfully!");
    }

    private void addCategory(final Scanner scanner) {

        System.out.print("Enter category name: ");
        String categoryName = scanner.nextLine();

        System.out.print("Enter spending limit (or 0 for no limit): ");
        long spendLimit = scanner.nextLong();
        scanner.nextLine();

        financeService.createCategory(categoryName, spendLimit);
        System.out.println("Category added successfully!");
    }

    private void addExpense(final Scanner scanner) {
        System.out.print("Enter category name: ");
        String categoryName = scanner.nextLine();

        System.out.print("Enter expense amount: ");
        long amount = scanner.nextLong();
        scanner.nextLine();

        financeService.registerSpend(categoryName, amount);
    }

    private void addIncome(final Scanner scanner) {
        System.out.print("Enter category name: ");
        String categoryName = scanner.nextLine();

        System.out.print("Enter income amount: ");
        long amount = scanner.nextLong();
        scanner.nextLine();

        financeService.registerIncome(categoryName, amount);
    }

    private void getExpensesStat() {
        System.out.println("Expenses are " + analyzeService.getUserSpends());
    }

    private void getIncomesStat() {
        System.out.println("Incomes are " + analyzeService.getUserIncomes());
    }

    private void getExpensesStatByCategory(final Scanner scanner) {
        System.out.print("Enter category name: ");
        String categoryName = scanner.nextLine();
        System.out.println("Expenses are " + analyzeService.getUserSpendsByCategory(categoryName));
    }

    private void getIncomesStatByCategory(final Scanner scanner) {
        System.out.print("Enter category name: ");
        String categoryName = scanner.nextLine();
        System.out.println("Incomes are " + analyzeService.getUserIncomesByCategory(categoryName));
    }
}
