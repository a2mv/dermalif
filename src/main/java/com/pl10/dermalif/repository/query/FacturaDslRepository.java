package com.pl10.dermalif.repository.query;

import java.util.List;

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
	
	public List<Factura> searchAllDataFacturas(String str, int page){
		JPAQuery<Factura> query = new JPAQuery<Factura>(em);
		page = (page*10)-10;
		List<Factura> facturas = query.select(qFactura).from(qFactura)
				.where(qFactura.ingreso.norden.stringValue().contains(str)
						.or(qFactura.idfactura.stringValue().contains(str))
						.or(qFactura.ingreso.person.ndocumento.contains(str))
						.or(qFactura.ingreso.person.firstname.contains(str))
						.or(qFactura.ingreso.person.firstname2.contains(str))
						.or(qFactura.ingreso.person.lastname.contains(str))
						.or(qFactura.ingreso.person.lastname2.contains(str))
						).offset(page).limit(10).fetch();
		return facturas;
	}
	
	public Long countSearchAllDataFacturas(String str){
		JPAQuery<Factura> query = new JPAQuery<Factura>(em);
		long count = query.select(qFactura).from(qFactura)
				.where(qFactura.ingreso.norden.stringValue().contains(str)
						.or(qFactura.idfactura.stringValue().contains(str))
						.or(qFactura.ingreso.person.ndocumento.contains(str))
						.or(qFactura.ingreso.person.firstname.contains(str))
						.or(qFactura.ingreso.person.firstname2.contains(str))
						.or(qFactura.ingreso.person.lastname.contains(str))
						.or(qFactura.ingreso.person.lastname2.contains(str))
						).fetchCount();
		return count;
	}

}
