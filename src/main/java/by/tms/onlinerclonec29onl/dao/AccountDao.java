package by.tms.onlinerclonec29onl.dao;

import by.tms.onlinerclonec29onl.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Account> getAll() {
        return jdbcTemplate.queryForList("select * from account", Account.class);
    }
}
