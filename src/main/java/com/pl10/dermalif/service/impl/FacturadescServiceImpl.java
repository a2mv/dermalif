package com.pl10.dermalif.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.pl10.dermalif.coverter.FacturaConverter;
import com.pl10.dermalif.coverter.FacturadescConverter;
import com.pl10.dermalif.entity.Facturadesc;
import com.pl10.dermalif.model.FacturaModel;
import com.pl10.dermalif.model.FacturadescModel;
import com.pl10.dermalif.repository.FacturadescRepository;
import com.pl10.dermalif.service.FacturadescService;

@Service("facturadescService")
public class FacturadescServiceImpl implements FacturadescService{

	@Autowired
	@Qualifier("facturadescConverter")
	private FacturadescConverter facturadescConverter;
	
	@Autowired
	@Qualifier("facturadescRepository")
	private FacturadescRepository facturadescRepository;
	
	@Autowired
	@Qualifier("facturaConverter")
	FacturaConverter facturaConverter;
	
	@Override
	public FacturadescModel addFacturadescModel(FacturadescModel facturadescModel) {
		Facturadesc facturadesc = facturadescConverter.facturadescModelToFacturadesc(facturadescModel);
		return facturadescConverter.facturadescToFacturadescModel(facturadescRepository.save(facturadesc));
	}

	@Override
	public List<FacturadescModel> findFacturadescModelByFactura(FacturaModel facturaModel) {
		List<Facturadesc> facturadescs = facturadescRepository.findByFactura(facturaConverter.facturaModelToFactura(facturaModel));
		List<FacturadescModel> facturadescModel = new ArrayList<FacturadescModel>();
		for(Facturadesc facturadesc:facturadescs) {
			facturadescModel.add(facturadescConverter.facturadescToFacturadescModel(facturadesc));
		}		
		return facturadescModel;
	}

	@Override
	public FacturadescModel findFacturadescModelById(String id) {
		return facturadescConverter.facturadescToFacturadescModel(findFacturadescById(id));
	}

	@Override
	public Facturadesc findFacturadescById(String id) {
		return facturadescRepository.findById(id);
	}

	@Override
	public void removeFacturadescModel(FacturadescModel facturadescModel) {
		facturadescRepository.delete(facturadescConverter.facturadescModelToFacturadesc(facturadescModel));
	}

}
