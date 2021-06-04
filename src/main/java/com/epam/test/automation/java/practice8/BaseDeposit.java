package com.epam.test.automation.java.practice8;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BaseDeposit extends Deposit {
    private static final BigDecimal MONTH_PERCENT = BigDecimal.valueOf(0.05);

    public BaseDeposit(BigDecimal depositAmount, int depositPeriod) {
        super(depositAmount, depositPeriod);
    }

    @Override
    public BigDecimal income() {
        BigDecimal currentAmount = this.amount;
        for (int month = 1; month <= this.period; month++) {
            currentAmount = currentAmount.multiply(MONTH_PERCENT).add(currentAmount);
        }
        return currentAmount.subtract(this.amount).setScale(2, RoundingMode.HALF_EVEN);
    }
}
