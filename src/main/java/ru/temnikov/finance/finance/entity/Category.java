package ru.temnikov.finance.finance.entity;

import java.util.Objects;

public class Category {

    private String name;

    private Long spendLimit;

    public Category(final String name, final Long spendLimit) {
        this.name = name;
        this.spendLimit = spendLimit;
    }

    public String name() {
        return name;
    }

    public Long spendLimit() {
        return spendLimit;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Category category = (Category) o;
        return Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
