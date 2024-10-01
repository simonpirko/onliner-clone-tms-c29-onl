package by.tms.onlinerclonec29onl.dao;

import by.tms.onlinerclonec29onl.dao.mapper.OrderRowMapper;
import by.tms.onlinerclonec29onl.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private OrderRowMapper orderRowMapper;


    public int save(Order order) {
        return jdbcTemplate.update("insert into public.order (main_order_id, totalprice, status, delivery_address, account_id) values (default, ?, ?, ?, ?)",
                //order.getOrderItem().getId(),
                order.getTotalPrice(),
                order.getStatus().toString().toUpperCase(),
                order.getDelivery_address(),
                order.getAccountId());
    }

    public int update(Order order) {
        return jdbcTemplate.update("update public.order set totalprice = ?, status = ?, delivery_address = ?, account_id = ? where main_order_id = ?",
                //order.getOrderItem().getId(),
                order.getTotalPrice(),
                order.getStatus().toString().toUpperCase(),
                order.getDelivery_address(),
                order.getAccountId(),
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
