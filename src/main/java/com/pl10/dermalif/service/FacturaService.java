package com.pl10.dermalif.service;

import java.util.List;

import com.pl10.dermalif.entity.Factura;
import com.pl10.dermalif.model.FacturaModel;
import com.pl10.dermalif.model.IngresoModel;

public interface FacturaService {

	public abstract FacturaModel addFactura(FacturaModel facturaModel);
	
	public abstract Integer generarConsecutivo();
	
	public abstract boolean existFactura(Integer facturaModel);
	
	public abstract FacturaModel findFacturaModelById(String id);
	
	public abstract Factura findFacturaById(String id);
	
	public abstract void anularFactura(IngresoModel ingresoModel);
	
	public abstract List<FacturaModel> findFacturaModelByIngresoModel(IngresoModel ingresoModel);
	
}
