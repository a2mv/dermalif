package com.pl10.dermalif.service;

import java.util.List;

import com.pl10.dermalif.entity.Historia;
import com.pl10.dermalif.entity.Ingreso;
import com.pl10.dermalif.entity.Person;
import com.pl10.dermalif.model.HistoriaModel;
import com.pl10.dermalif.model.IngresoModel;
import com.pl10.dermalif.model.PersonModel;

public interface HistoriaService {

	public abstract HistoriaModel addHistoriaModel(HistoriaModel historiaModel);
	
	public abstract HistoriaModel findHistoriaModelById(String id);
	
	public abstract Historia findHistoriaById(String id);
	
	public abstract List<HistoriaModel> findAllHistoriaModelByIngreso(IngresoModel ingresoModel);
	
	public abstract List<Historia> findAllHistoriaByIngreso(Ingreso ingreso);
	
	public abstract List<PersonModel> findAllPersonModelWithHistoria(String str, int page);
	
	public abstract List<Person> findAllPersonWithHistoria(String str, int page);
	
	public abstract Long countFindAllPersonModelWithHistoria(String str);
	
	public abstract List<HistoriaModel> findAllHistoriaModelByPerson(String id);
	
	public abstract List<Historia> findAllHistoriaByPerson(String id);
	
}
