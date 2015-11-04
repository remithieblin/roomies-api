package roomies.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import roomies.api.contracts.HouseRequest;
import roomies.db.entities.House;
import roomies.services.HouseService;

@RestController
public class HouseController extends ParentController {

	@Autowired
	private HouseService houseService;

	@RequestMapping(method = RequestMethod.POST, value = "/house")
	public void create(@RequestBody HouseRequest houseRequest) {

		houseService.create(houseRequest);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/house")
	public House get(long id) {
		return houseService.findOne(id);
	}

}
