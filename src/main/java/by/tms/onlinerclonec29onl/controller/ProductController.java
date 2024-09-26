package by.tms.onlinerclonec29onl.controller;

import by.tms.onlinerclonec29onl.model.Cart;
import by.tms.onlinerclonec29onl.model.CartItem;
import by.tms.onlinerclonec29onl.model.SellerProduct;
import by.tms.onlinerclonec29onl.service.SellerProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("product")
public class ProductController {
    @Autowired
    SellerProductService sellerProductService;

    @GetMapping("/{id}")
    public String getOffers(@PathVariable("id") Long id, Model model) {
        List<SellerProduct> sellerProductList = sellerProductService.getSellerProductByProductId(id);
        model.addAttribute("sellerProductList", sellerProductList);
        return "product";
    }

    @PostMapping("/put")
    public String putToCart(@RequestParam("sellerProductId") Long id, HttpSession session, Model model) {
        Cart cart = (Cart) session.getAttribute("cart");
        List<CartItem> cartItems = cart.getProducts();
        CartItem cartItem = new CartItem();

        Optional<SellerProduct> sellerProduct = sellerProductService.getById(id);

        if (sellerProduct.isPresent()) {
            cartItem.setSellerProduct(sellerProduct.get());
            cartItem.setQuantity(1);
            cartItems.add(cartItem);
            cart.setProducts(cartItems);

            session.setAttribute("cart", cart);
        }
        
        return "redirect:/cart";
    }
}
