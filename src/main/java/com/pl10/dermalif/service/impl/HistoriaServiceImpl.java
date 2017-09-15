package com.pl10.dermalif.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.pl10.dermalif.coverter.HistoriaConverter;
import com.pl10.dermalif.coverter.IngresoConverter;
import com.pl10.dermalif.coverter.PersonConverter;
import com.pl10.dermalif.entity.Factura;
import com.pl10.dermalif.entity.Historia;
import com.pl10.dermalif.entity.Ingreso;
import com.pl10.dermalif.entity.Person;
import com.pl10.dermalif.enums.TypeIngresoStatus;
import com.pl10.dermalif.model.HistoriaModel;
import com.pl10.dermalif.model.IngresoModel;
import com.pl10.dermalif.model.PersonModel;
import com.pl10.dermalif.repository.FacturaRepository;
import com.pl10.dermalif.repository.HistoriaRepository;
import com.pl10.dermalif.repository.IngresoRepository;
import com.pl10.dermalif.repository.query.HistoriaDslRepository;
import com.pl10.dermalif.service.HistoriaService;

@Service("historiaService")
public class HistoriaServiceImpl implements HistoriaService {

	@Autowired
	@Qualifier("historiaConverter")
	HistoriaConverter historiaConverter;
	
	@Autowired
	@Qualifier("ingresoRepository")
	IngresoRepository ingresoRepository;
	
	@Autowired
	@Qualifier("historiaRepository")
	HistoriaRepository historiaRepository;
	
	@Autowired
	@Qualifier("historiaDslRepository")
	HistoriaDslRepository historiaDslRepository;
	
	@Autowired
	@Qualifier("ingresoConverter")
	IngresoConverter ingresoConverter;
	
	@Autowired
	@Qualifier("personConverter")
	PersonConverter personConverter;
	
	@Autowired
	@Qualifier("facturaRepository")
	FacturaRepository facturaRepository; 
	
	@Override
	public HistoriaModel addHistoriaModel(HistoriaModel historiaModel) {
		Historia historia = historiaRepository.save(historiaConverter.historiaModelToHistoria(historiaModel));
		List<Factura> facturas = facturaRepository.findByIngreso(historia.getIngreso());
		boolean ingresoActivo = false;
		for(Factura factura: facturas) {
			if(!factura.getEstado().equals("FINALIZADA")) {
				ingresoActivo = true;
				break;
			}
		}
		if(ingresoActivo==true) {
			historia.getIngreso().setTstatus(TypeIngresoStatus.FINALIZADO);
		}else {
			historia.getIngreso().setTstatus(TypeIngresoStatus.EN_CURSO);
		}
		historia.setIngreso(ingresoRepository.save(historia.getIngreso()));
		return historiaConverter.historiaToHistoriaModel(historia);
	}

	@Override
	public HistoriaModel findHistoriaModelById(String id) {
		return historiaConverter.historiaToHistoriaModel(findHistoriaById(id));
	}

	@Override
	public Historia findHistoriaById(String id) {
		return historiaRepository.findById(id);
	}

	@Override
	public List<HistoriaModel> findAllHistoriaModelByIngreso(IngresoModel ingresoModel) {
		List<Historia> historias = findAllHistoriaByIngreso(ingresoConverter.ingresoModelToIngreso(ingresoModel));
		List<HistoriaModel> historiaModel = new ArrayList<HistoriaModel>();
		for(Historia historia:historias){
			historiaModel.add(historiaConverter.historiaToHistoriaModel(historia));
		}
		return historiaModel;
	}

	@Override
	public List<Historia> findAllHistoriaByIngreso(Ingreso ingreso) {
		return historiaRepository.findByIngreso(ingreso);
	}

	@Override
	public List<PersonModel> findAllPersonModelWithHistoria(String str, int page) {
		List<Person> persons = findAllPersonWithHistoria(str, page);
		List<PersonModel> personModels = new ArrayList<PersonModel>();
		for(Person person : persons){
			personModels.add(personConverter.personToPersonModel(person));
		}
		return personModels;
	}

	@Override
	public List<Person> findAllPersonWithHistoria(String str, int page) {
		return historiaDslRepository.searchAllDataHistoria(str, page);
	}

	@Override
	public Long countFindAllPersonModelWithHistoria(String str) {
		return historiaDslRepository.countSearchAllDataHistoria(str);
	}

	@Override
	public List<HistoriaModel> findAllHistoriaModelByPerson(String id) {
		List<Historia> historias = findAllHistoriaByPerson(id);
		List<HistoriaModel> historiaModels = new ArrayList<HistoriaModel>();
		for(Historia historia : historias){
			historiaModels.add(historiaConverter.historiaToHistoriaModel(historia));
		}
		return historiaModels;
	}

	@Override
	public List<Historia> findAllHistoriaByPerson(String id) {
		return historiaRepository.findByIngresoPersonId(id);
	}

}
