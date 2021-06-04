package com.epam.test.automation.java.practice8;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BaseDepositTest {

    @Test
    public void testBaseDeposit1Month() {
        BaseDeposit baseDeposit = new BaseDeposit(new BigDecimal(1000.00), 1);
        baseDeposit.income();
        Assert.assertEquals(baseDeposit.income(), new BigDecimal(50.00).setScale(2, RoundingMode.HALF_EVEN));
    }

    @Test
    public void testBaseDeposit() {
        BaseDeposit baseDeposit = new BaseDeposit(new BigDecimal(1000.45), 2);
        baseDeposit.income();
        Assert.assertEquals(baseDeposit.income(), new BigDecimal(102.55).setScale(2, RoundingMode.HALF_EVEN));
    }

    @Test
    public void testBaseDeposit3Month() {
        BaseDeposit baseDeposit = new BaseDeposit(new BigDecimal(1000.00), 3);
        baseDeposit.income();
        Assert.assertEquals(baseDeposit.income(), new BigDecimal(157.62).setScale(2, RoundingMode.HALF_EVEN));
    }
}