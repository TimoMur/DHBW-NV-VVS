package de.dhbw.DHBWVVS.model.request.departure;

public enum StopEventType {

	DEPARTURE("departure"),
	ARRIVAL("arrival"),
	BOTH("both");
	
	private final String type;
	
	private StopEventType(final String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return type;
	}
	
}
