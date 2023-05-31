
package com.sg.vendingmachine.service;

public class ClassVendingMachineInventoryException extends Exception{
    public ClassVendingMachineInventoryException(String message) {
        super(message);
    }
    
    public ClassVendingMachineInventoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
