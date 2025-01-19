package ru.temnikov.finance.finance.entity;

import java.util.*;

public class Wallet {

    private final UUID id;
    private UUID userId;
    private Set<Category> categories;
    private List<FinanceOperation> operations;

    public Wallet(final UUID userId) {
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.categories = new HashSet<>();
        this.operations = new ArrayList<>();
    }

    public UUID id() {
        return id;
    }

    public UUID userId() {
        return userId;
    }

    public void setUserId(final UUID userId) {
        this.userId = userId;
    }

    public Set<Category> categories() {
        return categories;
    }

    public void setCategories(final Set<Category> categories) {
        this.categories = categories;
    }

    public void addCategory(final Category category) {
        categories.add(category);
    }

    public List<FinanceOperation> operations() {
        return operations;
    }

    public void setOperations(final List<FinanceOperation> operations) {
        this.operations = operations;
    }

    public void registerOperation(final FinanceOperation operation) {
        this.operations.add(operation);
    }
}
