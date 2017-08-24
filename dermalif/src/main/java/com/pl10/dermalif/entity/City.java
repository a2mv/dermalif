package com.pl10.dermalif.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "city")
public class City {

	@Id
	@GeneratedValue
	@Column(unique = true, nullable = false, updatable = false)
	private int id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "country", nullable = false)
	private Country country;
	@Column(name = "name", nullable = false)
	private String name;

	public City() {
		super();
	}

	public City(int id, Country country, String name) {
		super();
		this.id = id;
		this.country = country;
		this.name = name;
	}

	public City(Country country, String name) {
		super();
		this.country = country;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "City [id=" + id + ", country=" + country + ", name=" + name + "]";
	}

}
