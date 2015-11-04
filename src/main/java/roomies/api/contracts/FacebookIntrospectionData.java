package roomies.api.contracts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FacebookIntrospectionData {

	@JsonProperty("app_id")
	private String appId;

	@JsonProperty("expires_at")
	private int expiresAt;

	@JsonProperty("is_valid")
	private boolean isValid;

	@JsonProperty("user_id")
	private String userId;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public int getExpiresAt() {
		return expiresAt;
	}

	public void setExpiresAt(int expiresAt) {
		this.expiresAt = expiresAt;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
