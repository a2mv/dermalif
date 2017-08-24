package com.pl10.dermalif.service;

import java.util.List;

import com.pl10.dermalif.entity.Person;
import com.pl10.dermalif.model.PersonModel;

public interface PersonSevice {
	
	public abstract PersonModel addPersonModel(PersonModel personModel);
	
	public abstract List<PersonModel> listAllPersonModel();
	
	public abstract Person findPersonById(String id);
	
	public abstract PersonModel findPersonModelById(String id);
	
	public abstract List<PersonModel> searchListPersonModel(String str, int page);
	
	public abstract Long countSearchListPersonModel(String str);
	
	public abstract PersonModel repairCityModel(PersonModel personModel);

}
