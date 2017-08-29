package com.pl10.dermalif.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "country")
public class Country {

	@Id
	@GeneratedValue
	@Column(unique = true, nullable = false, updatable=false)
	private int id;
	@Column(name= "name", nullable = false)
	private String name;

	public Country() {
		super();
	}

	public Country(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Country(String name) {
		super();
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
