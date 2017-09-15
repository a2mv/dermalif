package com.pl10.dermalif.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.pl10.dermalif.coverter.PersonConverter;
import com.pl10.dermalif.entity.Person;
import com.pl10.dermalif.model.PersonModel;
import com.pl10.dermalif.repository.PersonRepository;
import com.pl10.dermalif.repository.query.PersonDslRepository;
import com.pl10.dermalif.service.CityService;
import com.pl10.dermalif.service.PersonService;

@Service("personService")
public class PersonServiceImpl implements PersonService {

	@Autowired
	@Qualifier("personRepository")
	private PersonRepository personRepository;
	
	@Autowired
	@Qualifier("personDslRepository")
	private PersonDslRepository personDslRepository;

	@Autowired
	@Qualifier("personConverter")
	private PersonConverter personConverter;
	
	@Autowired
	@Qualifier("cityService")
	private CityService cityService;

	@Override
	public PersonModel addPersonModel(PersonModel personModel) {
		Person person = personRepository.save(personConverter.personModelToPerson(personModel));
		return personConverter.personToPersonModel(person);
	}

	@Override
	public List<PersonModel> listAllPersonModel() {
		List<Person> persons = personRepository.findAll();
		List<PersonModel> personModels = new ArrayList<PersonModel>();
		for (Person person : persons) {
			personModels.add(personConverter.personToPersonModel(person));
		}
		return personModels;
	}

	@Override
	public Person findPersonById(String id) {		
		return personRepository.findById(id);
	}

	@Override
	public PersonModel findPersonModelById(String id) {
		return personConverter.personToPersonModel(findPersonById(id));
	}

	@Override
	public List<PersonModel> searchListPersonModel(String str, int page) {
		List<Person> persons = personDslRepository.searchAllDataPersons(str, page);
		List<PersonModel> personModels = new ArrayList<PersonModel>();
		for (Person person : persons) {
			personModels.add(personConverter.personToPersonModel(person));
		}
		return personModels;
	}

	@Override
	public Long countSearchListPersonModel(String str) {
		return personDslRepository.countSearchAllDataPersons(str);
	}

	@Override
	public PersonModel repairCityModel(PersonModel personModel) {
		if(personModel.getCnacimiento()==null){
			personModel.setCnacimiento(cityService.findCityModelById(55));			
		}else{
			personModel.setCnacimiento(cityService.findCityModelById(personModel.getCnacimiento().getId()));
		}
		if(personModel.getCresidencia()==null){
			personModel.setCresidencia(cityService.findCityModelById(55));			
		}else{
			personModel.setCresidencia(cityService.findCityModelById(personModel.getCresidencia().getId()));
		}
		return personModel;
	}

	@Override
	public PersonModel findPersonModelByEmail(String email) {
		return personConverter.personToPersonModel(findPersonByEmail(email));
	}

	@Override
	public Person findPersonByEmail(String email) {
		return personRepository.findByEmail(email);
	}
	
	

}
