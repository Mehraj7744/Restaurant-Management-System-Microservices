package com.restaurant.order.exception;

public class MenuItemUnavailableException extends RuntimeException {

    public MenuItemUnavailableException(String message) {
        super(message);
    }

}