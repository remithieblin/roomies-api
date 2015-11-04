package roomies.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import roomies.api.contracts.HouseRequest;
import roomies.db.entities.House;
import roomies.providers.HouseProvider;

@Service
public class HouseService extends ParentService {

	@Autowired
	private HouseProvider houseProvider;

	public House findOne(long id) {
		return houseProvider.findOne(id);
	}

	public void create(HouseRequest houseRequest) {

		House house = new House();
		house.setName(houseRequest.getName());
		house.setCity(house.getCity());
		house.setZip(houseRequest.getZip());

		//check if account has already created more than 3 house
		houseProvider.save(house);
	}

}
