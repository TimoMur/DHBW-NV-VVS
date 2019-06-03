package de.dhbw.DHBWVVS.model.departure;

import java.util.List;

public class DepartureDAO {

	private List<PtSituation> situations;
	private List<StopEvent> stopEvents;

	public List<PtSituation> getSituations() {
		return situations;
	}

	public void setSituations(List<PtSituation> situations) {
		this.situations = situations;
	}

	public List<StopEvent> getStopEvents() {
		return stopEvents;
	}

	public void setStopEvents(List<StopEvent> stopEvents) {
		this.stopEvents = stopEvents;
	}

}
