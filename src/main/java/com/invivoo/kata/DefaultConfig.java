package com.invivoo.kata;

import java.time.LocalDateTime;
import java.util.Currency;

public interface DefaultConfig {

    Currency DEFAULT_CURRENCY = Currency.getInstance("EUR");

    LocalDateTime AS_OF_DAY = LocalDateTime.parse("2001-04-05T10:15:45");
}

