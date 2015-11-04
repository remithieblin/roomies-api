package roomies.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import roomies.api.contracts.FacebookCredentials;
import roomies.api.contracts.JwtResponse;
import roomies.services.TokenService;

@RestController
public class TokenController extends ParentController {

	@Autowired
	private TokenService tokenService;

	@RequestMapping(method = RequestMethod.POST, value = "/auth/token")
	public JwtResponse create(@RequestBody FacebookCredentials credentials) throws Exception {

		return tokenService.authenticate(credentials.getFacebookToken());

	}

}
