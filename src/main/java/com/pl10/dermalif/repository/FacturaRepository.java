package com.pl10.dermalif.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pl10.dermalif.entity.Factura;
import com.pl10.dermalif.entity.Ingreso;

@Repository("facturaRepository")
public interface FacturaRepository extends JpaRepository<Factura, Serializable>{

	public abstract Factura findById(String id);
	
	public abstract Factura findByIdfactura(Integer id);
	
	public abstract List<Factura> findByIngreso(Ingreso ingreso);
	
}
