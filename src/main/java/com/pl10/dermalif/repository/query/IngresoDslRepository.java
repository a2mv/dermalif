package com.pl10.dermalif.repository.query;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.pl10.dermalif.entity.Ingreso;
import com.pl10.dermalif.entity.Person;
import com.pl10.dermalif.entity.QIngreso;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;

@Repository("ingresoDslRepository")
public class IngresoDslRepository {

	private QIngreso qIngreso = QIngreso.ingreso;
	
	@PersistenceContext
	private EntityManager em;
	
	public List<Ingreso> findFirst10IngresoByPerson(Person person){
		JPAQuery<Ingreso> query = new JPAQuery<Ingreso>(em);
		List<Ingreso> ingresos = query.select(qIngreso).from(qIngreso)
				.where(qIngreso.person.eq(person)).orderBy(qIngreso.fecha.desc()).limit(10)
				.fetch();		
		return ingresos;
	}
	
	public Long findMaxValueNorden(){
		JPAQuery<Ingreso> query = new JPAQuery<Ingreso>(em);
		QIngreso i = new QIngreso("i");
		Ingreso ingreso = query.select(qIngreso).from(qIngreso)
		.where(qIngreso.norden.eq(JPAExpressions.select(i.norden.max()).from(i)))
				.fetchFirst();		
		return ingreso.getNorden();
	}
	
	public List<Ingreso> searchAllDataIngresos(String str, int page){
		JPAQuery<Ingreso> query = new JPAQuery<Ingreso>(em);
		page = (page*10)-10;
		List<Ingreso> ingresos = query.select(qIngreso).from(qIngreso)
				.where(qIngreso.empresa.contains(str)
						.or(qIngreso.norden.stringValue().contains(str))
						.or(qIngreso.person.ndocumento.contains(str))
						.or(qIngreso.person.firstname.contains(str))
						.or(qIngreso.person.firstname2.contains(str))
						.or(qIngreso.person.lastname.contains(str))
						.or(qIngreso.person.lastname2.contains(str))
						).offset(page).limit(10).fetch();
		return ingresos;
	}
	
	public Long countSearchAllDataIngresos(String str){
		JPAQuery<Ingreso> query = new JPAQuery<Ingreso>(em);
		long count = query.select(qIngreso).from(qIngreso)
				.where(qIngreso.empresa.contains(str)
						.or(qIngreso.norden.stringValue().contains(str))
						.or(qIngreso.person.ndocumento.contains(str))
						.or(qIngreso.person.firstname.contains(str))
						.or(qIngreso.person.firstname2.contains(str))
						.or(qIngreso.person.lastname.contains(str))
						.or(qIngreso.person.lastname2.contains(str))
						).fetchCount();
		return count;
	}

}
