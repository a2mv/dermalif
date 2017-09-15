package com.pl10.dermalif.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FacturaModel {

	private String id;
	private IngresoModel ingresomodel;
	private String user;
	private Integer idfactura;
	private Date fecha;
	private Double subtotal;
	private Double valoriva;
	private Double valortotal;
	private String estado;
	private Double iva;

	public FacturaModel() {
		super();
	}

	public FacturaModel(String id, IngresoModel ingresomodel, String user, Integer idfactura, Date fecha,
			Double subtotal, Double valoriva, Double valortotal, String estado, Double iva) {
		super();
		this.id = id;
		this.ingresomodel = ingresomodel;
		this.user = user;
		this.idfactura = idfactura;
		this.fecha = fecha;
		this.subtotal = subtotal;
		this.valoriva = valoriva;
		this.valortotal = valortotal;
		this.estado = estado;
		this.iva = iva;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public IngresoModel getIngresomodel() {
		return ingresomodel;
	}

	public void setIngresomodel(IngresoModel ingresomodel) {
		this.ingresomodel = ingresomodel;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Integer getIdfactura() {
		return idfactura;
	}

	public void setIdfactura(Integer idfactura) {
		this.idfactura = idfactura;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	public Double getValoriva() {
		return valoriva;
	}

	public void setValoriva(Double valoriva) {
		this.valoriva = valoriva;
	}

	public Double getValortotal() {
		return valortotal;
	}

	public void setValortotal(Double valortotal) {
		this.valortotal = valortotal;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Double getIva() {
		return iva;
	}

	public void setIva(Double iva) {
		this.iva = iva;
	}
	
	public String getFechaFormat() {
		return new SimpleDateFormat("yyyy-MM-dd hh:mm a").format(fecha);
	}

	@Override
	public String toString() {
		return "FacturaModel [id=" + id + ", ingresomodel=" + ingresomodel + ", user=" + user + ", idfactura="
				+ idfactura + ", fecha=" + fecha + ", subtotal=" + subtotal + ", valoriva=" + valoriva + ", valortotal="
				+ valortotal + ", estado=" + estado + ", iva=" + iva + "]";
	}

}
