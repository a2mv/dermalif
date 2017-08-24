package com.pl10.dermalif.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.pl10.dermalif.coverter.HistoriaConverter;
import com.pl10.dermalif.coverter.IngresoConverter;
import com.pl10.dermalif.entity.Historia;
import com.pl10.dermalif.entity.Ingreso;
import com.pl10.dermalif.enums.TypeIngresoStatus;
import com.pl10.dermalif.model.HistoriaModel;
import com.pl10.dermalif.model.IngresoModel;
import com.pl10.dermalif.repository.HistoriaRepository;
import com.pl10.dermalif.repository.IngresoRepository;
import com.pl10.dermalif.service.HistoriaService;

@Service("historiaService")
public class HistoriaServiceImpl implements HistoriaService {

	@Autowired
	@Qualifier("historiaConverter")
	HistoriaConverter historiaConverter;
	
	@Autowired
	@Qualifier("ingresoRepository")
	IngresoRepository ingresoRepository;
	
	@Autowired
	@Qualifier("historiaRepository")
	HistoriaRepository historiaRepository;
	
	@Autowired
	@Qualifier("ingresoConverter")
	IngresoConverter ingresoConverter;
	
	@Override
	public HistoriaModel addHistoriaModel(HistoriaModel historiaModel) {
		Historia historia = historiaRepository.save(historiaConverter.historiaModelToHistoria(historiaModel));
		//validar factura para ponerle estado a ingreso
		historia.getIngreso().setTstatus(TypeIngresoStatus.PENDIENTE);
		historia.setIngreso(ingresoRepository.save(historia.getIngreso()));
		return historiaConverter.historiaToHistoriaModel(historia);
	}

	@Override
	public HistoriaModel findHistoriaModelById(String id) {
		return historiaConverter.historiaToHistoriaModel(findHistoriaById(id));
	}

	@Override
	public Historia findHistoriaById(String id) {
		return historiaRepository.findById(id);
	}

	@Override
	public List<HistoriaModel> findAllHistoriaModelByIngreso(IngresoModel ingresoModel) {
		List<Historia> historias = findAllHistoriaByIngreso(ingresoConverter.ingresoModelToIngreso(ingresoModel));
		List<HistoriaModel> historiaModel = new ArrayList<HistoriaModel>();
		for(Historia historia:historias){
			historiaModel.add(historiaConverter.historiaToHistoriaModel(historia));
		}
		return historiaModel;
	}

	@Override
	public List<Historia> findAllHistoriaByIngreso(Ingreso ingreso) {
		return historiaRepository.findByIngreso(ingreso);
	}

}
