package de.dhbw.DHBWVVS.model.departure;

import java.util.List;

public class PtSituation {
	private String creationTime;
	private String participantRef;
	private String situationNumber;
	private String version;
	private String source;
	private String sourceType;
	private String progress;
	private ValidityPeriod validityPeriod;
	private String unknownReason;
	private String priority;
	private String audience;
	private String scopeType;
	private String planned;
	private String language;
	private String summary;
	private String description;
	private String detail;
	private List<Affect> affects;

	public String getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}

	public String getParticipantRef() {
		return participantRef;
	}

	public void setParticipantRef(String participantRef) {
		this.participantRef = participantRef;
	}

	public String getSituationNumber() {
		return situationNumber;
	}

	public void setSituationNumber(String situationNumber) {
		this.situationNumber = situationNumber;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public String getProgress() {
		return progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}

	public ValidityPeriod getValidityPeriod() {
		return validityPeriod;
	}

	public void setValidityPeriod(ValidityPeriod validityPeriod) {
		this.validityPeriod = validityPeriod;
	}

	public String getUnknownReason() {
		return unknownReason;
	}

	public void setUnknownReason(String unknownReason) {
		this.unknownReason = unknownReason;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getAudience() {
		return audience;
	}

	public void setAudience(String audience) {
		this.audience = audience;
	}

	public String getScopeType() {
		return scopeType;
	}

	public void setScopeType(String scopeType) {
		this.scopeType = scopeType;
	}

	public String getPlanned() {
		return planned;
	}

	public void setPlanned(String planned) {
		this.planned = planned;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public List<Affect> getAffects() {
		return affects;
	}

	public void setAffects(List<Affect> affects) {
		this.affects = affects;
	}

}
