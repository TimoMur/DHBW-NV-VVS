package de.dhbw.DHBWVVS.model.request.departure;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class LocationRef {

	@XmlElement(name = "StopPointRef")
	private String stopPointRef;

	public LocationRef(String stopPointRef) {
		this.stopPointRef = stopPointRef;
	}

	public String getStopPointRef() {
		return stopPointRef;
	}

	public void setStopPointRef(String stopPointRef) {
		this.stopPointRef = stopPointRef;
	}

}
