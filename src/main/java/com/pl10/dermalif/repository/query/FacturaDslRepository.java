package com.pl10.dermalif.repository.query;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.pl10.dermalif.entity.Factura;
import com.pl10.dermalif.entity.QFactura;
import com.querydsl.jpa.impl.JPAQuery;

@Repository("facturaDslRepository")
public class FacturaDslRepository {

	private QFactura qFactura = QFactura.factura;
	
	@PersistenceContext
	private EntityManager em;
	
	public Factura searchLastFactura() {
		JPAQuery<Factura> query = new JPAQuery<Factura>(em);
		Factura factura = query.select(qFactura).from(qFactura).orderBy(qFactura.idfactura.desc()).fetchFirst();
		return factura;
	}
}
