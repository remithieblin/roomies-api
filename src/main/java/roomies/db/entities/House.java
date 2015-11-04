package roomies.db.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "house")
public class House {

	@Id
	private long houseId;

	private String name;

	private String zip;

	private String city;

	public long getHouseId() {
		return houseId;
	}

	public void setHouseId(long houseId) {
		this.houseId = houseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
