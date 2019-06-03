package de.dhbw.DHBWVVS.model.departure;

import java.time.LocalDateTime;

public class ThisCall {

	private String stopPointRef;

	private String stopPointName;
	private String language;
	private String plannedBy;
	private LocalDateTime timetabledTime;
	private LocalDateTime estimatedTime;
	private int stopSeqNumber;
	private String participantRef;
	private int situationNumber;

	public String getStopPointRef() {
		return stopPointRef;
	}

	public void setStopPointRef(String stopPointRef) {
		this.stopPointRef = stopPointRef;
	}

	public String getStopPointName() {
		return stopPointName;
	}

	public void setStopPointName(String stopPointName) {
		this.stopPointName = stopPointName;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getPlannedBy() {
		return plannedBy;
	}

	public void setPlannedBy(String plannedBy) {
		this.plannedBy = plannedBy;
	}

	public LocalDateTime getTimetabledTime() {
		return timetabledTime;
	}

	public void setTimetabledTime(LocalDateTime timetabledTime) {
		this.timetabledTime = timetabledTime;
	}

	public LocalDateTime getEstimatedTime() {
		return estimatedTime;
	}

	public void setEstimatedTime(LocalDateTime estimatedTime) {
		this.estimatedTime = estimatedTime;
	}

	public int getStopSeqNumber() {
		return stopSeqNumber;
	}

	public void setStopSeqNumber(int stopSeqNumber) {
		this.stopSeqNumber = stopSeqNumber;
	}

	public String getParticipantRef() {
		return participantRef;
	}

	public void setParticipantRef(String participantRef) {
		this.participantRef = participantRef;
	}

	public int getSituationNumber() {
		return situationNumber;
	}

	public void setSituationNumber(int situationNumber) {
		this.situationNumber = situationNumber;
	}

}
