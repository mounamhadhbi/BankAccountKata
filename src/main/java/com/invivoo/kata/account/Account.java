package com.invivoo.kata.account;

import com.invivoo.kata.helper.AccountStatement;
import com.invivoo.kata.helper.AccountStatements;
import com.invivoo.kata.helper.Amount;

import java.util.Currency;
import java.util.Objects;

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

    public void deposit(Amount depositAmount) {
        assert depositAmount != null : "Deposit Amount cannot be empty.";
        assert depositAmount.doubleValue() > 0 : "Deposit Amount must be greater than 0";
        assert Objects.equals(depositAmount.getCurrency(), this.currency) : "Deposit Amount must be on the some currency of account";

        this.balance = this.balance.add(depositAmount);

        this.accountStatements.addStatement(AccountStatement.ofDeposit(depositAmount));

    }
    public void withdrawal(Amount withdrawalAmount) {
        assert withdrawalAmount != null : "Deposit Amount cannot be empty.";
        assert withdrawalAmount.doubleValue() > 0 : "Deposit Amount must be greater than 0";
        assert Objects.equals(withdrawalAmount.getCurrency(), this.currency) : "Deposit Amount must be on the some currency of account";
        assert balance.doubleValue() >= withdrawalAmount.doubleValue() : "Balance is insufficient to make this operation";

        this.balance = this.balance.substruct(withdrawalAmount);

        this.accountStatements.addStatement(AccountStatement.ofWithdrawal(withdrawalAmount));


    }

    public Amount getBalance() {
        return balance;
    }

    public String print(){
        return "** Account History **\n"
        + accountStatements.print();
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
}
