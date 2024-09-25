package by.tms.onlinerclonec29onl.dao;

import by.tms.onlinerclonec29onl.dao.mapper.SellerProductRowMapper;
import by.tms.onlinerclonec29onl.model.SellerProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SellerProductDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SellerProductRowMapper sellerProductRowMapper;

    public List<SellerProduct> getAll() {
        return jdbcTemplate.query("select * from public.sellerproduct s join public.product p on s.product_id = p.main_product_id join public.shop s2 on s2.main_shop_id = s.shop_id join public.account a on a.id = s2.creator_id", sellerProductRowMapper);
    }

    public int save(SellerProduct sellerProduct) {
        return jdbcTemplate.update("insert into sellerproduct (seller_product_id, product_id, shop_id, price) values (default, ?, ?, ?)",
                sellerProduct.getProduct().getId(),
                sellerProduct.getShop().getId(),
                sellerProduct.getPrice());
    }

    public int update(SellerProduct sellerProduct) {
        return jdbcTemplate.update("update public.sellerProduct set product_id = ?, shop_id = ?, price = ? where seller_product_id = ?",
                sellerProduct.getProduct().getId(),
                sellerProduct.getShop().getId(),
                sellerProduct.getPrice(),
                sellerProduct.getId());
    }

    public Optional<SellerProduct> getById(Long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject("select * from public.sellerproduct s join public.product p on s.product_id = p.main_product_id join public.shop s2 on s2.main_shop_id = s.shop_id join public.account a on a.id = s2.creator_id where s.seller_product_id = ?", sellerProductRowMapper, id));
    }

    public int delete(SellerProduct sellerProduct) {
        return jdbcTemplate.update("delete from public.sellerproduct where sellerproduct.seller_product_id = ?", sellerProduct.getId());
    }
}
