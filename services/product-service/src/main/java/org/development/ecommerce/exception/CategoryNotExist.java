package org.development.ecommerce.exception;

public class CategoryNotExist extends IllegalArgumentException {
    public CategoryNotExist(String message) {
        super(message);
    }
}
