package org.example.shoppingcartspringboot.controller;

import jakarta.servlet.http.HttpSession;
import org.example.shoppingcartspringboot.model.Cart;
import org.example.shoppingcartspringboot.model.CartItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Locale;

@Controller
public class CartController {
    private static final String CART_SESSION_KEY = "cart";

    @GetMapping("/cart")
    public String showCart(Model model, HttpSession session) {
        Cart cart = (Cart)session.getAttribute(CART_SESSION_KEY);

        if (cart == null) {
            cart = new Cart();
            session.setAttribute(CART_SESSION_KEY, cart);
        }

        session.setMaxInactiveInterval(30);

        model.addAttribute("items", cart.getItems());
        model.addAttribute("total", cart.getTotal());

        return "cart";
    }

    @PostMapping("cart/add")
    public String addItemToCart(@RequestParam String name,
                                @RequestParam double price,
                                @RequestParam int quantity,
                                HttpSession session) {

        CartItem item = new CartItem(name, price, quantity);

        Cart cart = (Cart)session.getAttribute(CART_SESSION_KEY);

        if (cart == null) {
            cart = new Cart();
            session.setAttribute(CART_SESSION_KEY, cart);
        }

        cart.addItem(item);

        return "redirect:/cart";
    }

    @PostMapping("/cart/remove")
    public String removeFromCart(@RequestParam int index, HttpSession session) {
        Cart cart = (Cart)session.getAttribute(CART_SESSION_KEY);

        if (cart == null) {
            return "redirect:/cart";
        }

        cart.removeItem(index);

        return "redirect:/cart";
    }

    @PostMapping("/cart/empty")
    public String emptyCart(HttpSession session) {
        session.removeAttribute(CART_SESSION_KEY);

        return "redirect:/cart";
    }
}
