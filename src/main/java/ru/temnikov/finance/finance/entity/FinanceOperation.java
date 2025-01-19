package ru.temnikov.finance.finance.entity;

import java.util.Date;

/**
 * Финансовая операция
 */
public abstract class FinanceOperation {

    private long amount;

    private Category category;

    private final Date date;

    public FinanceOperation(final long amount, final Category category) {
        this.amount = amount;
        this.category = category;
        this.date = new Date();
    }

    public long amount() {
        return amount;
    }

    public void setAmount(final long amount) {
        this.amount = amount;
    }

    public Category category() {
        return category;
    }

    public void setCategory(final Category category) {
        this.category = category;
    }

    public Date date() {
        return date;
    }
}
