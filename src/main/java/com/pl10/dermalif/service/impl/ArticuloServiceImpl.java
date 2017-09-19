package com.pl10.dermalif.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.pl10.dermalif.coverter.ArticuloConverter;
import com.pl10.dermalif.entity.Articulo;
import com.pl10.dermalif.model.ArticuloModel;
import com.pl10.dermalif.model.ModelAjaxResponse;
import com.pl10.dermalif.repository.ArticuloRepository;
import com.pl10.dermalif.repository.query.ArticuloDslRepository;
import com.pl10.dermalif.service.ArticuloService;

@Service("articuloService")
public class ArticuloServiceImpl implements ArticuloService {

	@Autowired
	@Qualifier("articuloRepository")
	private ArticuloRepository articuloRepository;

	@Autowired
	@Qualifier("articuloDslRepository")
	private ArticuloDslRepository articuloDslRepository;

	@Autowired
	@Qualifier("articuloConverter")
	private ArticuloConverter articuloConverter;

	@Override
	public ArticuloModel addArticuloModel(ArticuloModel articuloModel) {
		Articulo articulo = articuloConverter.articuloModelToArticulo(articuloModel);
		return articuloConverter.articuloToArticuloModel(articuloRepository.save(articulo));
	}

	@Override
	public ArticuloModel findArticuloModelById(int id) {
		return articuloConverter.articuloToArticuloModel(findArticuloById(id));
	}

	@Override
	public Articulo findArticuloById(int id) {
		return articuloRepository.findById(id);
	}

	@Override
	public List<ArticuloModel> searchArticuloModel(String str, int page) {
		List<Articulo> articulos = articuloDslRepository.searchAllDataArticulo(str, page);
		List<ArticuloModel> articuloModels = new ArrayList<ArticuloModel>();
		for (Articulo articulo : articulos) {
			articuloModels.add(articuloConverter.articuloToArticuloModel(articulo));
		}
		return articuloModels;
	}

	@Override
	public Long countSearchArticuloModel(String str) {
		return articuloDslRepository.countSearchAllDataArticulo(str);
	}

	@Override
	public List<ModelAjaxResponse> listArticuloAjaxResponse(String str) {
		List<Articulo> articulos = articuloDslRepository.searchArticuloByNameOrByCodigo(str);
		List<ModelAjaxResponse> articuloAjaxs = new ArrayList<ModelAjaxResponse>();
		for(Articulo articulo: articulos) {
			articuloAjaxs.add(new ModelAjaxResponse(articulo.getId(), articulo.getCodigo()+" "+articulo.getName()));
		}
		return articuloAjaxs;
	}
}
