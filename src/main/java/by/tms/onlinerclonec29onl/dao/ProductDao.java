package by.tms.onlinerclonec29onl.dao;

import by.tms.onlinerclonec29onl.dao.mapper.ProductRowMapper;
import by.tms.onlinerclonec29onl.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ProductRowMapper productRowMapper;

    public int save(Product product) {
        return jdbcTemplate.update("insert into public.product (main_product_id, product_name, product_description, product_category, product_image) values (default, ?, ?, ?, ?)",
                product.getName(),
                product.getDescription(),
                product.getCategory(),
                product.getImage());
    }

    public int update(Product product) {
        return jdbcTemplate.update("update public.product set product_name = ?, product_description = ?, product_category = ?, product_image = ? where main_product_id = ?",
                product.getName(),
                product.getDescription(),
                product.getCategory(),
                product.getImage(),
                product.getId());
    }

    public Optional<Product> getById(long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject("select * from public.product where main_product_id = ?", productRowMapper, id));
    }

    public int delete(Product product) {
        return jdbcTemplate.update("delete from public.product where main_product_id = ?", product.getId());
    }

    public List<Product> getAll() {
        return jdbcTemplate.query("select * from public.product", productRowMapper);
    }
}
