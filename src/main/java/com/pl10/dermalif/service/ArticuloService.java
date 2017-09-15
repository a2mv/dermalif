package com.pl10.dermalif.service;

import java.util.List;

import com.pl10.dermalif.entity.Articulo;
import com.pl10.dermalif.model.ArticuloAjaxResponse;
import com.pl10.dermalif.model.ArticuloModel;

public interface ArticuloService {

	public abstract ArticuloModel addArticuloModel(ArticuloModel articuloModel);
	
	public abstract ArticuloModel findArticuloModelById(int id);
	
	public abstract Articulo findArticuloById(int id);
	
	public abstract List<ArticuloModel> searchArticuloModel(String str, int page);
	
	public abstract Long countSearchArticuloModel(String str);
	
	public abstract List<ArticuloAjaxResponse> listArticuloAjaxResponse(String str);
}
