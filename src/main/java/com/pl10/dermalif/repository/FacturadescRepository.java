package com.pl10.dermalif.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pl10.dermalif.entity.Factura;
import com.pl10.dermalif.entity.Facturadesc;

@Repository("facturadescRepository")
public interface FacturadescRepository extends JpaRepository<Facturadesc, Serializable>{

	public abstract Facturadesc findById(String id);
	
	public abstract List<Facturadesc> findByFactura(Factura factura); 
	
	
}
