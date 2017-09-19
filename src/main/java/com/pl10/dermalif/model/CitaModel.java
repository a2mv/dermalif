package com.pl10.dermalif.model;

import java.util.Date;


public class CitaModel {

	private String id;
	private PersonModel personModel;
	private String title;
	private Date start;
	private Date end;
	private Short duration;
	private String backgroundColor;
	private String borderColor;
	public CitaModel() {
		super();
	}
	public CitaModel(String id, PersonModel personModel, String title, Date start, Date end, Short duration,
			String backgroundColor, String borderColor) {
		super();
		this.id = id;
		this.personModel = personModel;
		this.title = title;
		this.start = start;
		this.end = end;
		this.duration = duration;
		this.backgroundColor = backgroundColor;
		this.borderColor = borderColor;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public PersonModel getPersonModel() {
		return personModel;
	}
	public void setPersonModel(PersonModel personModel) {
		this.personModel = personModel;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public Short getDuration() {
		return duration;
	}
	public void setDuration(Short duration) {
		this.duration = duration;
	}
	public String getBackgroundColor() {
		return backgroundColor;
	}
	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	public String getBorderColor() {
		return borderColor;
	}
	public void setBorderColor(String borderColor) {
		this.borderColor = borderColor;
	}
	@Override
	public String toString() {
		return "CitaModel [id=" + id + ", personModel=" + personModel + ", title=" + title + ", start=" + start
				+ ", end=" + end + ", duration=" + duration + ", backgroundColor=" + backgroundColor + ", borderColor="
				+ borderColor + "]";
	}
	
}
