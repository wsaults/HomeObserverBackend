package com.saults.HomeObserverBackend.Controllers;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(Long id) {
        super("Unable to find user for id: " + id);
    }
}
