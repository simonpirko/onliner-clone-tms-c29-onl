package by.tms.onlinerclonec29onl.service;

import by.tms.onlinerclonec29onl.dao.AccountDao;
import by.tms.onlinerclonec29onl.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountDao accountDao;

    public void register(Account account) {
        accountDao.save(account);
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