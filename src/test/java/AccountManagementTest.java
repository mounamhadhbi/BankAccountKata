import com.invivoo.kata.Amount;
import com.invivoo.kata.account.Account;
import com.invivoo.kata.account.AccountManagement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.Currency;

import static com.invivoo.kata.account.AccountManagement.*;

public class AccountManagementTest {

    private static Currency USD = Currency.getInstance("USD");
    private static Currency EUR = Currency.getInstance("EUR");
    private static Amount HUNDRED_DOLLAR = Amount.of(100, USD);
    private static Amount HUNDRED_EUR = Amount.of(100, EUR);
    private static Amount TWO_HUNDRED_DOLLAR = Amount.of(200, USD);


    @Test
    public void depositTestOk() {
        final Account account = new Account(1234L, USD);
        makeDeposit(account, HUNDRED_DOLLAR);
        Assert.assertEquals(balanceOf(account), HUNDRED_DOLLAR);
        makeDeposit(account, HUNDRED_DOLLAR);
        Assert.assertEquals(balanceOf(account), TWO_HUNDRED_DOLLAR);
    }


    @Test(expectedExceptions = AssertionError.class, expectedExceptionsMessageRegExp = "Transaction Amount must be on the some currency of account.")
    public void depositWithWrongCurrencyTestKo() {
        final Account account = new Account(1234L, USD);

        makeDeposit(account, HUNDRED_EUR);
    }

    @Test(expectedExceptions = AssertionError.class, expectedExceptionsMessageRegExp = "Transaction Amount cannot be empty.")
    public void depositWithEmptyAmountTestKo() {
        final Account account = new Account(1234L, USD);

        makeDeposit(account, null);
    }


    @Test(expectedExceptions = AssertionError.class, expectedExceptionsMessageRegExp = "Transaction Amount must be greater than 0.")
    public void depositWithNegativeAmountTestKo() {
        final Account account = new Account(1234L, USD);

        makeDeposit(account, HUNDRED_EUR.negate());
    }


    @Test
    public void withdrawalTestOk() {
        final Account account = new Account(1234L, USD);
        makeDeposit(account, TWO_HUNDRED_DOLLAR);
        makeWithdrawal(account, HUNDRED_DOLLAR);
        Assert.assertEquals(balanceOf(account), HUNDRED_DOLLAR);
        makeWithdrawal(account, HUNDRED_DOLLAR);
        Assert.assertEquals(balanceOf(account), Amount.zeroOf(USD));
    }


    @Test(expectedExceptions = AssertionError.class, expectedExceptionsMessageRegExp = "Transaction Amount must be on the some currency of account.")
    public void withdrawalWithWrongCurrencyTestKo() {
        final Account account = new Account(1234L, USD);

        makeWithdrawal(account, HUNDRED_EUR);
    }

    @Test(expectedExceptions = AssertionError.class, expectedExceptionsMessageRegExp = "Transaction Amount cannot be empty.")
    public void withdrawalWithEmptyAmountTestKo() {
        final Account account = new Account(1234L, USD);

        makeWithdrawal(account, null);
    }


    @Test(expectedExceptions = AssertionError.class, expectedExceptionsMessageRegExp = "Transaction Amount must be greater than 0.")
    public void withdrawalWithNegativeAmountTestKo() {
        final Account account = new Account(1234L, USD);

        makeWithdrawal(account, HUNDRED_EUR.negate());
    }

    @Test(expectedExceptions = AssertionError.class, expectedExceptionsMessageRegExp = "Balance is insufficient to make this operation.")
    public void withdrawalWithInsufficientBalanceAmountTestKo() {
        final Account account = new Account(1234L, USD);

        makeWithdrawal(account, TWO_HUNDRED_DOLLAR);
    }

    @Test
    public void printHistoryTestOk() {
        final Account account = new Account(1234L, USD);
        makeDeposit(account, TWO_HUNDRED_DOLLAR, LocalDateTime.parse("2007-08-03T10:15:30"));
        makeDeposit(account, HUNDRED_DOLLAR, LocalDateTime.parse("2007-09-03T10:15:40"));
        makeWithdrawal(account, HUNDRED_DOLLAR, LocalDateTime.parse("2007-10-03T10:16:30"));
        makeDeposit(account, HUNDRED_DOLLAR, LocalDateTime.parse("2007-11-03T10:18:30"));
        makeWithdrawal(account, HUNDRED_DOLLAR, LocalDateTime.parse("2007-12-03T10:15:45"));
        Assert.assertEquals(AccountManagement.printAllHistory(account),
                "** Account (1234) History **\n" +
                        "DEPOSIT | 2007-08-03T10:15:30 | 200.0 USD\n" +
                        "DEPOSIT | 2007-09-03T10:15:40 | 100.0 USD\n" +
                        "WITHDRAWAL | 2007-10-03T10:16:30 | 100.0 USD\n" +
                        "DEPOSIT | 2007-11-03T10:18:30 | 100.0 USD\n" +
                        "WITHDRAWAL | 2007-12-03T10:15:45 | 100.0 USD\n" +
                        "BALANCE = 200.0 USD"
        );
    }


}
