package com.pl10.dermalif.model;

public class LocationViewModel {

	private String ubicacion;
	private String modulo;
	private String descripcion;

	public LocationViewModel() {
		super();
	}

	public LocationViewModel(String ubicacion, String modulo, String descripcion) {
		super();
		this.ubicacion = ubicacion;
		this.modulo = modulo;
		this.descripcion = descripcion;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getModulo() {
		return modulo;
	}

	public void setModulo(String modulo) {
		this.modulo = modulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "LocationViewModel [ubicacion=" + ubicacion + ", modulo=" + modulo + ", descripcion=" + descripcion
				+ "]";
	}

}
