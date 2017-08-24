package com.pl10.dermalif.repository.query;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.pl10.dermalif.entity.City;
import com.pl10.dermalif.entity.QCity;
import com.querydsl.jpa.impl.JPAQuery;

@Repository("cityDslRepository")
public class CityDslRepository {
	
	private QCity qCity = QCity.city;
	
	@PersistenceContext
	private EntityManager em;
	
	public List<City> searchCityByNameOrByCountry(String str){
		JPAQuery<City> query = new JPAQuery<City>(em);
		List<City> cities = query.select(qCity).from(qCity).
				where(qCity.name.contains(str).
						or(qCity.country.name.contains(str))).limit(7).fetch();
		return cities;
	}

}
