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
import org.hibernate.annotations.Type;

import com.pl10.dermalif.enums.TypeIngresoStatus;

@Entity
@Table(name = "ingreso")
public class Ingreso {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(unique = true, nullable = false, updatable = false)
	private String id;
	@Column(name = "norden")
	@GeneratedValue
	private Long norden;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "person", nullable = false)
	private Person person;
	@Column(name = "empresa", nullable = false)
	private String empresa;
	@Column(name = "ocupacion", nullable = false)
	private String ocupacion;
	@Column(name = "cconsulta", nullable = false)
	private String cconsulta;
	@Column(name = "tstatus", nullable = false)
	private TypeIngresoStatus tstatus;
	@Type(type = "timestamp")
	@Column(name = "fecha", nullable = false)
	private Date fecha;

	public Ingreso() {
		super();
	}

	public Ingreso(String id, Long norden, Person person, String empresa, String ocupacion, String cconsulta,
			TypeIngresoStatus tstatus, Date fecha) {
		super();
		this.id = id;
		this.norden = norden;
		this.person = person;
		this.empresa = empresa;
		this.ocupacion = ocupacion;
		this.cconsulta = cconsulta;
		this.tstatus = tstatus;
		this.fecha = fecha;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getNorden() {
		return norden;
	}

	public void setNorden(Long norden) {
		this.norden = norden;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getOcupacion() {
		return ocupacion;
	}

	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}

	public String getCconsulta() {
		return cconsulta;
	}

	public void setCconsulta(String cconsulta) {
		this.cconsulta = cconsulta;
	}

	public TypeIngresoStatus getTstatus() {
		return tstatus;
	}

	public void setTstatus(TypeIngresoStatus tstatus) {
		this.tstatus = tstatus;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "Ingreso [id=" + id + ", norden=" + norden + ", person=" + person + ", empresa=" + empresa
				+ ", ocupacion=" + ocupacion + ", cconsulta=" + cconsulta + ", tstatus=" + tstatus + ", fecha=" + fecha
				+ "]";
	}

}
