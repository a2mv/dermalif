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
@Table(name = "historia")
public class Historia {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(unique = true, nullable = false, updatable = false)
	private String id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ingreso", nullable = false)
	private Ingreso ingreso;
	@Column(name = "mconsulta", nullable = false, columnDefinition="TEXT")
	private String mconsulta;
	@Column(name = "etopografia", columnDefinition="TEXT")
	private String etopografia;
	@Column(name = "emorfologia", columnDefinition="TEXT")
	private String emorfologia;
	@Column(name = "eresto", columnDefinition="TEXT")
	private String eresto;
	@Column(name = "interrogatorio", nullable = false, columnDefinition="TEXT")
	private String interrogatorio;
	@Column(name = "afamiliares", columnDefinition="TEXT")
	private String afamiliares;
	@Column(name = "apersonales", columnDefinition="TEXT")
	private String apersonales;
	@Column(name = "efgeneral", nullable = false, columnDefinition="TEXT")
	private String efgeneral;
	@Column(name = "escomplementario", columnDefinition="TEXT")
	private String escomplementario;
	@Column(name = "diagnostico", nullable = false, columnDefinition="TEXT")
	private String diagnostico;
	@Column(name = "tratamiento", columnDefinition="TEXT")
	private String tratamiento;

	public Historia() {
		super();
	}

	public Historia(String id, Ingreso ingreso, String mconsulta, String etopografia, String emorfologia, String eresto,
			String interrogatorio, String afamiliares, String apersonales, String efgeneral, String escomplementario,
			String diagnostico, String tratamiento) {
		super();
		this.id = id;
		this.ingreso = ingreso;
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

	public Ingreso getIngreso() {
		return ingreso;
	}

	public void setIngreso(Ingreso ingreso) {
		this.ingreso = ingreso;
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
		return "Historia [id=" + id + ", ingreso=" + ingreso + ", mconsulta=" + mconsulta + ", etopografia="
				+ etopografia + ", emorfologia=" + emorfologia + ", eresto=" + eresto + ", interrogatorio="
				+ interrogatorio + ", afamiliares=" + afamiliares + ", apersonales=" + apersonales + ", efgeneral="
				+ efgeneral + ", escomplementario=" + escomplementario + ", diagnostico=" + diagnostico + ", tratamiento="
				+ tratamiento + "]";
	}

}
