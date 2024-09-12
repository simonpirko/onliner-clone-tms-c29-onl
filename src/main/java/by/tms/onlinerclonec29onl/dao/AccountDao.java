package by.tms.onlinerclonec29onl.dao;

import by.tms.onlinerclonec29onl.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int save(Account account) {
        return jdbcTemplate.update("insert into public.account (id, name, username, password, type, role) VALUES (default, ?, ?, ?, ?, ?)",
                account.getName(),
                account.getUsername(),
                account.getPassword(),
                account.getType().toString().toUpperCase(),
                account.getRole().toString().toUpperCase());
    }

    public int update(Account account) {
        return jdbcTemplate.update("update public.account set name = ?, username = ?, password = ?, type = ?, role = ? where id = ?",
                account.getName(),
                account.getUsername(),
                account.getPassword(),
                account.getType().toString(),
                account.getRole().toString(),
                account.getId());
    }

    public Optional<Account> getById(long id) {
        Account account = jdbcTemplate.queryForObject("select * from public.account where id = ?", (resultSet, rowNum) -> {
            Account a = new Account();
            a.setId(id);
            a.setName(resultSet.getString("name"));
            a.setUsername(resultSet.getString("username"));
            a.setPassword(resultSet.getString("password"));
            a.setType(Account.Type.valueOf(resultSet.getString("type").toUpperCase()));
            a.setRole(Account.Role.valueOf(resultSet.getString("role").toUpperCase()));
            return a;
        }, id);
        if (account == null) {
            return Optional.empty();
        }
        return Optional.of(account);
    }

    public int delete(Account account) {
        return jdbcTemplate.update("delete from public.account where id = ?", account.getId());
    }

    public List<Account> getAll() {
        return jdbcTemplate.queryForList("select * from public.account", Account.class);
    }
}
