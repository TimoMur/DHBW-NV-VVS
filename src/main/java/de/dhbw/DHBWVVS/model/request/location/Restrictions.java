package de.dhbw.DHBWVVS.model.request.location;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Restrictions {

	@XmlElement(name = "IncludePtModes")
	private Boolean includePtModes;

	public Boolean getIncludePtModes() {
		return includePtModes;
	}

	public void setIncludePtModes(Boolean includePtModes) {
		this.includePtModes = includePtModes;
	}

}
