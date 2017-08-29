package com.pl10.dermalif.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.pl10.dermalif.coverter.IngresoConverter;
import com.pl10.dermalif.coverter.PersonConverter;
import com.pl10.dermalif.entity.Ingreso;
import com.pl10.dermalif.entity.Person;
import com.pl10.dermalif.model.IngresoModel;
import com.pl10.dermalif.model.PersonModel;
import com.pl10.dermalif.repository.IngresoRepository;
import com.pl10.dermalif.repository.query.IngresoDslRepository;
import com.pl10.dermalif.service.IngresoService;

@Service("ingresoService")
public class IngresoServiceImpl implements IngresoService {
	
	@Autowired
	@Qualifier("ingresoRepository")
	private IngresoRepository ingresoRepository;
	
	@Autowired
	@Qualifier("ingresoDslRepository")
	private IngresoDslRepository ingresoDslRepository;
	
	@Autowired
	@Qualifier("ingresoConverter")
	private IngresoConverter ingresoConverter;
	
	@Autowired
	@Qualifier("personConverter")
	private PersonConverter personConverter;
	

	@Override
	public IngresoModel addIngresoModel(IngresoModel ingresoModel) {
		Ingreso ingreso = ingresoRepository.save(ingresoConverter.ingresoModelToIngreso(ingresoModel));
		return ingresoConverter.ingresoToIngresoModel(ingreso);
	}

	@Override
	public List<IngresoModel> listAllIngresoModel() {
		List<Ingreso> ingresos = ingresoRepository.findAll();
		List<IngresoModel> ingresoModels = new ArrayList<IngresoModel>();
		for(Ingreso ingreso : ingresos){
			ingresoModels.add(ingresoConverter.ingresoToIngresoModel(ingreso));
		}
		return ingresoModels;
	}

	@Override
	public List<IngresoModel> listPageableIngresoModel(PersonModel personModel) {
		Person person = personConverter.personModelToPerson(personModel);
		List<Ingreso> ingresos = ingresoDslRepository.findFirst10IngresoByPerson(person);
		List<IngresoModel> ingresoModels = new ArrayList<IngresoModel>();
		for(Ingreso ingreso : ingresos){
			ingresoModels.add(ingresoConverter.ingresoToIngresoModel(ingreso));
		}
		return ingresoModels;
	}

	@Override
	public IngresoModel findIngresoModelById(String id) {
		return ingresoConverter.ingresoToIngresoModel(findIngresoById(id));
	}

	@Override
	public Ingreso findIngresoById(String id) {
		return ingresoRepository.findById(id);
	}

	@Override
	public Long findMaxValueNorden() {
		return ingresoDslRepository.findMaxValueNorden();
	}

	@Override
	public Long countAllIngresos() {
		return ingresoRepository.count();
	}

	@Override
	public List<IngresoModel> searchListIngresoModel(String str, int page) {
		List<Ingreso> ingresos = ingresoDslRepository.searchAllDataIngresos(str, page);
		List<IngresoModel> ingresoModels = new ArrayList<IngresoModel>();
		for (Ingreso ingreso : ingresos) {
			ingresoModels.add(ingresoConverter.ingresoToIngresoModel(ingreso));
		}
		return ingresoModels;
	}

	@Override
	public Long countSearchListIngresoModel(String str) {
		return ingresoDslRepository.countSearchAllDataIngresos(str);
	}

}
