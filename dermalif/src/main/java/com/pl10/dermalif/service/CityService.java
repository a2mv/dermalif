package com.pl10.dermalif.service;

import java.util.List;

import com.pl10.dermalif.entity.City;
import com.pl10.dermalif.model.CityAjaxResponse;
import com.pl10.dermalif.model.CityModel;

public interface CityService {
	
	public abstract CityModel findCityModelById(int id);
	
	public abstract City findCityById(int id);

	public abstract List<CityAjaxResponse> listCityAjaxResponse(String str);
	
}
