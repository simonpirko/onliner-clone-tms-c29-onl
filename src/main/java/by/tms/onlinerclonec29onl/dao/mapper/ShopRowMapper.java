package by.tms.onlinerclonec29onl.dao.mapper;

import by.tms.onlinerclonec29onl.model.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ShopRowMapper implements RowMapper<Shop> {
    @Autowired
    AccountRowMapper accountRowMapper;
    @Override
    public Shop mapRow(ResultSet rs, int rowNum) throws SQLException {
        Shop shop = new Shop();
        shop.setId(rs.getLong("id"));
        shop.setTitle(rs.getString("title"));
        shop.setDescription(rs.getString("description"));
        shop.setCreator(accountRowMapper.mapRow(rs, rowNum));
        return shop;
    }
}
