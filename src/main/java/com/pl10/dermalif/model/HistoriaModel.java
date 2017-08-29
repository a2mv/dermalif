package com.pl10.dermalif.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class HistoriaModel {

	private String id;
	private IngresoModel ingresomodel;
	@NotBlank(message = "Este campo no puede estar vacío")
	@NotNull(message = "Este campo no puede ser nulo")
	private String mconsulta;
	private String etopografia;
	private String emorfologia;
	private String eresto;
	@NotBlank(message = "Este campo no puede estar vacío")
	@NotNull(message = "Este campo no puede ser nulo")
	private String interrogatorio;
	private String afamiliares;
	private String apersonales;
	@NotBlank(message = "Este campo no puede estar vacío")
	@NotNull(message = "Este campo no puede ser nulo")
	private String efgeneral;
	private String escomplementario;
	@NotBlank(message = "Este campo no puede estar vacío")
	@NotNull(message = "Este campo no puede ser nulo")
	private String diagnostico;
	private String tratamiento;

	public HistoriaModel() {
		super();
	}

	public HistoriaModel(String id, IngresoModel ingresomodel, String mconsulta, String etopografia, String emorfologia,
			String eresto, String interrogatorio, String afamiliares, String apersonales, String efgeneral,
			String escomplementario, String diagnostico, String tratamiento) {
		super();
		this.id = id;
		this.ingresomodel = ingresomodel;
		this.mconsulta = mconsulta;
		this.etopografia = etopografia;
		this.emorfologia = emorfologia;
		this.eresto = eresto;
		this.interrogatorio = interrogatorio;
		this.afamiliares = afamiliares;
		this.apersonales = apersonales;
		this.efgeneral = efgeneral;
		this.escomplementario = escomplementario;
		this.diagnostico = diagnostico;
		this.tratamiento = tratamiento;
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

	public String getMconsulta() {
		return mconsulta;
	}

	public void setMconsulta(String mconsulta) {
		this.mconsulta = mconsulta;
	}

	public String getEtopografia() {
		return etopografia;
	}

	public void setEtopografia(String etopografia) {
		this.etopografia = etopografia;
	}

	public String getEmorfologia() {
		return emorfologia;
	}

	public void setEmorfologia(String emorfologia) {
		this.emorfologia = emorfologia;
	}

	public String getEresto() {
		return eresto;
	}

	public void setEresto(String eresto) {
		this.eresto = eresto;
	}

	public String getInterrogatorio() {
		return interrogatorio;
	}

	public void setInterrogatorio(String interrogatorio) {
		this.interrogatorio = interrogatorio;
	}

	public String getAfamiliares() {
		return afamiliares;
	}

	public void setAfamiliares(String afamiliares) {
		this.afamiliares = afamiliares;
	}

	public String getApersonales() {
		return apersonales;
	}

	public void setApersonales(String apersonales) {
		this.apersonales = apersonales;
	}

	public String getEfgeneral() {
		return efgeneral;
	}

	public void setEfgeneral(String efgeneral) {
		this.efgeneral = efgeneral;
	}

	public String getEscomplementario() {
		return escomplementario;
	}

	public void setEscomplementario(String escomplementario) {
		this.escomplementario = escomplementario;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public String getTratamiento() {
		return tratamiento;
	}

	public void setTratamiento(String tratamiento) {
		this.tratamiento = tratamiento;
	}

	@Override
	public String toString() {
		return "HistoriaModel [id=" + id + ", ingresomodel=" + ingresomodel + ", mconsulta=" + mconsulta + ", etopografia="
				+ etopografia + ", emorfologia=" + emorfologia + ", eresto=" + eresto + ", interrogatorio="
				+ interrogatorio + ", afamiliares=" + afamiliares + ", apersonales=" + apersonales + ", efgeneral="
				+ efgeneral + ", escomplementario=" + escomplementario + ", diagnostico=" + diagnostico + ", tratamiento="
				+ tratamiento + "]";
	}

}
