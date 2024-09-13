package by.tms.onlinerclonec29onl.service;

import by.tms.onlinerclonec29onl.dao.ShopDao;
import by.tms.onlinerclonec29onl.model.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShopService {

    @Autowired
    private ShopDao shopDao;

    public void createShop(Shop shop) {
        shopDao.save(shop);
    }

    public void updateShop(Shop shop) {
        shopDao.update(shop);
    }

    public Optional<Shop> getShopById(long id) {
        return shopDao.findById(id);
    }

    public List<Shop> getAllShops() {
        return shopDao.findAll();
    }

    public void deleteShop(long id) {
        shopDao.delete(shopDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Shop not found")));
    }
}