package de.dhbw.DHBWVVS.model;

public class StopPoint extends Point {

	private String ref;
	private String localityRef;
	private String text;
	private String language;

	public String getLocalityRef() {
		return localityRef;
	}

	public void setLocalityRef(String localityRef) {
		this.localityRef = localityRef;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

}
