package ru.temnikov.finance.analyze;

public interface AnalyzeService {

    Long getUserSpends();

    Long getUserIncomes();

    Long getUserSpendsByCategory(final String categoryName);

    Long getUserIncomesByCategory(final String categoryName);
}
