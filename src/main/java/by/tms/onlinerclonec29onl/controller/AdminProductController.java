package by.tms.onlinerclonec29onl.controller;


import by.tms.onlinerclonec29onl.model.Product;
import by.tms.onlinerclonec29onl.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("admin")
public class AdminProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("/products")
    public String showProductManagementPage(Model model) {

            model.addAttribute("products", productService.getAll());
            model.addAttribute("newProduct", new Product());
            return "adminProducts";
    }


    @PostMapping("/products/add")
    public String addProduct(
                             @RequestParam("name") String productName,
                             @RequestParam("description") String productDescription,
                             @RequestParam("category") String productCategory
                             ,@RequestParam("image") MultipartFile image
    ) throws IOException {

        Product product = new Product();

        product.setName(productName);
        product.setDescription(productDescription);
        product.setCategory(productCategory);
        if (!image.isEmpty()) {
            product.setImage(image.getBytes());
        }
        productService.save(product);
        return "redirect:/admin/products";
    }


    @PostMapping("/products/update")
    public String updateProduct(@RequestParam("id") Long id,
                                @RequestParam("name") String productName,
                                @RequestParam("description") String productDescription,
                                @RequestParam("category") String productCategory
                                ,@RequestParam("image") MultipartFile image
                                ) throws IOException {

        Optional<Product> productOpt = productService.getById(id);
        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            product.setName(productName);
            product.setDescription(productDescription);
            product.setCategory(productCategory);
            if (!image.isEmpty()) {
                product.setImage(image.getBytes());
            }
            productService.update(product);
        }
        return "redirect:/admin/products";
    }


    @PostMapping("/products/delete")
    public String deleteProduct(@RequestParam("id") Long productId) {
        Optional<Product> product = productService.getById(productId);
        if (product.isPresent()) {
            productService.delete(product.get());
        }
        return "redirect:/admin/products";
    }

}
