package com.pl10.dermalif.coverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.pl10.dermalif.entity.Factura;
import com.pl10.dermalif.model.FacturaModel;

@Component("facturaConverter")
public class FacturaConverter {
	
	@Autowired
	@Qualifier("ingresoConverter")
	IngresoConverter ingresoConverter;
	
	@Autowired
	@Qualifier("facturadescConverter")
	FacturadescConverter facturadescConverter;

	public Factura facturaModelToFactura(FacturaModel facturaModel) {
		Factura factura = new Factura();
		factura.setEstado(facturaModel.getEstado());
		factura.setFecha(facturaModel.getFecha());
		factura.setId(facturaModel.getId());
		factura.setIdfactura(facturaModel.getIdfactura());
		factura.setIngreso(ingresoConverter.ingresoModelToIngreso(facturaModel.getIngresomodel()));
		factura.setSubtotal(facturaModel.getSubtotal());
		factura.setUser(facturaModel.getUser());
		factura.setValoriva(facturaModel.getValoriva());
		factura.setValortotal(facturaModel.getValortotal());
		factura.setIva(facturaModel.getIva());
		return factura;
	}
	
	public FacturaModel facturaToFacturaModel(Factura factura) {
		FacturaModel facturaModel = new FacturaModel();
		facturaModel.setEstado(factura.getEstado());
		facturaModel.setFecha(factura.getFecha());
		facturaModel.setId(factura.getId());
		facturaModel.setIdfactura(factura.getIdfactura());
		facturaModel.setIngresomodel(ingresoConverter.ingresoToIngresoModel(factura.getIngreso()));
		facturaModel.setSubtotal(factura.getSubtotal());
		facturaModel.setUser(factura.getUser());
		facturaModel.setValoriva(factura.getValoriva());
		facturaModel.setValortotal(factura.getValortotal());
		facturaModel.setIva(factura.getIva());
		return facturaModel;
	}
}
