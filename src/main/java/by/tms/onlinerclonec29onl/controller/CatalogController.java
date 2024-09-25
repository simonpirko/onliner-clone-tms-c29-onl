package by.tms.onlinerclonec29onl.controller;

import by.tms.onlinerclonec29onl.model.Product;
import by.tms.onlinerclonec29onl.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/catalog")
public class CatalogController {
    @Autowired
    ProductService productService;

    @GetMapping("")
    public String getAllCategories(Model model) {
        List<String> categories = productService.getAllCategories();
        model.addAttribute("categories", categories);
        return "catalog";
    }

    @GetMapping("/{category}")
    public String getProductsByCategory(@PathVariable("category") String category, Model model) {
        List<String> categories = productService.getAllCategories();
        List<Product> products = productService.getByCategory(category);
        model.addAttribute("categories", categories);
        model.addAttribute("activeCategory", category);
        model.addAttribute("products", products);
        return "catalog";
    }

    @PostMapping("/search")
    public String search(@RequestParam("name") String name, Model model) {
        List<String> categories = productService.getAllCategories();
        List<Product> products = productService.getByName(name);
        model.addAttribute("categories", categories);
        model.addAttribute("products", products);
        model.addAttribute("productSearch", name);
        return "catalog";
    }
}
