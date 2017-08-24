package com.pl10.dermalif.coverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.pl10.dermalif.entity.Ingreso;
import com.pl10.dermalif.model.IngresoModel;

@Component("ingresoConverter")
public class IngresoConverter {
	
	@Autowired
	@Qualifier("personConverter")
	private PersonConverter personConverter;
	
	public IngresoModel ingresoToIngresoModel(Ingreso ingreso){
		IngresoModel ingresoModel = new IngresoModel();
		ingresoModel.setCconsulta(ingreso.getCconsulta());
		ingresoModel.setEmpresa(ingreso.getEmpresa());
		ingresoModel.setFecha(ingreso.getFecha());
		ingresoModel.setId(ingreso.getId());
		ingresoModel.setNorden(ingreso.getNorden());
		ingresoModel.setOcupacion(ingreso.getOcupacion());
		ingresoModel.setTstatus(ingreso.getTstatus());
		ingresoModel.setPersonModel(personConverter.personToPersonModel(ingreso.getPerson()));
		return ingresoModel;
	}
	
	public Ingreso ingresoModelToIngreso(IngresoModel ingresoModel){
		Ingreso ingreso = new Ingreso();
		ingreso.setCconsulta(ingresoModel.getCconsulta());
		ingreso.setEmpresa(ingresoModel.getEmpresa());
		ingreso.setFecha(ingresoModel.getFecha());
		ingreso.setId(ingresoModel.getId());
		ingreso.setNorden(ingresoModel.getNorden());
		ingreso.setOcupacion(ingresoModel.getOcupacion());
		ingreso.setTstatus(ingresoModel.getTstatus());
		ingreso.setPerson(personConverter.personModelToPerson(ingresoModel.getPersonModel()));
		return ingreso;
	}

}
