package by.tms.onlinerclonec29onl.controller;

import by.tms.onlinerclonec29onl.model.Cart;
import by.tms.onlinerclonec29onl.model.CartItem;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/cart")
public class CartController {

    @GetMapping()
    public String showCart(HttpSession session, Model model) {
        Cart cart = (Cart) session.getAttribute("cart");
        List<CartItem> cartItems = cart.getProducts();
        Set<Long> shopsId = new HashSet<>();
        for(CartItem cartItem : cartItems) {
            Long shopId = cartItem.getSellerProduct().getShop().getId();
            shopsId.add(shopId);
        }
        model.addAttribute("shopIds", shopsId);
        model.addAttribute("cartItems", cartItems);
        return "cart";
    }

}
