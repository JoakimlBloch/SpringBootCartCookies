package org.example.shoppingcartspringboot.service;

import org.example.shoppingcartspringboot.model.CartItem;
import org.example.shoppingcartspringboot.repository.CartRepository;

import java.util.List;

public class CartService {
    CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public List<CartItem> getItems() {
        return cartRepository.getItems();
    }
}
