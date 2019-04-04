package com.invivoo.kata.helper;


import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.*;
import static java.util.Comparator.*;
import static java.util.stream.Collectors.joining;

/**
 * Account statement
 */
public class AccountStatements implements Iterable<AccountStatement> {

    private List<AccountStatement> statements;

    public AccountStatements() {
        this.statements = emptyList();
    }

    public AccountStatements(List<AccountStatement> statements) {
        this.statements = statements;
    }

    @Override
    public Iterator<AccountStatement> iterator() {
        return statements.iterator();
    }

    public List<AccountStatement> toList() {
        return statements;
    }

    public Stream<AccountStatement> stream() {
        return statements.stream();
    }

    public AccountStatements addStatement(AccountStatement statement) {
        this.statements.add(statement);
        return this;
    }

    public AccountStatements at(LocalDate at) {
        assert (at != null);
        return new AccountStatements(stream().filter(s -> Objects.equals(s.getStatementDate(), at)).collect(Collectors.toList()));
    }

    public AccountStatements before(LocalDate date) {
        assert (date != null);
        return new AccountStatements(
                stream()
                        .filter(s -> s.getStatementDate().isBefore(date))
                        .collect(Collectors.toList())
        );
    }

    public AccountStatements beforeOrOn(LocalDate date) {
        assert (date != null);
        return new AccountStatements(
                stream()
                        .filter(s -> s.getStatementDate().isBefore(date) || s.getStatementDate().isEqual(date))
                        .collect(Collectors.toList())
        );
    }

    public AccountStatements after(LocalDate date) {
        assert (date != null);
        return new AccountStatements(
                stream()
                        .filter(s -> s.getStatementDate().isAfter(date))
                        .collect(Collectors.toList())
        );
    }

    public AccountStatements afterOrOn(LocalDate date) {
        assert (date != null);
        return new AccountStatements(
                stream()
                        .filter(s -> s.getStatementDate().isAfter(date) || s.getStatementDate().isEqual(date))
                        .collect(Collectors.toList())
        );
    }


    public String print() {
        return stream()
                .sorted(comparing(AccountStatement::getStatementDate))
                .map(AccountStatement::print)
                .collect(joining("\n"));
    }


}
