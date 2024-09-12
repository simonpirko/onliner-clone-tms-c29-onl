package by.tms.onlinerclonec29onl.dao;

import by.tms.onlinerclonec29onl.model.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ShopDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int save(Shop shop) {
        return jdbcTemplate.update("insert into public.shop (id, title, description, creator_id) values (default, ?, ?, ?)",
                shop.getId(),
                shop.getTitle(),
                shop.getDescription(),
                shop.getCreator().getId());
    }

    public int update(Shop shop) {
        return jdbcTemplate.update("update public.shop set title = ?, description = ?, creator_id = ? where id = ?",
                shop.getTitle(),
                shop.getDescription(),
                shop.getCreator().getId(),
                shop.getId());
    }

    public Optional<Shop> findById(long id) {
        Shop shop = jdbcTemplate.queryForObject("select shop.title, shop.description, shop.creator_id from public.shop where id = ?", (resultSet, rowNum) -> {
            Shop s = new Shop();
            s.setId(id);
            s.setTitle(resultSet.getString("title"));
            s.setDescription(resultSet.getString("description"));
            s.getCreator().setId(resultSet.getLong("creator_id"));
            return s;
        }, id);
        if (shop == null) {
            return Optional.empty();
        }
        return Optional.of(shop);
    }

    public int delete(long id) {
        return jdbcTemplate.update("delete from public.shop where id = ?", id);
    }

    public List<Shop> findAll() {
        return jdbcTemplate.queryForList("select * from public.shop", Shop.class);
    }
}
