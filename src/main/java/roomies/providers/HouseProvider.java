package roomies.providers;

import org.springframework.data.repository.CrudRepository;

import roomies.db.entities.House;

public interface HouseProvider extends CrudRepository<House, Long> {

}
