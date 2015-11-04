package roomies.providers;

import org.springframework.data.repository.CrudRepository;

import roomies.db.entities.Account;

public interface AccountProvider extends CrudRepository<Account, Long> {

	Account findByProviderId(String providerId);

}