package com.pl10.dermalif.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pl10.dermalif.entity.Articulo;

@Repository("articuloRepository")
public interface ArticuloRepository extends JpaRepository<Articulo, Serializable>{

	public abstract List<Articulo> findByEnable(boolean enable);
	
	public abstract Articulo findById(int id);
}
