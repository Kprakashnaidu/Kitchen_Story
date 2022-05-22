package com.kitchenstory.exceptions;

public class DishNotFoundException extends RuntimeException{

    public DishNotFoundException(String message) {
        super(message);
    }
}
