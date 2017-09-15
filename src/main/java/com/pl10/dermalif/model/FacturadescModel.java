package com.pl10.dermalif.model;

public class FacturadescModel {

	private String id;
	private FacturaModel facturamodel;
	private String codarticulo;
	private String namearticulo;
	private Double varticulo;
	private Integer cantidad;
	private Double vdescuento;
	private Double vtotal;

	public FacturadescModel() {
		super();
	}

	public FacturadescModel(String id, FacturaModel facturamodel, String codarticulo, String namearticulo,
			Double varticulo, Integer cantidad, Double vdescuento, Double vtotal) {
		super();
		this.id = id;
		this.facturamodel = facturamodel;
		this.codarticulo = codarticulo;
		this.namearticulo = namearticulo;
		this.varticulo = varticulo;
		this.cantidad = cantidad;
		this.vdescuento = vdescuento;
		this.vtotal = vtotal;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public FacturaModel getFacturamodel() {
		return facturamodel;
	}

	public void setFacturamodel(FacturaModel facturamodel) {
		this.facturamodel = facturamodel;
	}

	public String getCodarticulo() {
		return codarticulo;
	}

	public void setCodarticulo(String codarticulo) {
		this.codarticulo = codarticulo;
	}

	public String getNamearticulo() {
		return namearticulo;
	}

	public void setNamearticulo(String namearticulo) {
		this.namearticulo = namearticulo;
	}

	public Double getVarticulo() {
		return varticulo;
	}

	public void setVarticulo(Double varticulo) {
		this.varticulo = varticulo;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Double getVdescuento() {
		return vdescuento;
	}

	public void setVdescuento(Double vdescuento) {
		this.vdescuento = vdescuento;
	}

	public Double getVtotal() {
		return vtotal;
	}

	public void setVtotal(Double vtotal) {
		this.vtotal = vtotal;
	}

	@Override
	public String toString() {
		return "FacturadescModel [id=" + id + ", facturamodel=" + facturamodel + ", codarticulo=" + codarticulo
				+ ", namearticulo=" + namearticulo + ", varticulo=" + varticulo + ", cantidad=" + cantidad
				+ ", vdescuento=" + vdescuento + ", vtotal=" + vtotal + "]";
	}

}
