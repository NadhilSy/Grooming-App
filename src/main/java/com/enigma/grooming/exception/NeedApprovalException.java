package com.enigma.grooming.exception;

public class NeedApprovalException extends RuntimeException{
    public NeedApprovalException() {
        super("Need approval");
    }

    public NeedApprovalException(String message) {
        super(message);
    }
}
