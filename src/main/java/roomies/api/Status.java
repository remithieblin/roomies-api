package roomies.api;

public enum Status {

	NEW("new"),
	EXISTING("existing");

	private final String status;

	Status(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

}
