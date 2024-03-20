package org.example.shoppingcartspringboot.repository;

import org.example.shoppingcartspringboot.model.Cart;
import org.example.shoppingcartspringboot.model.CartItem;

import java.util.List;

public class CartRepository {
    private Cart cart;

    public CartRepository() {
       this.cart = new Cart();
    }

    public List<CartItem> getItems() {
        return cart.getItems();
    }
}
