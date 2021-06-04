package com.epam.test.automation.java.practice8;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.NoSuchElementException;

public class ClientTest {


    @Test
    public void testAddDepositTrue() {
        Deposit longDeposit = new LongDeposit(new BigDecimal(3200.00), 5);
        Deposit baseDeposit = new BaseDeposit(new BigDecimal(4000.00), 6);
        Client client = new Client();
        Assert.assertTrue(client.addDeposit(longDeposit));
        Assert.assertTrue(client.addDeposit(baseDeposit));
        Assert.assertEquals(client.getDeposits()[0], longDeposit);
        Assert.assertEquals(client.getDeposits()[1], baseDeposit);
    }

    @Test
    public void testAddDepositFalse() {
        Client client = new Client();
        for (int i = 0; i < 10; i++) {
            client.addDeposit(new BaseDeposit(new BigDecimal(4000.00), 2));
        }
        Assert.assertFalse(client.addDeposit(new BaseDeposit(new BigDecimal(4000.00), 2)));

    }

    @Test
    public void testAddDepositNull() {
        Client client = new Client();
        Assert.assertFalse(client.addDeposit(null));
    }

    @Test
    public void testTotalIncome() {
        Deposit longDeposit = new LongDeposit(new BigDecimal(3200.00), 7);
        Deposit baseDeposit = new BaseDeposit(new BigDecimal(4000.00), 3);
        Deposit specialDeposit = new SpecialDeposit(new BigDecimal(6000.00), 4);
        Client client = new Client();
        client.addDeposit(longDeposit);
        client.addDeposit(baseDeposit);
        client.addDeposit(specialDeposit);
        Assert.assertEquals(client.totalIncome(), new BigDecimal(1731.80).setScale(2, RoundingMode.HALF_EVEN));
    }

    @Test
    public void testMaxIncome() {
        Deposit longDeposit = new LongDeposit(new BigDecimal(3200.00), 7);
        Deposit baseDeposit = new BaseDeposit(new BigDecimal(4000.00), 3);
        Deposit specialDeposit = new SpecialDeposit(new BigDecimal(6000.00), 4);
        Client client = new Client();
        client.addDeposit(longDeposit);
        client.addDeposit(baseDeposit);
        client.addDeposit(specialDeposit);
        Assert.assertEquals(client.maxIncome(), new BigDecimal(630.50).setScale(2, RoundingMode.HALF_EVEN));
    }

    @Test
    public void testGetIncomeByNumber() {
        Deposit longDeposit = new LongDeposit(new BigDecimal(3200.00), 7);
        Deposit baseDeposit = new BaseDeposit(new BigDecimal(4000.00), 3);
        Deposit specialDeposit = new SpecialDeposit(new BigDecimal(6000.00), 4);
        Client client = new Client();
        client.addDeposit(longDeposit);
        client.addDeposit(baseDeposit);
        client.addDeposit(specialDeposit);
        Assert.assertEquals(client.getIncomeByNumber(2), new BigDecimal(621.30).setScale(2, RoundingMode.HALF_EVEN));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testGetIncomeByNumberWhenNumberIsMoreThanDepositLength() {
        Client client = new Client();
        for (int i = 0; i < 10; i++) {
            client.addDeposit(new BaseDeposit(new BigDecimal(4000.00), 2));
        }
        client.getIncomeByNumber(11);
    }

    @Test
    public void testGetIncomeByNumberWhenNumberIsNull() {
        Client client = new Client();
        Assert.assertEquals(client.getIncomeByNumber(1), BigDecimal.ZERO);
    }

    @Test
    public void testSortDeposits() {
        Deposit deposit1 = new LongDeposit(new BigDecimal(200.00), 7);
        Deposit deposit2 = new BaseDeposit(new BigDecimal(1000.00), 3);
        Deposit deposit3 = new SpecialDeposit(new BigDecimal(3000.00), 4);
        Client client = new Client();
        client.addDeposit(deposit1);
        client.addDeposit(deposit2);
        client.addDeposit(deposit3);
        Deposit[] deposits = client.sortDeposits();
        Assert.assertEquals(deposits[0], deposit3);
        Assert.assertEquals(deposits[1], deposit2);
        Assert.assertEquals(deposits[2], deposit1);
    }

    @Test
    public void testCountPossibleToProlongDeposits() {
        Deposit longProlongable = new LongDeposit(new BigDecimal(200.00), 35);
        Deposit longNotProlongable = new LongDeposit(new BigDecimal(400.00), 37);
        Deposit base = new BaseDeposit(new BigDecimal(1000.00), 3);
        Deposit specialProlongable = new SpecialDeposit(new BigDecimal(3000.00), 4);
        Deposit specialNotProlongable = new SpecialDeposit(new BigDecimal(900.00), 4);
        Client client = new Client();
        client.addDeposit(longProlongable);
        client.addDeposit(longNotProlongable);
        client.addDeposit(base);
        client.addDeposit(specialProlongable);
        client.addDeposit(specialNotProlongable);
        Assert.assertEquals(client.countPossibleToProlongDeposit(), 2);
    }

    @Test
    public void testClientIterator() {
        Deposit deposit1 = new LongDeposit(new BigDecimal(200.00), 7);
        Deposit deposit2 = new BaseDeposit(new BigDecimal(1000.00), 3);
        Client client = new Client();
        client.addDeposit(deposit1);
        client.addDeposit(deposit2);
        int i = 0;
        for (Deposit deposit : client) {
            if (i == 0) {
                Assert.assertEquals(deposit, deposit1);
            } else if (i == 1) {
                Assert.assertEquals(deposit, deposit2);
            }
            i++;
        }
        Assert.assertEquals(i, 2);
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void testNextThrowNoSuchElementException() {
        Client client = new Client();
        Client.DepositIterator iterator = client.new DepositIterator();
        iterator.next();
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void testHasNextThrowNoSuchElementException() {
        Client client = new Client();
        Client.DepositIterator iterator = client.new DepositIterator();
        for (int i = 0; i < 10; i++) {
            iterator.next();
        }
        iterator.hasNext();
    }
}

