package com.company.user.exception;

public class UserException extends RuntimeException{
    public UserException(String test){
        super("Fill in the parameter and provide the text here." + test);
    }
}
