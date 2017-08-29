package com.pl10.dermalif.coverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.pl10.dermalif.entity.Historia;
import com.pl10.dermalif.model.HistoriaModel;


@Component("historiaConverter")
public class HistoriaConverter {
	
	@Autowired
	@Qualifier("ingresoConverter")
	IngresoConverter ingresoConverter;
	
	public Historia historiaModelToHistoria(HistoriaModel historiaModel){
		Historia historia = new Historia();
		historia.setAfamiliares(historiaModel.getAfamiliares());
		historia.setApersonales(historiaModel.getApersonales());
		historia.setDiagnostico(historiaModel.getDiagnostico());
		historia.setEfgeneral(historiaModel.getEfgeneral());
		historia.setEmorfologia(historiaModel.getEmorfologia());
		historia.setEresto(historiaModel.getEresto());
		historia.setEscomplementario(historiaModel.getEscomplementario());
		historia.setEtopografia(historiaModel.getEtopografia());
		historia.setId(historiaModel.getId());
		historia.setIngreso(ingresoConverter.ingresoModelToIngreso(historiaModel.getIngresomodel()));
		historia.setInterrogatorio(historiaModel.getInterrogatorio());
		historia.setMconsulta(historiaModel.getMconsulta());
		historia.setTratamiento(historiaModel.getTratamiento());
		return historia;
	}
	
	public HistoriaModel historiaToHistoriaModel(Historia historia){
		HistoriaModel historiaModel = new HistoriaModel();
		historiaModel.setAfamiliares(historia.getAfamiliares());
		historiaModel.setApersonales(historia.getApersonales());
		historiaModel.setDiagnostico(historia.getDiagnostico());
		historiaModel.setEfgeneral(historia.getEfgeneral());
		historiaModel.setEmorfologia(historia.getEmorfologia());
		historiaModel.setEresto(historia.getEresto());
		historiaModel.setEscomplementario(historia.getEscomplementario());
		historiaModel.setEtopografia(historia.getEtopografia());
		historiaModel.setId(historia.getId());
		historiaModel.setIngresomodel(ingresoConverter.ingresoToIngresoModel(historia.getIngreso()));
		historiaModel.setInterrogatorio(historia.getInterrogatorio());
		historiaModel.setMconsulta(historia.getMconsulta());
		historiaModel.setTratamiento(historia.getTratamiento());
		return historiaModel;
	}
}
