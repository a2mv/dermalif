package com.pl10.dermalif.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.pl10.dermalif.coverter.FacturaConverter;
import com.pl10.dermalif.coverter.IngresoConverter;
import com.pl10.dermalif.entity.Factura;
import com.pl10.dermalif.model.FacturaModel;
import com.pl10.dermalif.model.IngresoModel;
import com.pl10.dermalif.repository.FacturaRepository;
import com.pl10.dermalif.repository.IngresoRepository;
import com.pl10.dermalif.repository.query.FacturaDslRepository;
import com.pl10.dermalif.service.FacturaService;

@Service("facturaService")
public class FacturaServiceImpl implements FacturaService {

	@Autowired
	@Qualifier("facturaConverter")
	FacturaConverter facturaConverter;

	@Autowired
	@Qualifier("facturaRepository")
	FacturaRepository facturaRepository;

	@Autowired
	@Qualifier("facturaDslRepository")
	FacturaDslRepository facturaDslRepository;

	@Autowired
	@Qualifier("ingresoConverter")
	IngresoConverter ingresoConverter;

	@Autowired
	@Qualifier("ingresoRepository")
	IngresoRepository ingresoRepository;

	@Override
	public FacturaModel addFactura(FacturaModel facturaModel) {
		anularFactura(facturaModel.getIngresomodel());
		
		Factura factura = facturaConverter.facturaModelToFactura(facturaModel);
		return facturaConverter.facturaToFacturaModel(facturaRepository.save(factura));
	}

	@Override
	public Integer generarConsecutivo() {
		Factura factura = facturaDslRepository.searchLastFactura();
		Integer consecutivo = 0;
		if (factura == null) {
			consecutivo = 1;
		} else {
			consecutivo = factura.getIdfactura() + 1;
		}
		return consecutivo;
	}

	@Override
	public boolean existFactura(Integer idFactura) {
		if (facturaRepository.findByIdfactura(idFactura) != null) {
			return true;
		}
		return false;
	}

	@Override
	public FacturaModel findFacturaModelById(String id) {
		return facturaConverter.facturaToFacturaModel(findFacturaById(id));
	}

	@Override
	public Factura findFacturaById(String id) {
		return facturaRepository.findById(id);
	}

	@Override
	public void anularFactura(IngresoModel ingresoModel) {
		List<Factura> facturas = facturaRepository.findByIngreso(ingresoConverter.ingresoModelToIngreso(ingresoModel));
		for (Factura factura : facturas) {
			factura.setEstado("ANULADO");
			facturaRepository.save(factura);
		}
	}

	@Override
	public List<FacturaModel> findFacturaModelByIngresoModel(IngresoModel ingresoModel) {
		List<Factura> facturas = facturaRepository.findByIngreso(ingresoConverter.ingresoModelToIngreso(ingresoModel));
		List<FacturaModel> facturaModels = new ArrayList<FacturaModel>();
		for (Factura factura : facturas) {
			facturaModels.add(facturaConverter.facturaToFacturaModel(factura));
		}
		return facturaModels;
	}

	@Override
	public List<FacturaModel> searchListFacturaModel(String str, int page) {
		List<Factura> facturas = facturaDslRepository.searchAllDataFacturas(str, page);
		List<FacturaModel> facturaModels = new ArrayList<FacturaModel>();
		for (Factura factura : facturas) {
			facturaModels.add(facturaConverter.facturaToFacturaModel(factura));
		}
		return facturaModels;
	}

	@Override
	public Long countSearchListFacturaModel(String str) {
		return facturaDslRepository.countSearchAllDataFacturas(str);
	}

}
