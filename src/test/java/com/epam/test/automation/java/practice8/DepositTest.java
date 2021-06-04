package com.epam.test.automation.java.practice8;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;

public class DepositTest {

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testIllegalArgumentExceptionWhenDepositIsNull() {
        new BaseDeposit(null, 1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testIllegalArgumentExceptionWhenPeriodIs0() {
        new BaseDeposit(new BigDecimal(1000), 0);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testIllegalArgumentExceptionWhenPeriodIsLessThan0() {
        new BaseDeposit(new BigDecimal(1000), -1);
    }

    @Test
    public void testGetAmount(){
        Deposit deposit = new BaseDeposit(new BigDecimal(1000), 2);
        Assert.assertEquals(deposit.getAmount(), new BigDecimal(1000));
    }

    @Test
    public void testGetPeriod(){
        Deposit deposit = new BaseDeposit(new BigDecimal(1000), 2);
        Assert.assertEquals(deposit.getPeriod(), 2);
    }

    @Test
    public void testCompareToDepositsEquals(){
        Deposit deposit1 = new BaseDeposit(new BigDecimal(1000), 2);
        Deposit deposit2 = new BaseDeposit(new BigDecimal(1000), 2);
        Assert.assertEquals(deposit1.compareTo(deposit2), 0);
    }

    @Test
    public void testCompareToDeposit1IsLessThanDeposit2(){
        Deposit deposit1 = new BaseDeposit(new BigDecimal(999.99), 2);
        Deposit deposit2 = new BaseDeposit(new BigDecimal(1000), 2);
        Assert.assertEquals(deposit1.compareTo(deposit2), -1);
    }

    @Test
    public void testCompareToDeposit1IsMoreThanDeposit2(){
        Deposit deposit1 = new BaseDeposit(new BigDecimal(1000.02), 2);
        Deposit deposit2 = new BaseDeposit(new BigDecimal(1000.01), 2);
        Assert.assertEquals(deposit1.compareTo(deposit2), 1);
    }

    @Test
    public void testEquals(){
        Deposit deposit1 = new BaseDeposit(new BigDecimal(1000), 2);
        Deposit deposit2 = new BaseDeposit(new BigDecimal(1000), 2);
        Deposit deposit3 = new BaseDeposit(new BigDecimal(5000), 8);
        Assert.assertTrue(deposit1.equals(deposit2));
        Assert.assertFalse(deposit1.equals(deposit3));
    }

    @Test
    public void testHashCode(){
        Deposit deposit = new BaseDeposit(new BigDecimal(1000), 2);
        Assert.assertEquals(deposit.hashCode(), 961963);
    }
}