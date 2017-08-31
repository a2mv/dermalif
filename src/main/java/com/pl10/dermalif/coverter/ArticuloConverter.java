package com.pl10.dermalif.coverter;

import org.springframework.stereotype.Component;

import com.pl10.dermalif.entity.Articulo;
import com.pl10.dermalif.model.ArticuloModel;

@Component("articuloConverter")
public class ArticuloConverter {

	public Articulo articuloModelToArticulo(ArticuloModel articuloModel) {
		Articulo articulo = new Articulo();
		articulo.setCodigo(articuloModel.getCodigo());
		articulo.setEnable(articuloModel.isEnable());
		articulo.setId(articuloModel.getId());
		articulo.setName(articuloModel.getName());
		articulo.setValor(articuloModel.getValor());
		return articulo;
	}
	
	public ArticuloModel articuloToArticuloModel(Articulo articulo) {
		ArticuloModel articuloModel = new ArticuloModel();
		articuloModel.setCodigo(articulo.getCodigo());
		articuloModel.setEnable(articulo.isEnable());
		articuloModel.setId(articulo.getId());
		articuloModel.setName(articulo.getName());
		articuloModel.setValor(articulo.getValor());
		return articuloModel;
	}
}
