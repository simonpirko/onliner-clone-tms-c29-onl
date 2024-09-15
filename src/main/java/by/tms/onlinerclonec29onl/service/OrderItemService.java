package by.tms.onlinerclonec29onl.service;

import by.tms.onlinerclonec29onl.dao.OrderItemDao;
import by.tms.onlinerclonec29onl.model.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService {
    @Autowired
    private OrderItemDao orderItemDao;

    public int save(OrderItem orderItem) {
        return orderItemDao.save(orderItem);
    }

    public int update(OrderItem orderItem) {
        return orderItemDao.update(orderItem);
    }

    public Optional<OrderItem> getById(long id) {
        return orderItemDao.getById(id);
    }

    public int delete(OrderItem orderItem) {
        return orderItemDao.delete(orderItem);
    }

    public List<OrderItem> getAll() {
        return orderItemDao.getAll();
    }
}
