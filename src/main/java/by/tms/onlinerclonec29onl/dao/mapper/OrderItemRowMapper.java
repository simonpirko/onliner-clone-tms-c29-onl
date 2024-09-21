package by.tms.onlinerclonec29onl.dao.mapper;

import by.tms.onlinerclonec29onl.model.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class OrderItemRowMapper implements RowMapper<OrderItem> {

    @Autowired
    private ProductRowMapper productRowMapper;

    @Override
    public OrderItem mapRow(ResultSet rs, int rowNum) throws SQLException {
        OrderItem orderItem = new OrderItem();
        orderItem.setId(rs.getLong("main_orderitem_id"));
        orderItem.setProduct(productRowMapper.mapRow(rs, rowNum));
        orderItem.setQuantity(rs.getInt("quantity"));
        orderItem.setPrice(rs.getBigDecimal("price"));
        return orderItem;
    }
}
