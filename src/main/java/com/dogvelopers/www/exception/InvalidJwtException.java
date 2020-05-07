package com.dogvelopers.www.exception;

public class InvalidJwtException extends RuntimeException{
    public InvalidJwtException(String msg){
        super(msg);
    }
}
