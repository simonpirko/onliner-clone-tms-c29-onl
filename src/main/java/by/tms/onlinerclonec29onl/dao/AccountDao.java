package by.tms.onlinerclonec29onl.dao;

import by.tms.onlinerclonec29onl.dao.mapper.AccountRowMapper;
import by.tms.onlinerclonec29onl.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private AccountRowMapper accountRowMapper;

    public int save(Account account) {
        return jdbcTemplate.update("insert into public.account (id, name, username, password, type, role) VALUES (default, ?, ?, ?, ?, ?)",
                account.getName(),
                account.getUsername(),
                account.getPassword(),
                account.getType().name(),
                account.getRole().name());
    }

    public int update(Account account) {
        return jdbcTemplate.update("update public.account set name = ?, username = ?, password = ?, type = ?, role = ? where id = ?",
                account.getName(),
                account.getUsername(),
                account.getPassword(),
                account.getType().name(),
                account.getRole().name(),
                account.getId());
    }

    public Optional<Account> getById(long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject("select * from public.account where id = ?", accountRowMapper, id));
    }

    public Optional<Account> getByUsername(String username) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject("select * from public.account where username = ?", accountRowMapper, username));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public int delete(Account account) {
        return jdbcTemplate.update("delete from public.account where id = ?", account.getId());
    }

    public List<Account> getAll() {
        return jdbcTemplate.query("select * from public.account", accountRowMapper);
    }
}
