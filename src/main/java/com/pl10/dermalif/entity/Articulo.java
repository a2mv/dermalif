package com.pl10.dermalif.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "articulo")
public class Articulo {

	@Id
	@GeneratedValue
	@Column(unique = true, nullable = false, updatable = false)
	private int id;
	@Column(name = "codigo", nullable = false)
	private String codigo;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "valor", nullable = false, precision = 10, scale = 2)
	private Double valor;
	@Column(name = "enable", nullable = false)
	private boolean enable;

	public Articulo() {
		super();
	}

	public Articulo(int id, String codigo, String name, Double valor, boolean enable) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.name = name;
		this.valor = valor;
		this.enable = enable;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	@Override
	public String toString() {
		return "Articulo [id=" + id + ", codigo=" + codigo + ", name=" + name + ", valor=" + valor + ", enable="
				+ enable + "]";
	}
}
