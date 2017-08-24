package com.pl10.dermalif.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pl10.dermalif.entity.Historia;
import com.pl10.dermalif.entity.Ingreso;

@Repository("historiaRepository")
public interface HistoriaRepository extends JpaRepository<Historia, Serializable>{
	
	public abstract Historia findById(String id);
	
	public abstract List<Historia> findByIngreso(Ingreso ingreso);

}
