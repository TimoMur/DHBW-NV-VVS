package de.dhbw.DHBWVVS.model.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ServiceRequest {

	@XmlElement(name = "siri:RequestTimestamp")
	private String requestTimestamp;
	@XmlElement(name = "siri:RequestorRef")
	private String requestorRef;
	@XmlElement(name = "RequestPayload")
	private RequestPayload payload;

	public RequestPayload getPayload() {
		return payload;
	}

	public void setPayload(RequestPayload payload) {
		this.payload = payload;
	}

	public String getRequestTimestamp() {
		return requestTimestamp;
	}

	public void setRequestTimestamp(String requestTimestamp) {
		this.requestTimestamp = requestTimestamp;
	}

	public String getRequestorRef() {
		return requestorRef;
	}

	public void setRequestorRef(String requestorRef) {
		this.requestorRef = requestorRef;
	}

}
