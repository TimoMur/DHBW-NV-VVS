package de.dhbw.DHBWVVS.model.departure;

public class StopEvent {

	private String id;
	private ThisCall thisCall;
	private VVSService service;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ThisCall getThisCall() {
		return thisCall;
	}

	public void setThisCall(ThisCall thisCall) {
		this.thisCall = thisCall;
	}

	public VVSService getService() {
		return service;
	}

	public void setService(VVSService service) {
		this.service = service;
	}

}
