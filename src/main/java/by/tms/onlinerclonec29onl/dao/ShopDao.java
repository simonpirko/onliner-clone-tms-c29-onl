package by.tms.onlinerclonec29onl.dao;

import by.tms.onlinerclonec29onl.dao.mapper.ShopRowMapper;
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

    @Autowired
    private ShopRowMapper shopRowMapper;

    public int save(Shop shop) {
        return jdbcTemplate.update("insert into public.shop (main_shop_id, shop_title, shop_description, creator_id) values (default, ?, ?, ?)",
                shop.getTitle(),
                shop.getDescription(),
                shop.getCreator().getId());
    }

    public int update(Shop shop) {
        return jdbcTemplate.update("update public.shop set shop_title = ?, shop_description = ?, creator_id = ? where main_shop_id = ?",
                shop.getTitle(),
                shop.getDescription(),
                shop.getCreator().getId(),
                shop.getId());
    }

    public Optional<Shop> getById(long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject("select * from public.shop s join public.account a on s.creator_id = a.id where s.main_shop_id = ?", shopRowMapper, id));
    }


    public int delete(Shop shop) {
        return jdbcTemplate.update("delete from public.shop where shop.main_shop_id = ?", shop.getId());
    }

    public List<Shop> getAll() {
        return jdbcTemplate.query("select * from public.shop s join public.account a on s.creator_id = a.id", shopRowMapper);
    }
}
