package com.pl10.dermalif.model;

public class CityModel {

	private int id;
	private CountryModel countryModel;
	private String name;

	public CityModel() {
		super();
	}

	public CityModel(int id, CountryModel countryModel, String name) {
		super();
		this.id = id;
		this.countryModel = countryModel;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CountryModel getCountryModel() {
		return countryModel;
	}

	public void setCountryModel(CountryModel countryModel) {
		this.countryModel = countryModel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "CityModel [id=" + id + ", countryModel=" + countryModel + ", name=" + name + "]";
	}

}
