package roomies.providers;

import org.springframework.data.repository.CrudRepository;

import roomies.db.entities.AccountInfo;

public interface AccountInfoProvider extends CrudRepository<AccountInfo, Long> {

}