package com.mainacad.dao;

import com.mainacad.model.Cart;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class CartDAOTest {

    private static List<Cart> carts = new ArrayList<>();
    private static Date date = new Date();

    @BeforeEach
    void setUp() {
        Cart cart = new Cart(date.getTime(), false, 2);
        carts.add(cart);
    }

    @AfterEach
    void tearDown() {
        carts.stream().forEach(cart -> ItemDAO.delete(cart.getId()));
    }

    @Test
    void create() {

        assertNull(carts.get(0).getId());
        Cart cartInDB = CartDAO.create(carts.get(0));

        assertNotNull(cartInDB);
        assertNotNull(cartInDB.getId());

        Cart checkedCartInDBById =  CartDAO.findById(cartInDB.getId());
        assertNotNull(checkedCartInDBById);

        List<Cart> checkedCartInDBByUser =  CartDAO.findByUser(cartInDB.getUserId());
        assertNotNull(checkedCartInDBByUser);

        Cart checkedCartInDBByOpenCartAndUser = CartDAO.findOpenCartByUser(cartInDB.getUserId());
        assertNotNull(checkedCartInDBByOpenCartAndUser);
    }
}