package com.epam.assertion;

import static com.epam.constant.GeneralConstants.CART_IS_EMPTY;
import static org.testng.Assert.assertEquals;

public class CartAssertion {
    public static void assertCartIsEmpty(String header){
        assertEquals(CART_IS_EMPTY, header, String.format("Cart is not empty. Expected header %s1 But found %s2", CART_IS_EMPTY, header));
    }
}
