package com.fabricode.customer.Exception;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(){
        super("Customer not found.");
    }

    public CustomerNotFoundException(Integer id) {
        super (
                String.format("Customer with ID: %d not found.", id)
        );
    }
}
