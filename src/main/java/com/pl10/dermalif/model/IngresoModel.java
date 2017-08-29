package com.pl10.dermalif.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.pl10.dermalif.enums.TypeIngresoStatus;

public class IngresoModel {

	private String id;
	private Long norden;
	private PersonModel personModel;
	@NotBlank(message= "Este campo no puede estar vacío")
	@NotNull(message = "Este campo no puede ser nulo")
	private String empresa;
	private String ocupacion;
	@NotBlank(message= "Este campo no puede estar vacío")
	@NotNull(message = "Este campo no puede ser nulo")
	private String cconsulta;
	private TypeIngresoStatus tstatus;
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm a")
	private Date fecha;

	public IngresoModel() {
		super();
	}

	public IngresoModel(String id, Long norden, PersonModel personModel, String empresa, String ocupacion,
			String cconsulta, TypeIngresoStatus tstatus, Date fecha) {
		super();
		this.id = id;
		this.norden = norden;
		this.personModel = personModel;
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

	public PersonModel getPersonModel() {
		return personModel;
	}

	public void setPersonModel(PersonModel personModel) {
		this.personModel = personModel;
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
	
	public String getFechaFormat() {
		return new SimpleDateFormat("yyyy-MM-dd hh:mm a").format(fecha);
	}

	@Override
	public String toString() {
		return "IngresoModel [id=" + id + ", norden=" + norden + ", personModel=" + personModel + ", empresa=" + empresa
				+ ", ocupacion=" + ocupacion + ", cconsulta=" + cconsulta + ", tstatus=" + tstatus + ", fecha=" + fecha + "]";
	}



}
