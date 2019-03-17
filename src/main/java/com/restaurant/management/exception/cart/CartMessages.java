package com.restaurant.management.exception.cart;

public enum  CartMessages {
    CART_NOT_FOUND("First add products to cart. "),
    CART_DELETED("Cart deleted. "),
    CART_NOT_REGISTER("Please first open cart for customer"),
    CART_EXISTS("SessionCart already register for customer. You can add products. "),
    CART_OPENED_EMPTY("There is no opened customer carts. "),
    CART_CLOSED_EMPTY("There is no closed customer carts. "),
    CART_IS_CLOSED("SessionCart is closed cannot be deleted"),
    PRODUCT_ALREADY_IN_CART("Product is already in cart. "),
    NOT_ENOUGH_AT_CART("Incorrect quantity request. "),
    CART_UNIQUE_ID_NOT_FOUND("SessionCart with provided unique id not found. ");

    private String errorMessage;

    CartMessages(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * @return the errorMessage
     */
    public String getMessage() {
        return errorMessage;
    }

    /**
     * @param errorMessage the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}