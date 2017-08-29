package com.pl10.dermalif.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.pl10.dermalif.coverter.CityConverter;
import com.pl10.dermalif.entity.City;
import com.pl10.dermalif.model.CityAjaxResponse;
import com.pl10.dermalif.model.CityModel;
import com.pl10.dermalif.repository.CityRepository;
import com.pl10.dermalif.repository.query.CityDslRepository;
import com.pl10.dermalif.service.CityService;

@Service("cityService")
public class CityServiceImpl implements CityService {

	@Autowired
	@Qualifier("cityDslRepository")
	private CityDslRepository cityDslRepository;
	
	@Autowired
	@Qualifier("cityRepository")
	private CityRepository cityRepository;
	
	@Autowired
	@Qualifier("cityConverter")
	private CityConverter cityConverter;
	
	@Override
	public List<CityAjaxResponse> listCityAjaxResponse(String str) {
		List<City> cities = cityDslRepository.searchCityByNameOrByCountry(str);
		List<CityAjaxResponse> cityAjaxs = new ArrayList<CityAjaxResponse>();
		for(City city : cities){
			cityAjaxs.add(new CityAjaxResponse(city.getId(), city.getCountry().getName()+" | "+city.getName()));
		}
		return cityAjaxs;
	}

	@Override
	public CityModel findCityModelById(int id) {
		return cityConverter.cityToCityModel(findCityById(id));
	}

	@Override
	public City findCityById(int id) {
		return cityRepository.findById(id);
	}

	

}
