package de.dhbw.DHBWVVS.model.departure;

public class AffectedVehicleJourney extends Affect {

	private String lineRef;
	private String route;

	public AffectedVehicleJourney() {
		super("VehicleJourneySituation");
	}

	public String getLineRef() {
		return lineRef;
	}

	public void setLineRef(String lineRef) {
		this.lineRef = lineRef;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

}
