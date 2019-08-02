package com.mainacad.dao;

import com.mainacad.model.Order;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class OrderDAOTest {

    private static List<Order> orders = new ArrayList<>();

    @BeforeEach
    void setUp() {
        Order order = new Order(1, 3, 2);
        orders.add(order);
    }

    @AfterEach
    void tearDown() {
        for (Order order : orders) {
            if (order.getId() != null)
                OrderDAO.delete(order.getId());
        }
    }

    @Test
    void create() {

        assertNull(orders.get(0).getId());
        Order orderInDB = OrderDAO.create(orders.get(0));

        assertNotNull(orderInDB);
        assertNotNull(orderInDB.getId());

        Order checkedOrderInDB = OrderDAO.findById(orderInDB.getId());
        assertNotNull(checkedOrderInDB);

        List<Order> checkedOrderInDBByCart = OrderDAO.findByCart(orderInDB.getCartId());
        assertNotNull(checkedOrderInDBByCart);

        OrderDAO.delete(checkedOrderInDB.getId());
        Order deletedUser = OrderDAO.findById(orderInDB.getId());
        assertNull(deletedUser);
    }
}