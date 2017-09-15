package com.pl10.dermalif.coverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.pl10.dermalif.entity.Facturadesc;
import com.pl10.dermalif.model.FacturadescModel;

@Component("facturadescConverter")
public class FacturadescConverter {
	
	@Autowired
	@Qualifier("facturaConverter")
	FacturaConverter facturaConverter;
	
	public Facturadesc facturadescModelToFacturadesc(FacturadescModel facturadescModel) {
		Facturadesc facturadesc = new Facturadesc();
		facturadesc.setCantidad(facturadescModel.getCantidad());
		facturadesc.setCodarticulo(facturadescModel.getCodarticulo());
		facturadesc.setFactura(facturaConverter.facturaModelToFactura(facturadescModel.getFacturamodel()));
		facturadesc.setId(facturadescModel.getId());
		facturadesc.setNamearticulo(facturadescModel.getNamearticulo());
		facturadesc.setVarticulo(facturadescModel.getVarticulo());
		facturadesc.setVdescuento(facturadescModel.getVdescuento());
		facturadesc.setVtotal(facturadescModel.getVtotal());
		return facturadesc;
	}
	
	public FacturadescModel facturadescToFacturadescModel(Facturadesc facturadesc) {
		FacturadescModel facturadescModel = new FacturadescModel();
		facturadescModel.setCantidad(facturadesc.getCantidad());
		facturadescModel.setCodarticulo(facturadesc.getCodarticulo());
		facturadescModel.setFacturamodel(facturaConverter.facturaToFacturaModel(facturadesc.getFactura()));
		facturadescModel.setId(facturadesc.getId());
		facturadescModel.setNamearticulo(facturadesc.getNamearticulo());
		facturadescModel.setVarticulo(facturadesc.getVarticulo());
		facturadescModel.setVdescuento(facturadesc.getVdescuento());
		facturadescModel.setVtotal(facturadesc.getVtotal());
		return facturadescModel;
	}

}
