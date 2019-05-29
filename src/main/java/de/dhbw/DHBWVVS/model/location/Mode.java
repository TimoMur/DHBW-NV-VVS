package de.dhbw.DHBWVVS.model.location;

import java.util.List;

public class Mode {

	private String name;
	private List<String> submodes;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getSubmodes() {
		return submodes;
	}

	public void setSubmodes(List<String> submodes) {
		this.submodes = submodes;
	}

}
