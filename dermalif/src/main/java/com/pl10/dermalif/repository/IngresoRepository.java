package com.pl10.dermalif.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pl10.dermalif.entity.Ingreso;
import com.pl10.dermalif.entity.Person;

@Repository("ingresoRepository")
public interface IngresoRepository extends JpaRepository<Ingreso, Serializable>{
	
	public abstract Ingreso findById(String id);
	
	public abstract List<Ingreso> findByPerson(Person person, Pageable pageable);

}
