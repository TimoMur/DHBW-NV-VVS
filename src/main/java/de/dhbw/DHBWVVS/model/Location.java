package de.dhbw.DHBWVVS.model;

import java.util.List;

public class Location {

	private Point point;
	private String name;
	private String language;
	private GeoPosition geoposition;
	private double probability;
	private boolean complete;
	private List<Mode> modes;

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public GeoPosition getGeoposition() {
		return geoposition;
	}

	public void setGeoposition(GeoPosition geoposition) {
		this.geoposition = geoposition;
	}

	public double getProbability() {
		return probability;
	}

	public void setProbability(double probability) {
		this.probability = probability;
	}

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	public List<Mode> getModes() {
		return modes;
	}

	public void setModes(List<Mode> modes) {
		this.modes = modes;
	}

}