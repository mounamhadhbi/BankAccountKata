package com.invivoo.kata.account;

import com.invivoo.kata.Amount;

import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Objects;

import static com.invivoo.kata.DefaultConfig.AS_OF_DAY;
import static com.invivoo.kata.account.AccountStatement.ofDeposit;
import static com.invivoo.kata.account.AccountStatement.ofWithdrawal;
import static java.time.LocalDateTime.now;


public class Account {

    private final long id;

    private final Currency currency;

    private Amount balance;

    private AccountStatements accountStatements;

    public Account(long id, Currency currency) {
        this.id = id;
        this.currency = currency;
        this.balance = Amount.zeroOf(currency);
        this.accountStatements = new AccountStatements();
    }

    void deposit(Amount depositAmount) {
        deposit(depositAmount, AS_OF_DAY);

    }

    void deposit(Amount depositAmount, LocalDateTime transactionDate) {
        validateTransaction(depositAmount, transactionDate);

        this.balance = this.balance.add(depositAmount);

        this.accountStatements.addStatement(ofDeposit(depositAmount, transactionDate));

    }


    void withdrawal(Amount withdrawalAmount) {
        withdrawal(withdrawalAmount, now());
    }


    void withdrawal(Amount withdrawalAmount, LocalDateTime transactionDate) {
        validateTransaction(withdrawalAmount, transactionDate);
        assert balance.doubleValue() >= withdrawalAmount.doubleValue() : "Balance is insufficient to make this operation.";

        this.balance = this.balance.substruct(withdrawalAmount);

        this.accountStatements.addStatement(ofWithdrawal(withdrawalAmount, transactionDate));
    }

    Amount getBalance() {
        return balance;
    }

    private void validateTransaction(Amount depositAmount, LocalDateTime transactionDate) {
        assert depositAmount != null : "Transaction Amount cannot be empty.";
        assert transactionDate != null : "Transaction date must be defined";
        assert !transactionDate.isBefore(this.accountStatements.lastStatementsDate()) : "Date must be after the last transaction date.";
        assert depositAmount.doubleValue() > 0 : "Transaction Amount must be greater than 0.";
        assert Objects.equals(depositAmount.getCurrency(), this.currency) : "Transaction Amount must be on the some currency of account.";
    }


    String printHistory() {
        return "** Account (" + id + ") History **\n"
                + accountStatements.print()
                + "\nBALANCE = " + balance.toString();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return id == account.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", currency=" + currency +
                ", balance=" + balance +
                ", accountStatements=" + accountStatements +
                '}';
    }
}
