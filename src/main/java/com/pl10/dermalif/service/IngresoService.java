package com.pl10.dermalif.service;

import java.util.List;

import com.pl10.dermalif.entity.Ingreso;
import com.pl10.dermalif.model.IngresoModel;
import com.pl10.dermalif.model.PersonModel;

public interface IngresoService {
	
	public abstract IngresoModel addIngresoModel(IngresoModel ingresoModel);
	
	public abstract List<IngresoModel> listAllIngresoModel();
	
	public abstract List<IngresoModel> listPageableIngresoModel(PersonModel personModel);
	
	public abstract IngresoModel findIngresoModelById(String id);
	
	public abstract Ingreso findIngresoById(String id);
	
	public abstract Long findMaxValueNorden();
	
	public abstract Long countAllIngresos();
	
	public List<IngresoModel> searchListIngresoModel(String str, int page);
	
	public abstract Long countSearchListIngresoModel(String str);

}
