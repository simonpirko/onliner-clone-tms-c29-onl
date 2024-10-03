package by.tms.onlinerclonec29onl.controller;


import by.tms.onlinerclonec29onl.model.*;
import by.tms.onlinerclonec29onl.service.OrderItemService;
import by.tms.onlinerclonec29onl.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final List<CartItem> cartItemsCurrent = new ArrayList<>();

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;

    @PostMapping()
    public String confirmOrder(@RequestParam("deliveryAddress") String deliveryAddress, @RequestParam("cartAmount") BigDecimal cartAmount, HttpSession session) throws SQLException {

        Order order = new Order();
        order.setTotalPrice(cartAmount);
        order.setStatus(Order.OrderStatus.valueOf("OPEN"));
        order.setDeliveryAddress(deliveryAddress);
        Account account = (Account) session.getAttribute("account");
        order.setAccount(account);

        long orderId = orderService.createOrder(order);
        order.setId(orderId);

        Cart cart = (Cart) session.getAttribute("cart");
        List<CartItem> cartItems1 = cart.getProducts();


        for (CartItem cartItem : cartItemsCurrent) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(cartItem.getSellerProduct().getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getSellerProduct().getPrice());
            orderItemService.save(orderItem);
            cartItems1.remove(cartItem);
        }

        return "orderAdded";
    }

    @PostMapping("/shopOrder")
    public String conveyParamsToOrder(@RequestParam("shopId") long shopId, HttpSession session, Model model) {
        Cart cart = (Cart) session.getAttribute("cart");

        List<CartItem> cartItems = cart.getProducts();
        BigDecimal cartAmount;
        cartAmount = BigDecimal.valueOf(0.00);

        //List<CartItem> cartItemCurrent = new ArrayList<>();
        cartItemsCurrent.clear();
        for (CartItem cartItem : cartItems) {
            if (shopId == cartItem.getSellerProduct().getShop().getId()) {
                cartItemsCurrent.add(cartItem);
                cartAmount = cartAmount.add(cartItem.getAmount());
                model.addAttribute("shop", cartItem.getSellerProduct().getShop()); /*костыль, нужно получить магазин корректно*/
            }
        }

        model.addAttribute("cartItems", cartItemsCurrent);
        model.addAttribute("cartAmount", cartAmount);

        Account account = (Account) session.getAttribute("account");
        model.addAttribute("accountName", account.getName());

        return "order";
    }

}
