package de.dhbw.DHBWVVS.model.request.departure;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Parameter {

	@XmlElement(name = "NumberOfResults")
	int numberOfResults = 5;
	@XmlElement(name = "StopEventType")
	private String type;
	@XmlElement(name = "IncludeRealtimeData")
	private boolean includeRealtimeData = true;
//	private List<PtModeFilter> ptModeFilter;

	public int getNumberOfResults() {
		return numberOfResults;
	}

	public void setNumberOfResults(int numberOfResults) {
		this.numberOfResults = numberOfResults;
	}

	public String getStopEventType() {
		return type.toString();
	}

	public void setStopEventType(String type) {
		this.type = type;
	}

	public boolean isIncludeRealtimeData() {
		return includeRealtimeData;
	}

	public void setIncludeRealtimeData(boolean includeRealtimeData) {
		this.includeRealtimeData = includeRealtimeData;
	}

//	public List<PtModeFilter> getPtModeFilter() {
//		return ptModeFilter;
//	}
//
//	public void setPtModeFilter(List<PtModeFilter> ptModeFilter) {
//		this.ptModeFilter = ptModeFilter;
//	}

}
