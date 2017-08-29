package com.pl10.dermalif.repository.query;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.pl10.dermalif.entity.Person;
import com.pl10.dermalif.entity.QPerson;
import com.querydsl.jpa.impl.JPAQuery;

@Repository("personDslRepository")
public class PersonDslRepository {
	
	private QPerson qPerson = QPerson.person;
	
	@PersistenceContext
	private EntityManager em;
	
	public List<Person> searchAllDataPersons(String str, int page){
		JPAQuery<Person> query = new JPAQuery<Person>(em);
		page = (page*10)-10;
		List<Person> persons = query.select(qPerson).from(qPerson)
				.where(qPerson.ndocumento.contains(str)
						.or(qPerson.firstname.contains(str))
						.or(qPerson.firstname2.contains(str))
						.or(qPerson.lastname.contains(str))
						.or(qPerson.lastname2.contains(str))						
						).offset(page).limit(10).fetch();
		return persons;
	}
	
	public long countSearchAllDataPersons(String str){
		JPAQuery<Person> query = new JPAQuery<Person>(em);
		long count = query.select(qPerson).from(qPerson)
				.where(qPerson.ndocumento.contains(str)
						.or(qPerson.firstname.contains(str))
						.or(qPerson.firstname2.contains(str))
						.or(qPerson.lastname.contains(str))
						.or(qPerson.lastname2.contains(str))						
						).fetchCount();
		return count;
	}

}

