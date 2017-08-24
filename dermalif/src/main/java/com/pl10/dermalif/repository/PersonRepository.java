package com.pl10.dermalif.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pl10.dermalif.entity.Person;
import com.pl10.dermalif.enums.TypeDocument;

@Repository("personRepository")
public interface PersonRepository extends JpaRepository<Person, Serializable> {

	public abstract Person findById(String id);
	
	public abstract List<Person> findByTdocumentoAndNdocumento(TypeDocument document, String ndocumento);
	
	public abstract Person findByEmail(String email);
}
