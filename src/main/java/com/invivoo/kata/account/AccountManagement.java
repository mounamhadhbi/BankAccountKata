package com.invivoo.kata.account;

import com.invivoo.kata.Amount;

import java.time.LocalDateTime;

public class AccountManagement {

    /**
     * Make Deposit in account at a specific date
     *
     * @param account         user account
     * @param amount          amount to depose
     * @param transactionDate date of transaction
     */
    public static void makeDeposit(Account account, Amount amount, LocalDateTime transactionDate) {
        account.deposit(amount, transactionDate);
    }

    /**
     * Make Deposit in account at asofday date
     *
     * @param account user account
     * @param amount  amount to depose
     */
    public static void makeDeposit(Account account, Amount amount) {
        account.deposit(amount);
    }

    /**
     * Make a withdrawal from account at a specific date
     *
     * @param account         user account
     * @param amount          amount to withdrawal
     * @param transactionDate transaction date
     */
    public static void makeWithdrawal(Account account, Amount amount, LocalDateTime transactionDate) {
        account.withdrawal(amount, transactionDate);
    }

    /**
     * Make a withdrawal from account at asofday date
     *
     * @param account user account
     * @param amount  amount to withdrawal
     */
    public static void makeWithdrawal(Account account, Amount amount) {
        account.withdrawal(amount);
    }

    /**
     * Calculate balance at asofday date of account
     *
     * @param account user account
     * @return balance
     */
    public static Amount balanceOf(Account account) {
        return account.getBalance();
    }

    /**
     * Print the history of operations
     *
     * @param account user account
     * @return history of operations
     */
    public static String printAllHistory(Account account) {
        return account.printHistory();
    }
}
