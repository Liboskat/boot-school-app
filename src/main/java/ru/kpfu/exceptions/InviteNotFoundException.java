package ru.kpfu.exceptions;

public class InviteNotFoundException extends RuntimeException {
    private String msg;

    public InviteNotFoundException() {
        super();
    }

    @Override
    public String getMessage() {
        return msg;
    }

    public InviteNotFoundException(String s) {
        msg = s;
    }
}
