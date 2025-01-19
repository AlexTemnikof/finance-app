package ru.temnikov.finance.user.entity;

import ru.temnikov.finance.finance.entity.Wallet;

import java.util.Objects;
import java.util.UUID;

public class User {

    private final UUID id;

    private String username;

    private String hashedPassword;

    private Wallet wallet;

    public User(final String username, final String hashedPassword) {
        this.id = UUID.randomUUID();
        this.username = username;
        this.hashedPassword = hashedPassword;
    }

    public UUID id() {
        return id;
    }

    public void setWallet(final Wallet wallet) {
        this.wallet = wallet;
    }

    public Wallet wallet() {
        return wallet;
    }

    public String username() {
        return this.username;
    }

    public String hashedPassword() {
        return this.hashedPassword;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
