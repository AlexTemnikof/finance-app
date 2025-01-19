package ru.temnikov.finance.finance.service;

import org.springframework.stereotype.Service;
import ru.temnikov.finance.finance.entity.Category;
import ru.temnikov.finance.finance.entity.Income;
import ru.temnikov.finance.finance.entity.Spend;
import ru.temnikov.finance.finance.entity.Wallet;
import ru.temnikov.finance.user.entity.User;
import ru.temnikov.finance.user.service.UserService;

import java.util.Optional;

@Service
public class FinanceServiceImpl implements FinanceService {

    private final UserService userService;

    public FinanceServiceImpl(final UserService userService) {
        this.userService = userService;
    }

    public Wallet createWallet() {
        final User user = userService.getActiveUser();
        if (user.wallet() != null) {
            System.out.println("The wallet already created");
        }
        final var wallet = new Wallet(user.id());
        user.setWallet(wallet);
        return wallet;
    }

    public void createCategory(final String name, final Long spendLimit) {
        final User user = userService.getActiveUser();
        final var category = new Category(name, spendLimit);
        user.wallet().addCategory(category);
    }

    public void registerSpend(final String categoryName, final Long amount) {
        final User user = userService.getActiveUser();
        final Wallet userWallet = user.wallet();
        final Optional<Category> category = userWallet.categories().stream()
                .filter(cat -> cat.name().equals(categoryName))
                .findFirst();
        if (category.isEmpty()) {
            System.out.println("Category has not found. You should create category first.");
            return;
        }

        final var spend = new Spend(amount, category.get());
        userWallet.registerOperation(spend);
    }

    public void registerIncome(final String categoryName, final Long amount) {
        final User user = userService.getActiveUser();
        final Wallet userWallet = user.wallet();
        final Optional<Category> category = userWallet.categories().stream()
                .filter(cat -> cat.name().equals(categoryName))
                .findFirst();
        if (category.isEmpty()) {
            System.out.println("Category has not found. You should create category first.");
            return;
        }

        final var spend = new Income(amount, category.get());
        userWallet.registerOperation(spend);
    }
}
