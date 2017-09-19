package com.pl10.dermalif.model;

public class CitaJsonObject {

	String id;
	String id_pa;
	String title;
	String start;
	String end;
	Boolean allDay;
	String backgroundColor;
	String borderColor;
	String duration;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId_pa() {
		return id_pa;
	}

	public void setId_pa(String id_pa) {
		this.id_pa = id_pa;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public boolean getAllDay() {
		return false;
	}

	public void setAllDay(boolean allDay) {
		this.allDay = allDay;
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

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}
	

}
