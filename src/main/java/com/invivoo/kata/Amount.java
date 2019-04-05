package com.invivoo.kata;

import java.util.Currency;
import java.util.Objects;

import static com.invivoo.kata.DefaultConfig.DEFAULT_CURRENCY;


public class Amount {

    private final double value;

    private final Currency currency;

    public static final Amount ZERO = zeroOf(DEFAULT_CURRENCY);

    private Amount(double value, Currency currency) {
        this.value = value;
        this.currency = currency;
    }

    public static Amount of(double value, Currency currency) {
        return new Amount(value, currency);
    }

    public static Amount zeroOf(Currency currency) {
        return of(0d, currency);
    }

    public static Amount of(double value) {
        return new Amount(value, DEFAULT_CURRENCY);
    }

    public double doubleValue() {
        return value;
    }

    public Amount add(Amount toAdd) {
        assert currency.equals(toAdd.currency) : "Amounts must be on the same currency";
        return of(doubleValue() + toAdd.doubleValue(), this.currency);
    }

    public Amount substruct(Amount toSubstruct) {
        assert currency.equals(toSubstruct.currency) : "Amounts must be on the same currency";
        return of(doubleValue() - toSubstruct.doubleValue(), this.currency);
    }

    public Amount negate() {
        return of(-doubleValue(), this.currency);
    }

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Amount)) return false;
        Amount amount = (Amount) o;
        return Double.compare(amount.value, value) == 0 &&
                Objects.equals(currency, amount.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, currency);
    }

    @Override
    public String toString() {
        return value + (currency != null ? " " + currency : "");
    }
}
