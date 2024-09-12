package by.tms.onlinerclonec29onl.dao;

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

    public int save(Product product) {
        return jdbcTemplate.update("insert into public.product (id, name, description, category, image) values (default, ?, ?, ?, ?)",
                product.getName(),
                product.getDescription(),
                product.getCategory(),
                product.getImage());
    }

    public int update(Product product) {
        return jdbcTemplate.update("update public.product set name = ?, description = ?, category = ?, image = ? where id = ?",
                product.getName(),
                product.getDescription(),
                product.getCategory(),
                product.getImage(),
                product.getId());
    }

    public Optional<Product> findById(long id) {
        Product product = jdbcTemplate.queryForObject("select * from public.product where id = ?", (resultSet, rowNum) -> {
            Product p = new Product();
            p.setName(resultSet.getString("name"));
            p.setDescription(resultSet.getString("description"));
            p.setCategory(resultSet.getString("category"));
            p.setImage(resultSet.getBytes("image"));
            return p;
        }, id);
        if (product == null) {
            return Optional.empty();
        }
        return Optional.of(product);
    }

    public int delete(Product product) {
        return jdbcTemplate.update("delete from public.product where id = ?", product.getId());
    }

    public List<Product> findAll() {
        return jdbcTemplate.queryForList("select * from public.product", Product.class);
    }
}
