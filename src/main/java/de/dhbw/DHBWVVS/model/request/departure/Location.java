package de.dhbw.DHBWVVS.model.request.departure;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Location {

	@XmlElement(name = "LocationRef")
	private LocationRef locationRef;

	public LocationRef getLocationRef() {
		return locationRef;
	}

	public void setLocationRef(LocationRef locationRef) {
		this.locationRef = locationRef;
	}

}
