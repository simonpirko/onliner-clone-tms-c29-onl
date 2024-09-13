package by.tms.onlinerclonec29onl.dao;

import by.tms.onlinerclonec29onl.dao.mapper.SellerProductRowMapper;
import by.tms.onlinerclonec29onl.model.SellerProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SellerProductDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SellerProductRowMapper sellerProductRowMapper;

    public List<SellerProduct> getAll() {
        return jdbcTemplate.query("select * from public.sellerproduct s join public.product p on s.product_id = p.main_product_id join public.shop s2 on s2.main_shop_id = s.shop_id join public.account a on a.id = s2.creator_id", sellerProductRowMapper);
    }

}
