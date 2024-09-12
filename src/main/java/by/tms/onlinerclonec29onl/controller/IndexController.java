package by.tms.onlinerclonec29onl.controller;

import by.tms.onlinerclonec29onl.dao.OrderDao;
import by.tms.onlinerclonec29onl.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private OrderDao orderDao;

    @GetMapping
    public String index() {

        List<Order> all = orderDao.findAll();
        System.out.println(all);

        return "index";
    }
}
