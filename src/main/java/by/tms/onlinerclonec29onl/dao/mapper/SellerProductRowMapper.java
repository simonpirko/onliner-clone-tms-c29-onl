package by.tms.onlinerclonec29onl.dao.mapper;

import by.tms.onlinerclonec29onl.model.SellerProduct;
import by.tms.onlinerclonec29onl.model.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class SellerProductRowMapper implements RowMapper<SellerProduct> {
    @Autowired
    private ProductRowMapper productRowMapper;

    @Autowired
    private ShopRowMapper shopRowMapper;

    @Override
    public SellerProduct mapRow(ResultSet rs, int rowNum) throws SQLException {
        SellerProduct sellerProduct = new SellerProduct();
        sellerProduct.setId(rs.getLong("seller_product_id"));
        sellerProduct.setProduct(productRowMapper.mapRow(rs, rowNum));
        sellerProduct.setShop(shopRowMapper.mapRow(rs, rowNum));
        sellerProduct.setPrice(rs.getDouble("price"));
        return sellerProduct;
    }
}
