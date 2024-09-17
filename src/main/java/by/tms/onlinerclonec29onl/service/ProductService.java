package by.tms.onlinerclonec29onl.service;

import by.tms.onlinerclonec29onl.dao.ProductDao;
import by.tms.onlinerclonec29onl.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductDao productDao;

    public int save(Product product) {
        return productDao.save(product);
    }

    public int update(Product product) {
        return productDao.update(product);
    }

    public Optional<Product> getById(long id) {
        return productDao.getById(id);
    }

    public int delete(Product product) {
        return productDao.delete(product);
    }

    public List<Product> getAll() {
        return productDao.getAll();
    }
}
