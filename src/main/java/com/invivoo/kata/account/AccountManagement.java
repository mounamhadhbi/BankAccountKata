package com.invivoo.kata.account;

import com.invivoo.kata.helper.Amount;

public class AccountManagement {


    public static void makeDeposit(Account account, Amount amount){
        account.deposit(amount);
    }

    public static void makeWithdrawal(Account account, Amount amount){
        account.withdrawal(amount);
    }

    public String seeHistory(Account account){
        return account.print();
    }
}
