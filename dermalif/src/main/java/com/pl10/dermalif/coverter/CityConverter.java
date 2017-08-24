package com.pl10.dermalif.coverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.pl10.dermalif.entity.City;
import com.pl10.dermalif.model.CityModel;
import com.pl10.dermalif.repository.CityRepository;

@Component("cityConverter")
public class CityConverter {
	
	@Autowired
	@Qualifier("countryConverter")
	private CountryConverter countryConverter;
	
	@Autowired
	@Qualifier("cityRepository")
	private CityRepository cityRepository;
	
	public City cityModelToCity(CityModel cityModel){
		City city = new City();
		if(cityModel.getCountryModel()==null){
			cityModel = cityToCityModel(cityRepository.findById(cityModel.getId()));
		}
		city.setCountry(countryConverter.countryModelToCountry(cityModel.getCountryModel()));
		city.setId(cityModel.getId());
		city.setName(cityModel.getName());
		return city;
	}
	
	public CityModel cityToCityModel(City city){
		CityModel cityModel = new CityModel();
		cityModel.setCountryModel(countryConverter.countryToCountryModel(city.getCountry()));
		cityModel.setId(city.getId());
		cityModel.setName(city.getName());
		return cityModel;
	}

}
