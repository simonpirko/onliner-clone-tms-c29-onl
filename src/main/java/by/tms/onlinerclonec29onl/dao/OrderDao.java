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
        return jdbcTemplate.update("insert into public.order (main_order_id, orderitem_id, totalprice, status) values (default, ?, ?, ?)",
                order.getOrderItem().getId(),
                order.getTotalPrice(),
                order.getStatus().toString().toUpperCase());
    }

    public int update(Order order) {
        return jdbcTemplate.update("update public.order set orderitem_id = ?, totalprice = ?, status = ? where main_order_id = ?",
                order.getOrderItem().getId(),
                order.getTotalPrice(),
                order.getStatus().toString().toUpperCase(),
                order.getId());
    }

    public Optional<Order> getById(long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject("select * from public.order o join public.orderitem oi on o.orderitem_id = oi.main_orderitem_id join public.product p on oi.product_id = p.main_product_id where o.main_order_id = ?", orderRowMapper, id));
    }

    public int delete(Order order) {
        return jdbcTemplate.update("delete from public.order where main_order_id = ?", order.getId());
    }

    public List<Order> getAll() {
        return jdbcTemplate.query("select * from public.order o join public.orderitem oi on o.orderitem_id = oi.main_orderitem_id join public.product p on oi.product_id = p.main_product_id", orderRowMapper);
    }
}
