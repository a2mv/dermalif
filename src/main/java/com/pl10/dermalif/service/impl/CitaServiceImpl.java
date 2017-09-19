package com.pl10.dermalif.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.pl10.dermalif.coverter.CitaConverter;
import com.pl10.dermalif.entity.Cita;
import com.pl10.dermalif.model.CitaJsonObject;
import com.pl10.dermalif.model.CitaModel;
import com.pl10.dermalif.repository.CitaRepository;
import com.pl10.dermalif.service.CitaService;

@Service("citaService")
public class CitaServiceImpl implements CitaService {
	
	@Autowired
	@Qualifier("citaRepository")
	CitaRepository citaRepository;
	
	@Autowired
	@Qualifier("citaConverter")
	CitaConverter citaConverter;

	@Override
	public CitaModel findCitaModelById(String id) {
		return citaConverter.citaToCitaModel(findCitaById(id));
	}

	@Override
	public Cita findCitaById(String id) {
		return citaRepository.findById(id);
	}

	@Override
	public List<CitaModel> findByStartBetween(Date start, Date end) {
		List<Cita> citas = citaRepository.findByStartBetween(start, end);
		List<CitaModel> citaModels = new ArrayList<CitaModel>();
		for(Cita cita:citas) {
			citaModels.add(citaConverter.citaToCitaModel(cita));
		}
		return citaModels;
	}

	@Override
	public void deleteCita(CitaModel citaModel) {
		citaRepository.delete(citaConverter.citaModelToCita(citaModel));
	}

	@Override
	public List<CitaJsonObject> findCitaJsonStartBetween(Date start, Date end) {
		List<CitaModel> citaModels = findByStartBetween(start, end);
		List<CitaJsonObject> CitaJsonObjects = new ArrayList<CitaJsonObject>();
		for(CitaModel citaModel:citaModels) {
			CitaJsonObjects.add(citaConverter.citaModelToCitaJsonObject(citaModel));
		}
		return CitaJsonObjects;
	}

	@Override
	public CitaModel addCitaModel(CitaModel citaModel) {		
		Cita cita = citaRepository.save(citaConverter.citaModelToCita(citaModel));
		return citaConverter.citaToCitaModel(cita);
	}

}
