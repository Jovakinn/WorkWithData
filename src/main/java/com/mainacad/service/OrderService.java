package com.mainacad.service;

import com.mainacad.dao.CartDAO;
import com.mainacad.dao.OrderDAO;
import com.mainacad.model.Cart;
import com.mainacad.model.Item;
import com.mainacad.model.Order;
import com.mainacad.model.User;

import java.util.List;

public class OrderService {

    public static Order createOrderByItemAndUser(Item item, Integer amount, User user) {
        Order order = new Order();

        order.setItemId(item.getId());
        order.setAmount(amount);
        // get or create open cart

        Cart cart = CartService.findOpenCartByUser(user.getId());
        if (cart == null) {
            cart = CartService.createCartForUser(user.getId());
        }
        order.setCartId(cart.getId());
        return OrderDAO.create(order);
    }

    public static List<Order> getOrdersByCard(Cart cart){
        return OrderDAO.findByCard(cart.getId());
    }

    public static List<Order> findClosedOrdersByUserAndPeriod(User user, Long from, Long to){
        return OrderDAO.findClosedOrdersByUserAndPeriod(user.getId(), from, to);
    }
}
