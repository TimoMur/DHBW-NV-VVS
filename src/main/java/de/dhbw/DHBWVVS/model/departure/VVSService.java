package de.dhbw.DHBWVVS.model.departure;

import de.dhbw.DHBWVVS.model.location.Mode;

public class VVSService {

	private String operatingDayRef;
	private String journeyRef;
	private String lineRef;
	private String directionRef;
	private Mode mode;
	private String modeName;
	private String publishedLineName;
	private String operatorRef;
	private String routeDescription;
	private String originStopPointRef;
	private String originText;
	private String destinationStopPointRef;
	private String destinationText;
	private String language;

	public String getOperatingDayRef() {
		return operatingDayRef;
	}

	public void setOperatingDayRef(String operatingDayRef) {
		this.operatingDayRef = operatingDayRef;
	}

	public String getJourneyRef() {
		return journeyRef;
	}

	public void setJourneyRef(String journeyRef) {
		this.journeyRef = journeyRef;
	}

	public String getLineRef() {
		return lineRef;
	}

	public void setLineRef(String lineRef) {
		this.lineRef = lineRef;
	}

	public String getDirectionRef() {
		return directionRef;
	}

	public void setDirectionRef(String directionRef) {
		this.directionRef = directionRef;
	}

	public Mode getMode() {
		return mode;
	}

	public void setMode(Mode mode) {
		this.mode = mode;
	}

	public String getModeName() {
		return modeName;
	}

	public void setModeName(String modeName) {
		this.modeName = modeName;
	}

	public String getPublishedLineName() {
		return publishedLineName;
	}

	public void setPublishedLineName(String publishedLineName) {
		this.publishedLineName = publishedLineName;
	}

	public String getOperatorRef() {
		return operatorRef;
	}

	public void setOperatorRef(String operatorRef) {
		this.operatorRef = operatorRef;
	}

	public String getRouteDescription() {
		return routeDescription;
	}

	public void setRouteDescription(String routeDescription) {
		this.routeDescription = routeDescription;
	}

	public String getOriginStopPointRef() {
		return originStopPointRef;
	}

	public void setOriginStopPointRef(String originStopPointRef) {
		this.originStopPointRef = originStopPointRef;
	}

	public String getOriginText() {
		return originText;
	}

	public void setOriginText(String originText) {
		this.originText = originText;
	}

	public String getDestinationStopPointRef() {
		return destinationStopPointRef;
	}

	public void setDestinationStopPointRef(String destinationStopPointRef) {
		this.destinationStopPointRef = destinationStopPointRef;
	}

	public String getDestinationText() {
		return destinationText;
	}

	public void setDestinationText(String destinationText) {
		this.destinationText = destinationText;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

}
