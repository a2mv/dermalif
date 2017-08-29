package com.pl10.dermalif.repository.query;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.pl10.dermalif.entity.Historia;
import com.pl10.dermalif.entity.Person;
import com.pl10.dermalif.entity.QHistoria;
import com.querydsl.jpa.impl.JPAQuery;

@Repository("historiaDslRepository")
public class HistoriaDslRepository {

	private QHistoria qHistoria = QHistoria.historia;
	
	@PersistenceContext
	private EntityManager em;
	
	public List<Person> searchAllDataHistoria(String str, int page){
		JPAQuery<Historia> query = new JPAQuery<Historia>(em);
		page = (page*10)-10;
		List<Person> persons = query.select(qHistoria.ingreso.person).from(qHistoria)
				.groupBy(qHistoria.ingreso.person)
				.offset(page).limit(10).fetch();
		return persons;
	}
	
	public Long countSearchAllDataHistoria(String str){
		JPAQuery<Historia> query = new JPAQuery<Historia>(em);
		Long count = query.select(qHistoria.ingreso.person).from(qHistoria)
				.groupBy(qHistoria.ingreso.person)
				.fetchCount();
		return count;
	}
}
