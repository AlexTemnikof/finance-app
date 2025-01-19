package ru.temnikov.finance.finance.entity;

/**
 * Трата
 */
public class Spend extends FinanceOperation {

    public Spend(final long amount, final Category category) {
        super(amount, category);
    }
}
