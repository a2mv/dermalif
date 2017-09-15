package com.pl10.dermalif.repository.query;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.pl10.dermalif.entity.Articulo;
import com.pl10.dermalif.entity.QArticulo;
import com.querydsl.jpa.impl.JPAQuery;

@Repository("articuloDslRepository")
public class ArticuloDslRepository {
	
	private QArticulo qArticulo = QArticulo.articulo;
	
	@PersistenceContext
	private EntityManager em;
	
	public List<Articulo> searchAllDataArticulo(String str, int page){
		JPAQuery<Articulo> query = new JPAQuery<Articulo>(em);
		page = (page*10)-10;
		List<Articulo> articulos = query.select(qArticulo).from(qArticulo)
				.where(qArticulo.codigo.contains(str)
						.or(qArticulo.name.contains(str))
						).offset(page).limit(10).fetch();				
		return articulos;
	}
	
	public Long countSearchAllDataArticulo(String str){
		JPAQuery<Articulo> query = new JPAQuery<Articulo>(em);
		long count = query.select(qArticulo).from(qArticulo)
				.where(qArticulo.codigo.contains(str)
						.or(qArticulo.name.contains(str))
						).fetchCount();				
		return count;
	}
	
	public List<Articulo> searchArticuloByNameOrByCodigo(String str){
		JPAQuery<Articulo> query = new JPAQuery<Articulo>(em);
		List<Articulo> articulos = query.select(qArticulo).from(qArticulo)
				.where(qArticulo.name.contains(str)
				.or(qArticulo.codigo.contains(str))
				.and(qArticulo.enable.isTrue()))				
				.limit(7).fetch();
		return articulos;
	}

}
