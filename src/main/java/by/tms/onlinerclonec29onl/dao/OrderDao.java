package by.tms.onlinerclonec29onl.dao;

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
    private ProductDao productDao;

    @Autowired
    private OrderItemDao orderItemDaoDao;

    public int save(Order order) {
        return jdbcTemplate.update("insert into public.order (id, orderitem_id, totalprice, status) values (default, ?, ?, ?)",
                order.getOrderItem().getId(),
                order.getTotalPrice(),
                order.getStatus());
    }

    public int update(Order order) {
        return jdbcTemplate.update("update public.order set orderitem_id = ?, totalprice = ?, status = ? where id = ?",
                order.getOrderItem().getId(),
                order.getTotalPrice(),
                order.getStatus(),
                order.getId());
    }

    public Optional<Order> findById(long id) {
        Order order = jdbcTemplate.queryForObject("select * from public.order where id = ?", (resultSet, rowNum) -> {
            Order o = new Order();
            o.setId(id);
            if (orderItemDaoDao.findById(o.getId()).isPresent()) {
                o.setOrderItem(orderItemDaoDao.findById(o.getId()).get());
            }
            o.setTotalPrice(resultSet.getDouble("totalprice"));
            o.setStatus(resultSet.getString("status"));
            return o;
        }, id);
        if (order != null) {
            return Optional.of(order);
        }
        return Optional.empty();
    }

    public int delete(Order order) {
        return jdbcTemplate.update("delete from public.order where id = ?", order.getId());
    }

    public List<Order> findAll() {
        return jdbcTemplate.queryForList("select id, orderitem_id, totalprice, status from public.order", Order.class);
    }
}
