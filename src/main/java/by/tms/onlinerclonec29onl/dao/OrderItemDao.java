package by.tms.onlinerclonec29onl.dao;

import by.tms.onlinerclonec29onl.model.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class OrderItemDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ProductDao productDao;

    public Optional<OrderItem> findById(long id) {
        OrderItem orderItem = jdbcTemplate.queryForObject("select * from public.orderitem where id = ?", (resultSet, rowNum) -> {
            OrderItem o = new OrderItem();
            o.setId(id);
            if (productDao.findById(o.getId()).isPresent()) {
                o.setProduct(productDao.findById(o.getId()).get());
            }
            o.setQuantity(resultSet.getInt("quantity"));
            o.setPrice(resultSet.getDouble("price"));
            return o;
        }, id);
        if (orderItem == null) {
            return Optional.empty();
        }
        return Optional.of(orderItem);
    }
}
