package com.pl10.dermalif.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pl10.dermalif.entity.City;

@Repository("cityRepository")
public interface CityRepository extends JpaRepository<City, Serializable> {

	public abstract City findById(int id);
}
