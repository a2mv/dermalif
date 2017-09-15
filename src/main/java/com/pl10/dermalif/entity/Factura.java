package com.pl10.dermalif.entity;

import java.util.Date;

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
@Table(name = "factura")
public class Factura {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(unique = true, nullable = false, updatable = false)
	private String id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ingreso", nullable = false)
	private Ingreso ingreso;
	@Column(name = "user", nullable = false)
	private String user;
	@Column(name = "idfactura", unique = true, nullable = false)
	private Integer idfactura;
	@Column(name = "fecha", nullable = false)
	private Date fecha;
	@Column(name = "subtotal", nullable = false, precision = 10, scale = 2)
	private Double subtotal;
	@Column(name = "valoriva", nullable = false, precision = 10, scale = 2)
	private Double valoriva;
	@Column(name = "iva", nullable = false, precision = 10, scale = 2)
	private Double iva;
	@Column(name = "valortotal", nullable = false, precision = 10, scale = 2)
	private Double valortotal;
	@Column(name = "estado", nullable = false)
	private String estado;

	public Factura() {
		super();
	}

	public Factura(String id, Ingreso ingreso, String user, Integer idfactura, Date fecha, Double subtotal,
			Double valoriva, Double valortotal, String estado, Double iva) {
		super();
		this.id = id;
		this.ingreso = ingreso;
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

	public Ingreso getIngreso() {
		return ingreso;
	}

	public void setIngreso(Ingreso ingreso) {
		this.ingreso = ingreso;
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

	@Override
	public String toString() {
		return "Factura [id=" + id + ", ingreso=" + ingreso + ", user=" + user + ", idfactura=" + idfactura + ", fecha="
				+ fecha + ", subtotal=" + subtotal + ", valoriva=" + valoriva + ", iva=" + iva + ", valortotal="
				+ valortotal + ", estado=" + estado + "]";
	}

}
