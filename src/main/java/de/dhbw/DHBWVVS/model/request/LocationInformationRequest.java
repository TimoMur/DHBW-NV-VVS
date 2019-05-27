package de.dhbw.DHBWVVS.model.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class LocationInformationRequest  extends Request {

	@XmlElement(name = "InitialInput")
	private InitialInput initialInput;
	@XmlElement(name = "Restrictions")
	private Restrictions restrictions;

	public InitialInput getInitialInput() {
		return initialInput;
	}

	public void setInitialInput(InitialInput initialInput) {
		this.initialInput = initialInput;
	}

	public Restrictions getRestrictions() {
		return restrictions;
	}

	public void setRestrictions(Restrictions restrictions) {
		this.restrictions = restrictions;
	}

}
