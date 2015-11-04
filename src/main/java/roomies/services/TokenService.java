package roomies.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import roomies.api.Status;
import roomies.api.contracts.FacebookIntrospectionData;
import roomies.api.contracts.JwtResponse;
import roomies.db.entities.Account;
import roomies.providers.AccountProvider;
import roomies.utils.FacebookIntrospector;
import roomies.utils.TokenBuilder;

import com.nimbusds.jose.JOSEException;

@Service
public class TokenService extends ParentService {

	@Autowired
	private FacebookIntrospector instrospector;

	@Autowired
	private AccountProvider accountProvider;

	@Autowired
	private TokenBuilder tokenBuilder;

	public JwtResponse authenticate(String userAccessToken) throws Exception {

		LOG.debug("facebook user access_token: " + userAccessToken);

		FacebookIntrospectionData data = instrospector.introspect(userAccessToken);

		if (instrospector.vaidateTokenData(data)) {

			String userIdProvider = data.getUserId();

			Status status = Status.NEW;

			Account account = accountProvider.findByProviderId(userIdProvider);

			if (account == null)
				account = createAccount(userIdProvider, userAccessToken);

			else {

				if (!account.getProviderToken().equals(userAccessToken))
					updateUserAccessToken(account, userAccessToken);

				status = Status.EXISTING;

			}

			return createJwtForAccount(account.getId(), data.getExpiresAt(), status);

		} else {
			LOG.info("user " + data.getUserId() + " with token: " + userAccessToken + " is invalid (expires at: "
					+ data.getExpiresAt() + ")");
			throw new Exception("facebook user access token invalid");
		}
	}

	private void updateUserAccessToken(Account account, String userAccessToken) {
		account.setProviderToken(userAccessToken);
		accountProvider.save(account);
	}

	public Account createAccount(String userIdProvider, String userAccessToken) {

		Account account = new Account();
		account.setProviderId(userIdProvider);
		account.setProviderToken(userAccessToken);
		Account accountSaved = accountProvider.save(account);

		return accountSaved;
	}

	public JwtResponse createJwtForAccount(long accountId, int expiration, Status status) throws JOSEException,
			IOException {

		String jwt = tokenBuilder.buildToken(accountId, expiration);

		JwtResponse response = new JwtResponse();
		response.setExpiration(expiration);
		response.setAccessToken(jwt);
		response.setTokenType("token_type");
		response.setStatus(status.getStatus());

		return response;
	}

}
