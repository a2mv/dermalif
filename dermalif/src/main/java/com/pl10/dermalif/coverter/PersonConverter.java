package com.pl10.dermalif.coverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.pl10.dermalif.entity.Person;
import com.pl10.dermalif.model.PersonModel;

@Component("personConverter")
public class PersonConverter {
	
	@Autowired
	@Qualifier("cityConverter")
	private CityConverter cityConverter;

	public Person personModelToPerson(PersonModel personModel){
		Person person = new Person();
		person.setBirthdate(personModel.getBirthdate());
		person.setCnacimiento(cityConverter.cityModelToCity(personModel.getCnacimiento()));
		person.setCresidencia(cityConverter.cityModelToCity(personModel.getCresidencia()));
		person.setDireccion(personModel.getDireccion());
		person.setEmail(personModel.getEmail());
		person.setFirstname(personModel.getFirstname());
		person.setFirstname2(personModel.getFirstname2());
		person.setId(personModel.getId());
		person.setLastname(personModel.getLastname());
		person.setLastname2(personModel.getLastname2());
		person.setNdocumento(personModel.getNdocumento());
		person.setTcivilstatus(personModel.getTcivilstatus());
		person.setTdocumento(personModel.getTdocumento());
		person.setTelefono(personModel.getTelefono());
		person.setTsexo(personModel.getTsexo());
		person.setImageprofile(personModel.getImageprofile());
		return person;
	}
	
	public PersonModel personToPersonModel(Person person){
		PersonModel personModel = new PersonModel();
		personModel.setBirthdate(person.getBirthdate());
		personModel.setCnacimiento(cityConverter.cityToCityModel(person.getCnacimiento()));
		personModel.setCresidencia(cityConverter.cityToCityModel(person.getCresidencia()));
		personModel.setDireccion(person.getDireccion());
		personModel.setEmail(person.getEmail());
		personModel.setFirstname(person.getFirstname());
		personModel.setFirstname2(person.getFirstname2());
		personModel.setId(person.getId());
		personModel.setLastname(person.getLastname());
		personModel.setLastname2(person.getLastname2());
		personModel.setNdocumento(person.getNdocumento());
		personModel.setTcivilstatus(person.getTcivilstatus());
		personModel.setTdocumento(person.getTdocumento());
		personModel.setTelefono(person.getTelefono());
		personModel.setTsexo(person.getTsexo());
		personModel.setImageprofile(person.getImageprofile());
		return personModel;
	}
	
}
