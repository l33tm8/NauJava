package ru.ilya.NauJava.exception;

public class UserAlreadyExistsException extends java.lang.Exception {
    public UserAlreadyExistsException() {
        super("User already exists");
    }
}
