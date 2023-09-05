package com.example.rentingsystem.Api;

public class ApiException extends RuntimeException{
    public ApiException(String message){
        super(message);
    }
}
