package com.invivoo.kata.account;

import com.invivoo.kata.Amount;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.StringJoiner;

import static com.invivoo.kata.account.Operation.DEPOSIT;
import static com.invivoo.kata.account.Operation.WITHDRAWAL;

/**
 * Account statement
 */
public class AccountStatement {

    private final Operation operation;

    private final LocalDateTime statementDate;

    private final Amount amount;

    private AccountStatement(Operation operation, LocalDateTime statementDate, Amount amount) {
        this.operation = operation;
        this.statementDate = statementDate;
        this.amount = amount;
    }

    public Operation getOperation() {
        return operation;
    }

    static AccountStatement ofDeposit(Amount amount, LocalDateTime transactionDate) {
        return new AccountStatement(DEPOSIT, transactionDate, amount);
    }

    static AccountStatement ofWithdrawal(Amount amount, LocalDateTime transactionDate) {
        return new AccountStatement(WITHDRAWAL, transactionDate, amount);
    }

    public Amount getAmount() {
        return amount;
    }

    LocalDateTime getStatementDate() {
        return statementDate;
    }

    String print() {
        return new StringJoiner(" | ")
                .add(operation.name())
                .add(statementDate.format(DateTimeFormatter.ISO_DATE_TIME))
                .add(amount.toString())
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountStatement)) return false;
        AccountStatement that = (AccountStatement) o;
        return operation == that.operation &&
                Objects.equals(statementDate, that.statementDate) &&
                Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operation, statementDate, amount);
    }

    @Override
    public String toString() {
        return "AccountStatement{" +
                "operation=" + operation +
                ", statementDate=" + statementDate +
                ", amount=" + amount +
                '}';
    }
}
