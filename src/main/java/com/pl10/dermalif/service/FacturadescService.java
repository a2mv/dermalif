package com.pl10.dermalif.service;

import java.util.List;

import com.pl10.dermalif.entity.Facturadesc;
import com.pl10.dermalif.model.FacturaModel;
import com.pl10.dermalif.model.FacturadescModel;

public interface FacturadescService {

	public abstract FacturadescModel addFacturadescModel(FacturadescModel facturadescModel);
	
	public abstract List<FacturadescModel> findFacturadescModelByFactura(FacturaModel facturaModel);
	
	public abstract FacturadescModel findFacturadescModelById(String id);
	
	public abstract Facturadesc findFacturadescById(String id);
	
	public abstract void removeFacturadescModel(FacturadescModel facturadescModel);
}
