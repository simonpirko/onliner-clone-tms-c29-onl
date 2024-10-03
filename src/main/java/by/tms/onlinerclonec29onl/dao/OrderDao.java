package by.tms.onlinerclonec29onl.dao;

import by.tms.onlinerclonec29onl.dao.mapper.OrderRowMapper;
import by.tms.onlinerclonec29onl.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private OrderRowMapper orderRowMapper;


    public int save(Order order) throws SQLException {
        //return jdbcTemplate.update("insert into public.order (main_order_id, totalprice, status, delivery_address, account_id) values (default, ?, ?, ?, ?) RETURNING main_order_id",
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
        PreparedStatement preparedStatement = connection.prepareStatement("insert into public.order values (default, ?, ?, ?, ?) RETURNING main_order_id");
        preparedStatement.setBigDecimal(1, order.getTotalPrice());
        preparedStatement.setString(2, order.getStatus().toString().toUpperCase());
        preparedStatement.setString(3, order.getDeliveryAddress());
        preparedStatement.setLong(4, order.getAccount().getId());
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        long orderId = resultSet.getLong(1);
        return (int) orderId;

//        return jdbcTemplate.update("insert into public.order values (default, ?, ?, ?, ?) RETURNING main_order_id",
//                //order.getOrderItem().getId(),
//                order.getTotalPrice(),
//                order.getStatus().toString().toUpperCase(),
//                order.getDeliveryAddress(),
//                order.getAccount().getId());
    }

    public int update(Order order) {
        return jdbcTemplate.update("update public.order set totalprice = ?, status = ?, delivery_address = ?, account_id = ? where main_order_id = ?",
                //order.getOrderItem().getId(),
                order.getTotalPrice(),
                order.getStatus().toString().toUpperCase(),
                order.getDeliveryAddress(),
                order.getAccount().getId(),
                order.getId());
    }

    public Optional<Order> getById(long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject("select * from public.order where main_order_id = ?", orderRowMapper, id));
    }

    public int delete(Order order) {
        return jdbcTemplate.update("delete from public.order where main_order_id = ?", order.getId());
    }

    public List<Order> getAll() {
        return jdbcTemplate.query("select * from public.order", orderRowMapper);
    }
}
