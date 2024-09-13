package by.tms.onlinerclonec29onl.dao.mapper;

import by.tms.onlinerclonec29onl.model.Product;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ProductRowMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();
        product.setId(rs.getLong("main_product_id"));
        product.setName(rs.getString("name"));
        product.setDescription(rs.getString("description"));
        product.setCategory(rs.getString("category"));
        product.setImage(rs.getString("image").getBytes());
        return product;
    }
}
