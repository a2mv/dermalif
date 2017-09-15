package com.pl10.dermalif.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "facturadesc")
public class Facturadesc {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(unique = true, nullable = false, updatable = false)
	private String id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "factura", nullable = false)
	private Factura factura;
	@Column(name = "codarticulo", nullable = false)
	private String codarticulo;
	@Column(name = "namearticulo", nullable = false)
	private String namearticulo;
	@Column(name = "varticulo", nullable = false, precision = 10, scale = 2)
	private Double varticulo;
	@Column(name = "cantidad", nullable = false, precision = 10, scale = 2)
	private Integer cantidad;
	@Column(name = "vdescuento", nullable = false, precision = 10, scale = 2)
	private Double vdescuento;
	@Column(name = "vtotal", nullable = false, precision = 10, scale = 2)
	private Double vtotal;

	public Facturadesc() {
		super();
	}

	public Facturadesc(String id, Factura factura, String codarticulo, String namearticulo, Double varticulo,
			Integer cantidad, Double vdescuento, Double vtotal) {
		super();
		this.id = id;
		this.factura = factura;
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

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
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
		return "Facturadesc [id=" + id + ", factura=" + factura + ", codarticulo=" + codarticulo + ", namearticulo="
				+ namearticulo + ", varticulo=" + varticulo + ", cantidad=" + cantidad + ", vdescuento=" + vdescuento
				+ ", vtotal=" + vtotal + "]";
	}

}
