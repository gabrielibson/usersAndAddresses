package com.orange.talents.users.exceptions;

import java.util.NoSuchElementException;

public class ElementNotFoundException extends NoSuchElementException {

    private static final String MESSAGE = "User not found for id %s";

    public ElementNotFoundException(Long id) {
        super(String.format(MESSAGE, id));
    }
}
