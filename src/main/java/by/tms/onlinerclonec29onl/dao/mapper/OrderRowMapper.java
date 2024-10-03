package by.tms.onlinerclonec29onl.dao.mapper;

import by.tms.onlinerclonec29onl.model.Account;
import by.tms.onlinerclonec29onl.model.Order;
import by.tms.onlinerclonec29onl.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class OrderRowMapper implements RowMapper<Order> {

    @Autowired
    private OrderItemRowMapper orderItemRowMapper;

    @Autowired
    private AccountService accountService;

    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order order = new Order();
        Account account = new Account();
        order.setId(rs.getLong("main_order_id"));
        //order.setOrderItem(orderItemRowMapper.mapRow(rs, rowNum));
        order.setTotalPrice(rs.getBigDecimal("totalprice"));
        order.setStatus(Order.OrderStatus.valueOf(rs.getString("status").toUpperCase()));
       // order.setAccount(accountService.findById(rs.getLong("accountId")));
        return order;
    }
}
