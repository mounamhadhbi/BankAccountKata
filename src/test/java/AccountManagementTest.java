import com.invivoo.kata.account.Account;
import com.invivoo.kata.helper.Amount;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Currency;

public class AccountManagementTest {

    Account account;
    Currency currency;

    @BeforeClass
    void init() {
        currency = Currency.getInstance("USD");
        account = new Account(1234L, currency);
    }


    @Test
    void depositTest() {

        Amount centDollard = Amount.of(100, currency);
        account.deposit(centDollard);
        Assert.assertEquals(account.getBalance(), centDollard);
        Assert.assertEquals(account.print(), "");

    }
}
