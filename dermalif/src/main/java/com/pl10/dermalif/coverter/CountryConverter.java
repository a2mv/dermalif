package com.pl10.dermalif.coverter;

import org.springframework.stereotype.Component;

import com.pl10.dermalif.entity.Country;
import com.pl10.dermalif.model.CountryModel;

@Component("countryConverter")
public class CountryConverter {
	
	public Country countryModelToCountry(CountryModel countryModel){
		Country country = new Country();
		country.setId(countryModel.getId());
		country.setName(countryModel.getName());
		return country;
	}
	
	public CountryModel countryToCountryModel(Country country){
		CountryModel countryModel = new CountryModel();
		countryModel.setId(country.getId());
		countryModel.setName(country.getName());
		return countryModel;
	}

}
