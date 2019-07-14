package de.dhbw.DHBWVVS.model.request.departure;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Location {

	@XmlElement(name = "LocationRef")
	private LocationRef locationRef;
	@XmlElement(name="DepArrTime")
	private String depArrTime;

	public LocationRef getLocationRef() {
		return locationRef;
	}

	public void setLocationRef(LocationRef locationRef) {
		this.locationRef = locationRef;
	}

	public String getDepArrTime() {
		return depArrTime;
	}

	public void setDepArrTime(String depArrTime) {
		this.depArrTime = depArrTime;
	}

}
