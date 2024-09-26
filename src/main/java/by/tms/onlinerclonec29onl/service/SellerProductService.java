package by.tms.onlinerclonec29onl.service;

import by.tms.onlinerclonec29onl.dao.SellerProductDao;
import by.tms.onlinerclonec29onl.model.SellerProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SellerProductService {

    @Autowired
    private SellerProductDao sellerProductDao;

    public int save(SellerProduct sellerProduct) {
        return sellerProductDao.save(sellerProduct);
    }

    public int update(SellerProduct sellerProduct) {
        return sellerProductDao.update(sellerProduct);
    }

    public Optional<SellerProduct> getById(Long id) {
        return sellerProductDao.getById(id);
    }

    public int delete(SellerProduct sellerProduct) {
        return sellerProductDao.delete(sellerProduct);
    }

    public List<SellerProduct> getAll() {
        return sellerProductDao.getAll();
    }

    public List<SellerProduct> getAllProductsByShop(Long shopId) {
        return sellerProductDao.getAll().stream().filter(product -> product.getShop().getId().equals(shopId)).collect(Collectors.toList());
    }

    public List<SellerProduct> getSellerProductByProductId(Long id) {
        return sellerProductDao.getSellerProductByProductId(id);
    }
}
