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
        if (request.getSession().getAttribute("cart") == null) {
            Cart cart = new Cart();
            // Этот кусок кода используется для теста корзины
//                cart.setId(1L);
//                CartItem cartItem = new CartItem();
//                CartItem cartItem1 = new CartItem();
//                CartItem cartItem2 = new CartItem();
//                SellerProduct sellerProduct = new SellerProduct();
//                SellerProduct sellerProduct1 = new SellerProduct();
//                Product product = Product.builder().name("product").description("product").category("product").build();
//                Shop shop = new Shop();
//                Shop shop1 = new Shop();
//                shop1.setId(2L);
//                shop1.setTitle("shop1");
//                shop1.setDescription("shop1");
//                shop.setId(1L);
//                shop.setTitle("shop");
//                shop.setDescription("shop");
//                sellerProduct.setProduct(product);
//                sellerProduct1.setProduct(product);
//                sellerProduct.setShop(shop);
//                sellerProduct1.setShop(shop1);
//                sellerProduct.setPrice(new BigDecimal("120.00"));
//                sellerProduct1.setPrice(new BigDecimal("110.00"));
//                cartItem.setSellerProduct(sellerProduct);
//                cartItem.setQuantity(1);
//                cartItem.setId(1L);
//                cartItem1.setId(2L);
//                cartItem2.setId(3L);
//                cartItem1.setQuantity(1);
//                cartItem1.setSellerProduct(sellerProduct);
//                cartItem2.setQuantity(1);
//                cartItem2.setSellerProduct(sellerProduct1);
//                cart.setProducts(new ArrayList<>());
//                cart.getProducts().add(cartItem);
//                cart.getProducts().add(cartItem1);
//                cart.getProducts().add(cartItem2);
            request.getSession().setAttribute("cart", cart);
        }
        return true;
    }
}
