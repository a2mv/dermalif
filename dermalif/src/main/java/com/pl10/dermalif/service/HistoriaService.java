package com.pl10.dermalif.service;

import java.util.List;

import com.pl10.dermalif.entity.Historia;
import com.pl10.dermalif.entity.Ingreso;
import com.pl10.dermalif.model.HistoriaModel;
import com.pl10.dermalif.model.IngresoModel;

public interface HistoriaService {

	public abstract HistoriaModel addHistoriaModel(HistoriaModel historiaModel);
	
	public abstract HistoriaModel findHistoriaModelById(String id);
	
	public abstract Historia findHistoriaById(String id);
	
	public abstract List<HistoriaModel> findAllHistoriaModelByIngreso(IngresoModel ingresoModel);
	
	public abstract List<Historia> findAllHistoriaByIngreso(Ingreso ingreso);
	
}
