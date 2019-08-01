package com.mainacad.dao;

import com.mainacad.model.Cart;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class CartDAOTest {

    private static List<Cart> carts = new ArrayList<>();

    @BeforeEach
    void setUp() {
        Cart cart = new Cart((long) 1000000000, false, 4);
        carts.add(cart);
    }

    @AfterEach
    void tearDown() {
        for (Cart cart : carts) {
            if (cart.getId() != null)
                ItemDAO.delete(cart.getId());
        }
    }

    @Test
    void create() {
        assertNull(carts.get(0).getId());
        Cart cartInDB = CartDAO.create(carts.get(0));

        assertNotNull(cartInDB);
        assertNotNull(cartInDB.getId());

        Cart checkedCartInDBByUser =  CartDAO.findById(cartInDB.getId());
        assertNotNull(checkedCartInDBByUser);


    }
}