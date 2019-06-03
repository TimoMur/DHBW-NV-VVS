package de.dhbw.DHBWVVS.model.departure;

public abstract class Affect {

	private String type;
	
	public Affect(String type) {
		super();
		this.type = type;
	}

	public String getType() {
		return type;
	}

}
