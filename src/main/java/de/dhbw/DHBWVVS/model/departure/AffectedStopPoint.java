package de.dhbw.DHBWVVS.model.departure;

public class AffectedStopPoint extends Affect {

	private String stopPointRef;

	public AffectedStopPoint() {
		super("StopPointSituation");
	}

	public String getStopPointRef() {
		return stopPointRef;
	}

	public void setStopPointRef(String stopPointRef) {
		this.stopPointRef = stopPointRef;
	}

}
