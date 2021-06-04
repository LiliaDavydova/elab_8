package com.epam.test.automation.java.practice8;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class LongDepositTest {

    @Test
    public void testLongDeposit1Month() {
        LongDeposit longDeposit = new LongDeposit(new BigDecimal(1000.00), 1);
        longDeposit.income();
        Assert.assertEquals(longDeposit.income(), BigDecimal.ZERO);
    }

    @Test
    public void testLongDeposit6Month() {
        LongDeposit longDeposit = new LongDeposit(new BigDecimal(5000.00), 6);
        longDeposit.income();
        Assert.assertEquals(longDeposit.income(), BigDecimal.ZERO);
    }

    @Test
    public void testLongDeposit7Month() {
        LongDeposit longDeposit = new LongDeposit(new BigDecimal(1000.00), 7);
        longDeposit.income();
        Assert.assertEquals(longDeposit.income(), new BigDecimal(150.00).setScale(2, RoundingMode.HALF_EVEN));
    }

    @Test
    public void testLongDepositСanToProlongWhenPeriodIsLessThan3Years() {
        LongDeposit longDeposit = new LongDeposit(new BigDecimal(2000.00), 35);
        Assert.assertTrue(longDeposit.canToProlong());
    }

    @Test
    public void testLongDepositСanToProlongWhenPeriodIsMoreThan3Years() {
        LongDeposit longDeposit = new LongDeposit(new BigDecimal(2000.00), 37);
        Assert.assertFalse(longDeposit.canToProlong());
    }

    @Test
    public void testLongDepositСanToProlongWhenPeriodIs3Years() {
        LongDeposit longDeposit = new LongDeposit(new BigDecimal(2000.00), 36);
        Assert.assertFalse(longDeposit.canToProlong());
    }
}