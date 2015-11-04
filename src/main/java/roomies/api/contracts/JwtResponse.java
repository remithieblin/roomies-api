package roomies.api.contracts;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JwtResponse {

	@JsonProperty("access_token")
	private String accessToken;

	@JsonProperty("expires_at")
	private long expiration;

	@JsonProperty("token_type")
	private String tokenType;

	@JsonProperty("status")
	private String status;

	public long getExpiration() {
		return expiration;
	}

	public void setExpiration(long expiration) {
		this.expiration = expiration;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
