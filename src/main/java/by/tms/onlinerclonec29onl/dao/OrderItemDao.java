package by.tms.onlinerclonec29onl.dao;

import by.tms.onlinerclonec29onl.dao.mapper.OrderItemRowMapper;
import by.tms.onlinerclonec29onl.model.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderItemDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private OrderItemRowMapper orderItemRowMapper;

    public int save(OrderItem orderItem) {
        return jdbcTemplate.update("insert into public.orderitem (main_orderitem_id, product_id, quantity, price) values (default, ?, ?, ?)",
                orderItem.getProduct().getId(),
                orderItem.getQuantity(),
                orderItem.getPrice());
    }

    public int update(OrderItem orderItem) {
        return jdbcTemplate.update("update orderitem set product_id = ?, quantity = ?, price = ? where main_orderitem_id = ?",
                orderItem.getProduct().getId(),
                orderItem.getQuantity(),
                orderItem.getPrice(),
                orderItem.getId());
    }

    public Optional<OrderItem> getById(long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject("select * from public.orderitem o join public.product p on o.product_id = p.main_product_id where o.main_orderitem_id = ?", orderItemRowMapper, id));
    }

    public int delete(OrderItem orderItem) {
        return jdbcTemplate.update("delete from public.orderitem where main_orderitem_id = ?", orderItem.getId());
    }

    public List<OrderItem> getAll() {
        return jdbcTemplate.query("select * from public.orderitem o join public.product p on o.product_id = p.main_product_id", orderItemRowMapper);
    }
}
