package com.pl10.dermalif.model;

public class CountryModel {

	private int id;
	private String name;

	public CountryModel() {
		super();
	}

	public CountryModel(int id, String name) {
		super();
		this.id = id;
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

	@Override
	public String toString() {
		return "CountryModel [id=" + id + ", name=" + name + "]";
	}

}
