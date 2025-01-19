package ru.temnikov.finance.finance.service;

import ru.temnikov.finance.finance.entity.Wallet;

public interface FinanceService {

    Wallet createWallet();

    void createCategory(final String name, final Long spendLimit);

    void registerSpend(final String categoryName, final Long amount);

    void registerIncome(final String categoryName, final Long amount);


}
