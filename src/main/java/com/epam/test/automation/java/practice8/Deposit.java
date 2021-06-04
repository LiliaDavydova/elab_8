package com.epam.test.automation.java.practice8;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class Deposit implements Comparable<Deposit> {
    public final BigDecimal amount;
    public final int period;

    protected Deposit(BigDecimal amount, int period) {
        if (amount == null || period <= 0) {
            throw new IllegalArgumentException();
        }
        this.amount = amount;
        this.period = period;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public int getPeriod() {
        return period;
    }

    public abstract BigDecimal income();

    @Override
    public int compareTo(Deposit deposit) {
        BigDecimal sum1 = this.amount.add(income());
        BigDecimal sum2 = deposit != null ? deposit.getAmount().add(deposit.income()) : BigDecimal.ZERO;
        return sum1.compareTo(sum2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deposit deposit = (Deposit) o;
        return period == deposit.period &&
                amount.equals(deposit.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, period);
    }
}


