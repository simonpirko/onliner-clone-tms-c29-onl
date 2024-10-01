package by.tms.onlinerclonec29onl.controller;


import by.tms.onlinerclonec29onl.model.Account;
import by.tms.onlinerclonec29onl.model.Cart;
import by.tms.onlinerclonec29onl.model.CartItem;
import by.tms.onlinerclonec29onl.model.Order;
import by.tms.onlinerclonec29onl.service.OrderItemService;
import by.tms.onlinerclonec29onl.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    private OrderItemService orderItemService;

//    @GetMapping()
//    public String ShowOrder(HttpSession session, Model model){
//       Cart cart = (Cart) session.getAttribute("cart");
//       List<CartItem> cartItems = cart.getProducts();
//       long shopId = 1L; /*временно, пока shopId не передается при вызове со страницы корзины*/
//
//        BigDecimal cartAmount;
//        cartAmount = BigDecimal.valueOf(0.00);
//
//        List<CartItem> cartItemCurrent = new ArrayList<>();
//        for (CartItem cartItem : cartItems) {
//            if (shopId == cartItem.getSellerProduct().getShop().getId()) {
//                cartItemCurrent.add(cartItem);
//                cartAmount = cartAmount.add(cartItem.getAmount());
//                model.addAttribute("shop", cartItem.getSellerProduct().getShop()); /*костыль, нужно получить магазин корректно*/
//            }
//        }
//        model.addAttribute("cartItems", cartItemCurrent);
//        model.addAttribute("cartAmount", cartAmount);
//
//        return "order";
//    }

    @PostMapping()
    public String confirmOrder(@RequestParam("delivery_address") String delivery_address, @RequestParam("cartItems") List<CartItem> cartItems, @RequestParam("cartAmount") BigDecimal cartAmount, HttpSession session, Model model) {

//        Order order = new Order();
//        order.setTotalPrice(cartAmount);
//        order.getStatus("OPEN");
//        order.setDelivery_address(delivery_address);
//        Account account = (Account) session.getAttribute("account");
//        order.setAccountId(account.getId());
//
//        orderService.createOrder(order);
//
//        long orderId = order.getId();
//        for (CartItem cartItem : cartItems) {
//            CartItem newCartItem = new CartItem();
//          //сюда дописать сохранение продуктов из заказа
//        }
//


        return "order";
    }

    @PostMapping("/shopOrder")
    public String conveyParamsToOrder(@RequestParam("shopId") long shopId, HttpSession session, Model model) {
        Cart cart = (Cart) session.getAttribute("cart");

        List<CartItem> cartItems = cart.getProducts();
        /*if (cartItems.isEmpty()) {
            return "cart";
        }*/
        BigDecimal cartAmount;
        cartAmount = BigDecimal.valueOf(0.00);

        List<CartItem> cartItemCurrent = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            if (shopId == cartItem.getSellerProduct().getShop().getId()) {
                cartItemCurrent.add(cartItem);
                cartAmount = cartAmount.add(cartItem.getAmount());
                model.addAttribute("shop", cartItem.getSellerProduct().getShop()); /*костыль, нужно получить магазин корректно*/
            }
        }

        model.addAttribute("cartItems", cartItemCurrent);
        model.addAttribute("cartAmount", cartAmount);

        Account account = (Account) session.getAttribute("account");
        model.addAttribute("accountName", account.getName());

        return "order";
    }
}
