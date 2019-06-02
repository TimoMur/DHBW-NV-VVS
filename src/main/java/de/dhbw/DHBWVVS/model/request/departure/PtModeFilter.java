package de.dhbw.DHBWVVS.model.request.departure;

public class PtModeFilter {

	private boolean exclude;
	private PtMode ptMode;

	public boolean isExclude() {
		return exclude;
	}

	public void setExclude(boolean exclude) {
		this.exclude = exclude;
	}

	public PtMode getPtMode() {
		return ptMode;
	}

	public void setPtMode(PtMode ptMode) {
		this.ptMode = ptMode;
	}

}
