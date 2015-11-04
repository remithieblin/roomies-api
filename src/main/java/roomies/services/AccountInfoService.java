package roomies.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import roomies.api.contracts.AccountInfoRequest;
import roomies.db.entities.AccountInfo;
import roomies.providers.AccountInfoProvider;

@Service
public class AccountInfoService {

	@Autowired
	private AccountInfoProvider accountInfoProvider;

	public void create(AccountInfoRequest infos) {

		AccountInfo account = new AccountInfo();

		account.setAccountId(infos.getId());
		account.setEmail(infos.getEmail());
		account.setName(infos.getName());
		account.setPhone(infos.getPhone());

		if (accountInfoProvider.exists(account.getAccountId()))
			throw new DuplicateKeyException("account already exists: " + account.getAccountId());

		accountInfoProvider.save(account);
	}

	public AccountInfo findOne(long id) {
		return accountInfoProvider.findOne(id);
	}

}
