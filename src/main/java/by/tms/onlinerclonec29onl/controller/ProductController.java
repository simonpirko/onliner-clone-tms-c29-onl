package by.tms.onlinerclonec29onl.controller;

import by.tms.onlinerclonec29onl.dao.ProductDao;
import by.tms.onlinerclonec29onl.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {

    private final ProductDao productDao;

    public ProductController(ProductDao productDao) {
        this.productDao = productDao;
    }

    @GetMapping("/")
    public String getProducts(Model model) {
        List<Product> products = productDao.getAll();
        model.addAttribute("products", products);

        return "index";
    }

    @PostMapping("/add-to-cart")
    public String addToCart(@RequestParam("productId") Integer productId, Model model) {
        Product product = productDao.getById(productId).get();
        productDao.delete(product);
        return "redirect:/";

    }
}
