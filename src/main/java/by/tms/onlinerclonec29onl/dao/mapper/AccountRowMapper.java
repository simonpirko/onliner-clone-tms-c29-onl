package by.tms.onlinerclonec29onl.dao.mapper;

import by.tms.onlinerclonec29onl.model.Account;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AccountRowMapper implements RowMapper<Account> {
    @Override
    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
        Account account = new Account();
        account.setId(rs.getLong("id"));
        account.setName(rs.getString("name"));
        account.setUsername(rs.getString("username"));
        account.setPassword(rs.getString("password"));
        account.setType(Account.Type.valueOf(rs.getString("type").toUpperCase()));
        account.setRole(Account.Role.valueOf(rs.getString("role").toUpperCase()));
        return account;
    }
}
