package com.pl10.dermalif.coverter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.pl10.dermalif.entity.Cita;
import com.pl10.dermalif.model.CitaJsonObject;
import com.pl10.dermalif.model.CitaModel;

@Component("citaConverter")
public class CitaConverter {

	@Autowired
	@Qualifier("personConverter")
	private PersonConverter personConverter;
	
	public CitaModel citaToCitaModel(Cita cita) {
		CitaModel citaModel = new CitaModel();
		citaModel.setBackgroundColor(cita.getBackgroundColor());
		citaModel.setBorderColor(cita.getBorderColor());
		citaModel.setDuration(cita.getDuration());
		citaModel.setEnd(cita.getEnd());
		citaModel.setStart(cita.getStart());
		citaModel.setId(cita.getId());
		citaModel.setTitle(cita.getTitle());
		citaModel.setPersonModel(personConverter.personToPersonModel(cita.getPerson()));
		return citaModel;
	}
	
	public Cita citaModelToCita(CitaModel citaModel) {
		Cita cita = new Cita();
		cita.setBackgroundColor(citaModel.getBackgroundColor());
		cita.setBorderColor(citaModel.getBorderColor());
		cita.setDuration(citaModel.getDuration());
		cita.setEnd(citaModel.getEnd());
		cita.setStart(citaModel.getStart());
		cita.setId(citaModel.getId());
		cita.setTitle(citaModel.getTitle());
		cita.setPerson(personConverter.personModelToPerson(citaModel.getPersonModel()));
		return cita;
	}
	
	public CitaJsonObject citaModelToCitaJsonObject(CitaModel citaModel) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		CitaJsonObject citaJsonObject = new CitaJsonObject();
		citaJsonObject.setBackgroundColor(citaModel.getBackgroundColor());
		citaJsonObject.setBorderColor(citaModel.getBorderColor());
		citaJsonObject.setDuration(citaModel.getDuration().toString());
		citaJsonObject.setEnd(format.format(citaModel.getEnd()));
		citaJsonObject.setStart(format.format(citaModel.getStart()));
		citaJsonObject.setId(citaModel.getId());
		citaJsonObject.setTitle(citaModel.getTitle());
		citaJsonObject.setId_pa(citaModel.getPersonModel().getId());
		return citaJsonObject;
	}
}
