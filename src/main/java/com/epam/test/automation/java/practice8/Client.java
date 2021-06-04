package com.epam.test.automation.java.practice8;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Client implements Iterable<Deposit> {
    private Deposit[] deposits;

    public Client() {
        this.deposits = new Deposit[10];
    }

    public Deposit[] getDeposits() {
        return deposits;
    }

    public boolean addDeposit(Deposit deposit) {
        boolean isAdded = false;
        if (deposit != null) {
            for (int i = 0; i < this.deposits.length; i++) {
                if (this.deposits[i] == null) {
                    this.deposits[i] = deposit;
                    isAdded = true;
                    break;
                }
            }
        }
        return isAdded;
    }

    public BigDecimal totalIncome() {
        BigDecimal totalIncome = BigDecimal.ZERO;
        for (int i = 0; i < deposits.length; i++) {
            if (this.deposits[i] != null) {
                totalIncome = deposits[i].income().add(totalIncome);
            }
        }
        return totalIncome;
    }

    public BigDecimal maxIncome() {
        BigDecimal max = BigDecimal.ZERO;
        for (int i = 0; i < deposits.length; i++) {
            if (this.deposits[i] != null) {
                BigDecimal currentIncome = this.deposits[i].income();
                if (currentIncome.compareTo(max) > 0) {
                    max = currentIncome;
                }
            }
        }
        return max;
    }

    public BigDecimal getIncomeByNumber(int number) {
        if (number >= deposits.length) {
            throw new IllegalArgumentException("No such element in array");
        }
        Deposit deposit = this.deposits[number];
        if (deposit == null) {
            return BigDecimal.ZERO;
        }
        return deposit.income();
    }

    public Deposit[] sortDeposits() {
        boolean needIteration = true;
        while (needIteration) {
            needIteration = false;
            for (int i = 1; i < deposits.length; i++) {
                if (deposits[i] == null) {
                    continue;
                }
                if (deposits[i].compareTo(deposits[i - 1]) > 0) {
                    Deposit tmp = deposits[i];
                    deposits[i] = deposits[i - 1];
                    deposits[i - 1] = tmp;
                    needIteration = true;
                }
            }
        }
        return this.deposits;
    }


    public int countPossibleToProlongDeposit() {
        int count = 0;
        for (int i = 0; i < deposits.length; i++) {
            if (deposits[i] instanceof Prolongable && ((Prolongable) deposits[i]).canToProlong()) {
                count++;
            }
        }
        return count;
    }

    class DepositIterator implements Iterator<Deposit> {
        private int currentDepositPosition = 0;

        @Override
        public boolean hasNext() {
            if (this.currentDepositPosition >= deposits.length) {
                throw new NoSuchElementException();
            } else  {
                return deposits[this.currentDepositPosition] != null;
            } 
        }

        @Override
        public Deposit next() {
            if (hasNext()) {
                return deposits[this.currentDepositPosition++];
            } else {
                throw new NoSuchElementException();
            }
        }
    }

    @Override
    public Iterator<Deposit> iterator() {
        return new DepositIterator();
    }
}


