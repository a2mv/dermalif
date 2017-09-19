package com.pl10.dermalif.repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pl10.dermalif.entity.Cita;

@Repository("citaRepository")
public interface CitaRepository extends JpaRepository<Cita, Serializable>{
	
	public abstract Cita findById(String id);
	
	public abstract List<Cita> findByStartBetween(Date start, Date end);

}
