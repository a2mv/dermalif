package com.pl10.dermalif.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class ArticuloModel {

	private int id;
	@NotBlank(message = "Este campo no puede estar vacío")
	@NotNull(message = "Este campo no puede ser nulo")
	private String codigo;
	@NotBlank(message = "Este campo no puede estar vacío")
	@NotNull(message = "Este campo no puede ser nulo")
	private String name;
	@NotNull(message = "Este campo no puede ser nulo")
	private Double valor;
	private boolean enable;

	public ArticuloModel() {
		super();
	}

	public ArticuloModel(int id, String codigo, String name, Double valor, boolean enable) {
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
		return "ArticuloModel [id=" + id + ", codigo=" + codigo + ", name=" + name + ", valor=" + valor + ", enable="
				+ enable + "]";
	}

}
