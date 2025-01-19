package ru.temnikov.finance.analyze;

import org.springframework.stereotype.Service;
import ru.temnikov.finance.finance.entity.*;
import ru.temnikov.finance.user.entity.User;
import ru.temnikov.finance.user.service.UserService;

import java.util.Optional;

@Service
public class AnalyzeServiceImpl implements AnalyzeService {

    private final UserService userService;

    public AnalyzeServiceImpl(final UserService userService) {
        this.userService = userService;
    }

    public Long getUserSpends() {
        final User user = userService.getActiveUser();
        final Wallet wallet = user.wallet();

        return wallet.operations().stream()
                .filter(operation -> operation instanceof Spend)
                .mapToLong(FinanceOperation::amount)
                .sum();

    }

    public Long getUserIncomes() {
        final User user = userService.getActiveUser();
        final Wallet wallet = user.wallet();

        return wallet.operations().stream()
                .filter(operation -> operation instanceof Income)
                .mapToLong(FinanceOperation::amount)
                .sum();
    }

    public Long getUserSpendsByCategory(final String categoryName) {
        final User user = userService.getActiveUser();
        final Wallet wallet = user.wallet();
        final Optional<Category> categoryOptional = wallet.categories().stream()
                .filter(cat -> cat.name().equals(categoryName))
                .findFirst();
        if (categoryOptional.isEmpty()) {
            System.out.println("No category found");
            return null;
        }

        return wallet.operations().stream()
                .filter(operation -> operation instanceof Spend && operation.category().equals(categoryOptional.get()))
                .mapToLong(FinanceOperation::amount)
                .sum();
    }

    public Long getUserIncomesByCategory(final String categoryName) {
        final User user = userService.getActiveUser();
        final Wallet wallet = user.wallet();
        final Optional<Category> categoryOptional = wallet.categories().stream()
                .filter(cat -> cat.name().equals(categoryName))
                .findFirst();
        if (categoryOptional.isEmpty()) {
            System.out.println("No category found");
            return null;
        }

        return wallet.operations().stream()
                .filter(operation -> operation instanceof Income && operation.category().equals(categoryOptional.get()))
                .mapToLong(FinanceOperation::amount)
                .sum();
    }
}
