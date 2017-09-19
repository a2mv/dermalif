package com.pl10.dermalif.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "cita")
public class Cita {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(unique = true, nullable = false, updatable = false)
	private String id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "person", nullable = false)
	private Person person;
	@Column(name = "title", nullable = false)
	private String title;
	@Type(type = "timestamp")
	@Column(name = "start", nullable = false)
	private Date start;
	@Type(type = "timestamp")
	@Column(name = "end", nullable = false)
	private Date end;
	@Column(name = "duration", nullable = false)
	private Short duration;
	@Column(name = "backgroundColor", nullable = false)
	private String backgroundColor;
	@Column(name = "borderColor", nullable = false)
	private String borderColor;

	public Cita() {
		super();
	}

	public Cita(String id, Person person, String title, Date start, Date end, Short duration, String backgroundColor,
			String borderColor) {
		super();
		this.id = id;
		this.person = person;
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

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
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
		return "Cita [id=" + id + ", person=" + person + ", title=" + title + ", start=" + start + ", end=" + end
				+ ", duration=" + duration + ", backgroundColor=" + backgroundColor + ", borderColor=" + borderColor
				+ "]";
	}

}
