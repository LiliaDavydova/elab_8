package com.epam.test.automation.java.practice8;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class LongDeposit extends Deposit implements Prolongable {
    private static final BigDecimal MONTH_PERCENT = BigDecimal.valueOf(0.15);
    private static final int INCOME_START_MONTH = 7;

    public LongDeposit(BigDecimal depositAmount, int depositPeriod) {
        super(depositAmount, depositPeriod);
    }

    @Override
    public BigDecimal income() {
        if (this.period < INCOME_START_MONTH) {
            return BigDecimal.ZERO;
        }
        BigDecimal currentAmount = this.amount;
        for (int month = INCOME_START_MONTH; month <= this.period; month++) {
            currentAmount = currentAmount.multiply(MONTH_PERCENT).add(currentAmount);
        }
        return currentAmount.subtract(this.amount).setScale(2, RoundingMode.HALF_EVEN);
    }

    @Override
    public boolean canToProlong() {
        return this.period < 36;
    }
}
