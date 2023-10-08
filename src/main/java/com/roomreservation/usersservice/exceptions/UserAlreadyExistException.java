package com.roomreservation.usersservice.exceptions;

public class UserAlreadyExistException extends Exception {

    public UserAlreadyExistException() {

    }

    public UserAlreadyExistException(final String msg) {
        super(msg);
    }
}
