package de.dhbw.DHBWVVS.model.request.departure;

public enum PtMode {

	// @formatter:off
	ALL("ALL"),
	UNKNOWN("unknown"),
	BUS("bus"),
	TROLLEY_BUS("trolleyBus"),
	TRAM("tram"),
	COACH("coach"),
	RAIL("rail"),
	INTERCITY_RAIL("intercityRail"),
	URBAN_RAIL("urbanRail"),
	METRO("metro"),
	WATER("water"),
	CABLEWAY("cableway"),
	FUNICULAR("funicular"),
	TAXI("taxi"),
	AIR("AIR");
	// @formatter:on

	private final String mode;

	private PtMode(final String mode) {
		this.mode = mode;
	}

	@Override
	public String toString() {
		return mode;
	}

}
