package de.dhbw.DHBWVVS.model.request.departure;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import de.dhbw.DHBWVVS.model.request.Request;

@XmlAccessorType(XmlAccessType.FIELD)
public class StopEventRequest extends Request {
	@XmlElement(name = "Location")
	private Location location;
	@XmlElement(name = "Params")
	private Parameter params;

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Parameter getParams() {
		return params;
	}

	public void setParams(Parameter params) {
		this.params = params;
	}

}
