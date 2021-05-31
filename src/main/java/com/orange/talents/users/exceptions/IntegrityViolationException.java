package com.orange.talents.users.exceptions;

import org.springframework.dao.DataIntegrityViolationException;

public class IntegrityViolationException extends DataIntegrityViolationException {

    private static String MESSAGE = "%s already exists";

    public IntegrityViolationException(String field) {
        super(String.format(MESSAGE, field));
    }
}
