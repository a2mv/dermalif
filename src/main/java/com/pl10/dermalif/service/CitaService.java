package com.pl10.dermalif.service;

import java.util.Date;
import java.util.List;

import com.pl10.dermalif.entity.Cita;
import com.pl10.dermalif.model.CitaJsonObject;
import com.pl10.dermalif.model.CitaModel;

public interface CitaService {
	
	public abstract CitaModel findCitaModelById(String id);
	
	public abstract Cita findCitaById(String id);
	
	public abstract List<CitaModel> findByStartBetween(Date start, Date end);
	
	public abstract void deleteCita(CitaModel citaModel);
	
	public abstract List<CitaJsonObject> findCitaJsonStartBetween(Date start, Date end);
	
	public abstract CitaModel addCitaModel(CitaModel citaModel);

}
