package com.orange.talents.users.exceptions;

import java.time.format.DateTimeParseException;

public class InvalidDateFormatException extends DateTimeParseException {
    private static final String MESSAGE = "Date format is invalid. Pattern should be %s";

    public InvalidDateFormatException(String pattern, CharSequence parsedData, int errorIndex) {
        super(String.format(MESSAGE, pattern), parsedData, errorIndex);
    }
}
