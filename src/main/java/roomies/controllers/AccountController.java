package roomies.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import roomies.api.contracts.AccountInfoRequest;
import roomies.db.entities.AccountInfo;
import roomies.services.AccountInfoService;

@RestController
public class AccountController extends ParentController {

	@Autowired
	private AccountInfoService accountService;

	@RequestMapping(method = RequestMethod.POST, value = "/account")
	public void create(@RequestBody AccountInfoRequest infos) {

		accountService.create(infos);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/account")
	public AccountInfo get(long id) {
		return accountService.findOne(id);
	}

}
