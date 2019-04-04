package com.invivoo.kata.helper;

import org.springframework.format.Formatter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.StringJoiner;

import static com.invivoo.kata.helper.Operation.*;
import static java.time.LocalDate.*;

public class AccountStatement {

    private final Operation operation;

    private final LocalDate statementDate;

    private final Amount amount;

    public AccountStatement(Operation operation, LocalDate statementDate, Amount amount) {
        this.operation = operation;
        this.statementDate = statementDate;
        this.amount = amount;
    }

    public Operation getOperation() {
        return operation;
    }

    public LocalDate getStatementDate() {
        return statementDate;
    }

    public Amount getAmount() {
        return amount;
    }

    public static AccountStatement ofDeposit(Amount amount){
        return new AccountStatement(DEPOSIT, now(), amount);
    }

    public static AccountStatement ofWithdrawal(Amount amount){
        return new AccountStatement(WITHDRAWAL, now(), amount);
    }

    public String print(){
        return new StringJoiner(" | ")
                .add(operation.name())
                .add(statementDate.format(DateTimeFormatter.BASIC_ISO_DATE))
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
}
