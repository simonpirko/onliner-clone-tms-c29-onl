package by.tms.onlinerclonec29onl;

import by.tms.onlinerclonec29onl.model.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.math.BigDecimal;
import java.util.ArrayList;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cart cart = new Cart();
        cart.setId(1L);
        CartItem cartItem = new CartItem();
        CartItem cartItem1 = new CartItem();
        SellerProduct sellerProduct = new SellerProduct();
        Product product = Product.builder().name("product").description("product").category("product").build();
        Shop shop = new Shop();
        shop.setTitle("shop");
        shop.setDescription("shop");
        sellerProduct.setProduct(product);
        sellerProduct.setShop(shop);
        sellerProduct.setPrice(new BigDecimal("120.00"));
        cartItem.setSellerProduct(sellerProduct);
        cartItem.setQuantity(1);
        cartItem1.setQuantity(1);
        cartItem1.setSellerProduct(sellerProduct);
        cart.setProducts(new ArrayList<>());
        cart.getProducts().add(cartItem);
        cart.getProducts().add(cartItem1);
        request.getSession().setAttribute("cart", cart);
        if (request.getSession().getAttribute("account") == null && (
                request.getRequestURL().toString().contains("profile") ||
                        request.getRequestURL().toString().contains("logout"))) {
            response.sendRedirect("/");
            return false;
        }
        if (request.getSession().getAttribute("account") != null && (
                request.getRequestURL().toString().contains("registration") ||
                        request.getRequestURL().toString().contains("login"))) {
            response.sendRedirect("/");
            return false;
        }
        return true;
    }
}
