package com.invivoo.kata.account;


import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.invivoo.kata.DefaultConfig.AS_OF_DAY;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.joining;

/**
 * List of Account statement
 */
public class AccountStatements implements Iterable<AccountStatement> {

    private List<AccountStatement> statements;

    AccountStatements() {
        this.statements = new ArrayList<>();
    }

    private AccountStatements(List<AccountStatement> statements) {
        this.statements = new ArrayList<>(statements);
    }

    @Override
    public Iterator<AccountStatement> iterator() {
        return statements.iterator();
    }

    public List<AccountStatement> toList() {
        return statements;
    }

    private Stream<AccountStatement> stream() {
        return statements.stream();
    }

    AccountStatements addStatement(AccountStatement statement) {
        this.statements.add(statement);
        return this;
    }

    public AccountStatements at(LocalDateTime at) {
        assert (at != null);
        return new AccountStatements(stream().filter(s -> Objects.equals(s.getStatementDate(), at)).collect(Collectors.toList()));
    }

    public AccountStatements before(LocalDateTime date) {
        assert (date != null);
        return new AccountStatements(
                stream()
                        .filter(s -> s.getStatementDate().isBefore(date))
                        .collect(Collectors.toList())
        );
    }

    public AccountStatements beforeOrOn(LocalDateTime date) {
        assert (date != null);
        return new AccountStatements(
                stream()
                        .filter(s -> s.getStatementDate().isBefore(date) || s.getStatementDate().isEqual(date))
                        .collect(Collectors.toList())
        );
    }

    public AccountStatements after(LocalDateTime date) {
        assert (date != null);
        return new AccountStatements(
                stream()
                        .filter(s -> s.getStatementDate().isAfter(date))
                        .collect(Collectors.toList())
        );
    }

    public AccountStatements afterOrOn(LocalDateTime date) {
        assert (date != null);
        return new AccountStatements(
                stream()
                        .filter(s -> s.getStatementDate().isAfter(date) || s.getStatementDate().isEqual(date))
                        .collect(Collectors.toList())
        );
    }

    LocalDateTime lastStatementsDate() {
        return stream()
                .max(Comparator.comparing(AccountStatement::getStatementDate))
                .map(AccountStatement::getStatementDate)
                .orElse(AS_OF_DAY);
    }


    String print() {
        return stream()
                .sorted(comparing(AccountStatement::getStatementDate))
                .map(AccountStatement::print)
                .collect(joining("\n"));
    }

    @Override
    public String toString() {
        return "AccountStatements{" +
                "statements=" + statements +
                '}';
    }
}
