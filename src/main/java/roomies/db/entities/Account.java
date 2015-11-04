package roomies.db.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "account")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String providerId;

	private String providerToken;

	// ------------------------
	// PUBLIC METHODS
	// ------------------------

	public Account() {
	}

	public Account(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public String getProviderToken() {
		return providerToken;
	}

	public void setProviderToken(String providerToken) {
		this.providerToken = providerToken;
	}

}
