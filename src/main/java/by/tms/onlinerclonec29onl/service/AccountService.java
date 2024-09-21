package by.tms.onlinerclonec29onl.service;

import by.tms.onlinerclonec29onl.dao.AccountDao;
import by.tms.onlinerclonec29onl.model.Account;
import by.tms.onlinerclonec29onl.model.dto.LoginAccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountDao accountDao;


    public void register(Account account) {
        account.setRole(Account.Role.USER);
        account.setType(Account.Type.PERSONAL);
        accountDao.save(account);
    }

    public Optional<Account> login(LoginAccountDto loginAccountDto) {
        Optional<Account> account = accountDao.getByUsername(loginAccountDto.getUsername());
        if (account.isPresent()) {
            if (account.get().getPassword().equals(loginAccountDto.getPassword())) {
                return account;
            }
        }
        return Optional.empty();
    }

    public void updateAccount(Account account) {
        accountDao.update(account);
    }

    public Optional<Account> findById(long id) {
        return accountDao.getById(id);
    }

    public List<Account> getAllAccounts() {
        return accountDao.getAll();
    }

    public void deleteAccount(long id) {
        accountDao.delete(accountDao.getById(id).orElseThrow(() -> new IllegalArgumentException("Account not found")));
    }
}