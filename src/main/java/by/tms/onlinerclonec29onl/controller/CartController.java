package by.tms.onlinerclonec29onl.controller;

import by.tms.onlinerclonec29onl.model.Cart;
import by.tms.onlinerclonec29onl.model.CartItem;
import by.tms.onlinerclonec29onl.model.Shop;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping("/cart")
public class CartController {

    @GetMapping()
    public String showCart(HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        List<CartItem> cartItems = cart.getProducts();
        if (cartItems.isEmpty()) {
            return "cart";
        }
        Map<Long, BigDecimal> shopsId = new HashMap<>();
        HashSet<Shop> shops = new HashSet<>();
        for (CartItem cartItem : cartItems) {
            Long shopId = cartItem.getSellerProduct().getShop().getId();
            cartItem.setAmount(cartItem.getSellerProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())));
            shopsId.put(shopId, BigDecimal.ZERO);
        }
        for (CartItem cartItem : cartItems) {
            for (Map.Entry<Long, BigDecimal> entry : shopsId.entrySet()) {
                if (cartItem.getSellerProduct().getShop().getId().equals(entry.getKey())) {
                    shops.add(cartItem.getSellerProduct().getShop());
                }
                if (cartItem.getSellerProduct().getShop().getId().equals(entry.getKey())) {
                    entry.setValue(entry.getValue().add(cartItem.getAmount()));
                }
            }
        }
        cart.setProducts(cartItems);
        session.setAttribute("cart", cart);
        session.setAttribute("shops", shops);
        session.setAttribute("shopsId", shopsId);
        return "cart";
    }

    @PostMapping()
    public String updateCart(@RequestParam("quantity") int quantity, @RequestParam("cartItemId") Long cartItemId, HttpSession session, Model model) {
        Cart cart = (Cart) session.getAttribute("cart");
        Map<Long, BigDecimal> shopsId = (Map<Long, BigDecimal>) session.getAttribute("shopsId");
        List<CartItem> cartItems = new ArrayList<>(cart.getProducts());
        for (CartItem c : cartItems) {
            Long cartId = c.getId();
            for (Map.Entry<Long, BigDecimal> entry : shopsId.entrySet()) {
                if (cartId.equals(cartItemId) && c.getSellerProduct().getShop().getId().equals(entry.getKey())) {
                    c.setQuantity(quantity);
                    entry.setValue(entry.getValue().subtract(c.getAmount()));
                    c.setAmount(BigDecimal.valueOf(c.getSellerProduct().getPrice().doubleValue() * c.getQuantity()));
                    entry.setValue(entry.getValue().add(c.getAmount()));
                }
            }
        }
        cart.setProducts(cartItems);
        session.setAttribute("cart", cart);
        return "cart";
    }

    @PostMapping("/delete")
    public String deleteItem(@RequestParam("cartItemId") Long cartItemId, HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        List<CartItem> cartItems = cart.getProducts();
        cartItems.removeIf(cartItem -> cartItem.getId().equals(cartItemId));
        cart.setProducts(cartItems);
        session.setAttribute("cart", cart);
        return "redirect:/cart";
    }
}
