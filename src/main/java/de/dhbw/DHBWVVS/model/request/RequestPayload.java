package de.dhbw.DHBWVVS.model.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;

import de.dhbw.DHBWVVS.model.request.departure.StopEventRequest;
import de.dhbw.DHBWVVS.model.request.location.LocationInformationRequest;

@XmlAccessorType(XmlAccessType.FIELD)
public class RequestPayload {

	@XmlElements({@XmlElement(name = "LocationInformationRequest", type = LocationInformationRequest.class),
			@XmlElement(name = "StopEventRequest", type = StopEventRequest.class) })
	private Request request;

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

}
