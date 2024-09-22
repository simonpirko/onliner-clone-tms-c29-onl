package by.tms.onlinerclonec29onl.controller;

import by.tms.onlinerclonec29onl.model.Account;
import by.tms.onlinerclonec29onl.model.Product;
import by.tms.onlinerclonec29onl.model.SellerProduct;
import by.tms.onlinerclonec29onl.model.Shop;
import by.tms.onlinerclonec29onl.service.ProductService;
import by.tms.onlinerclonec29onl.service.SellerProductService;
import by.tms.onlinerclonec29onl.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/shop/products")
public class SellerProductController {

    @Autowired
    private SellerProductService sellerProductService;

    @Autowired
    private ShopService shopService;

    @Autowired
    private ProductService productService;

    @GetMapping
    public String listProducts(Model model, @SessionAttribute("account") Account account) {
        Optional<Shop> shop = shopService.getShopByCreatorId(account.getId());
        if (shop.isPresent()) {
            List<SellerProduct> sellerProducts = sellerProductService.getAllProductsByShop(shop.get().getId());
            List<Product> allMainProducts = productService.getAll();
            model.addAttribute("sellerProducts", sellerProducts);
            model.addAttribute("shopId", shop.get().getId());
            model.addAttribute("allMainProducts", allMainProducts);
        }
        return "shopProducts";
    }

    @PostMapping("/update")
    public String updateProductPrice(@RequestParam Long sellerProductId, @RequestParam BigDecimal price, @SessionAttribute("account") Account account) {
        Optional<Shop> shop = shopService.getShopByCreatorId(account.getId());
        if (shop.isPresent()) {
            SellerProduct sellerProduct = new SellerProduct();
            sellerProduct.setId(sellerProductId);
            sellerProduct.setPrice(price);
            sellerProductService.update(sellerProduct);
        }
        return "redirect:/shopProducts";
    }

    @PostMapping("/delete")
    public String deleteProduct(@RequestBody SellerProduct sellerProduct, @SessionAttribute("account") Account account) {
        Optional<Shop> shop = shopService.getShopByCreatorId(account.getId());
        if (shop.isPresent()) {
            sellerProductService.delete(sellerProduct);    //TODO  передается айди, а надо объект
        }
        return "redirect:/shopProducts";
    }

    @PostMapping("/add")
    public String addProductToShop(@RequestParam Long productId, @RequestParam BigDecimal price, @SessionAttribute("account") Account account) {
        Optional<Shop> shop = shopService.getShopByCreatorId(account.getId());
        Optional<Product> mainProduct = productService.getById(productId);
        if (shop.isPresent() && mainProduct.isPresent()) {
            SellerProduct sellerProduct = new SellerProduct();
            sellerProduct.setProduct(mainProduct.get());
            sellerProduct.setShop(shop.get());
            sellerProduct.setPrice(price);
            sellerProductService.save(sellerProduct);
        }
        return "redirect:/shopProducts";
    }

}

