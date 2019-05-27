package de.dhbw.DHBWVVS.model.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Trias {

	@XmlAttribute(name = "version")
	private String version = "1.1";
	@XmlAttribute(name = "xmlns")
	private String xmlns = "http://www.vdv.de/trias";
	@XmlAttribute(name = "xmlns:siri")
	private String xmlnsSiri = "http://www.siri.org.uk/siri";
	@XmlElement(name = "ServiceRequest")
	private ServiceRequest request;

	public ServiceRequest getRequest() {
		return request;
	}

	public void setRequest(ServiceRequest request) {
		this.request = request;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

//	public String getXmlns() {
//		return xmlns;
//	}
//
//	public void setXmlns(String xmlns) {
//		this.xmlns = xmlns;
//	}

	public String getXmlnsSiri() {
		return xmlnsSiri;
	}

	public void setXmlnsSiri(String xmlnsSiri) {
		this.xmlnsSiri = xmlnsSiri;
	}

}
