package by.tms.onlinerclonec29onl.service;

import by.tms.onlinerclonec29onl.dao.OrderDao;
import by.tms.onlinerclonec29onl.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;

//

    public long createOrder(Order order) throws SQLException {
        long orderId = orderDao.save(order);
        return orderId;
    }

    public void updateOrder(Order order) {
        orderDao.update(order);
    }

    public Optional<Order> getOrderById(long id) {
        return orderDao.getById(id);
    }

    public List<Order> getAllOrders() {
        return orderDao.getAll();
    }

    public void deleteOrder(long id) {
        orderDao.delete(orderDao.getById(id).orElseThrow(() -> new IllegalArgumentException("Order not found")));
    }
}