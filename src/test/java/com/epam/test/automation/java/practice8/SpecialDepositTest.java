package com.epam.test.automation.java.practice8;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SpecialDepositTest {

    @Test
    public void testSpecialDeposit1Month() {
        SpecialDeposit specialDeposit = new SpecialDeposit(new BigDecimal(1000.00), 1);
        specialDeposit.income();
        Assert.assertEquals(specialDeposit.income(), new BigDecimal(10.00).setScale(2, RoundingMode.HALF_EVEN));
    }

    @Test
    public void testSpecialDeposit8Month() {
        SpecialDeposit specialDeposit = new SpecialDeposit(new BigDecimal(1000.00), 4);
        specialDeposit.income();
        Assert.assertEquals(specialDeposit.income(), new BigDecimal(103.55).setScale(2, RoundingMode.HALF_EVEN));
    }

    @Test
    public void testSpecialDepositСanToProlongWhenDepositIsMoreThan1000() {
        SpecialDeposit specialDeposit = new SpecialDeposit(new BigDecimal(1000.01), 1);
        Assert.assertTrue(specialDeposit.canToProlong());
    }

    @Test
    public void testSpecialDepositСanToProlongWhenDepositIsLessThan1000() {
        SpecialDeposit specialDeposit = new SpecialDeposit(new BigDecimal(999.99), 1);
        Assert.assertFalse(specialDeposit.canToProlong());
    }

    @Test
    public void testSpecialDepositСanToProlongWhenDepositIs1000() {
        SpecialDeposit specialDeposit = new SpecialDeposit(new BigDecimal(1000.00), 1);
        Assert.assertFalse(specialDeposit.canToProlong());
    }
}