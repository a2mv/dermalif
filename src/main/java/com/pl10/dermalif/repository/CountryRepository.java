package com.pl10.dermalif.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pl10.dermalif.entity.Country;

@Repository("countryRepository")
public interface CountryRepository extends JpaRepository<Country, Serializable>{

	public abstract Country findByName(String name);
	
}
