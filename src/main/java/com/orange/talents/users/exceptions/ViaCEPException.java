package com.orange.talents.users.exceptions;

public class ViaCEPException extends RuntimeException{
    public static final String MESSAGE = "ViaCEP API appears to be out of service";

    public ViaCEPException() {
        super(MESSAGE);
    }
}
