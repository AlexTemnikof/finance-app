package ru.temnikov.finance.finance.entity;

/**
 * Доход
 */
public class Income extends FinanceOperation {

    public Income(final long amount, final Category category) {
        super(amount, category);
    }
}
