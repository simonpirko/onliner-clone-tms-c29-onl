package by.tms.onlinerclonec29onl.controller;

import by.tms.onlinerclonec29onl.model.Account;
import by.tms.onlinerclonec29onl.model.Shop;
import by.tms.onlinerclonec29onl.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @GetMapping("/manage")
    public String manageShop(Model model, @SessionAttribute("account") Account account) {
        Optional<Shop> shop = shopService.getShopByCreatorId(account.getId());
        shop.ifPresent(value -> model.addAttribute("shop", value));
        return "shopManage";
    }

    @PostMapping("/update")
    public String updateShop(@RequestParam("shopId") Long shopId, @RequestParam("title") String title, @RequestParam("description") String description, @SessionAttribute("account") Account account) {
        Optional<Shop> shop = shopService.getShopByCreatorId(account.getId());
        if (shop.isPresent()) {
            shopService.updateShop(new Shop(shopId, title, description, account));
        }
        return "redirect:/shop/manage";
    }

    @PostMapping("/delete")
    public String deleteShop(@RequestParam("shopId") Long shopId, @SessionAttribute("account") Account account) {
        Optional<Shop> shop = shopService.getShopByCreatorId(account.getId());
        if (shop.isPresent()) {
            shopService.deleteShop(shopId);
        }
        return "redirect:/";
    }


    @PostMapping("/create")
    public String createShop(@RequestParam("title") String title, @RequestParam("description") String description, @SessionAttribute("account") Account account) {
        Shop shop = Shop.builder()
                .title(title)
                .description(description)
                .creator(account)
                .build();
        shopService.createShop(shop);
        return "redirect:/shop/manage";
    }
}

