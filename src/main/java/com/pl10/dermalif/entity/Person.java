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

import com.pl10.dermalif.enums.TypeCivilStatus;
import com.pl10.dermalif.enums.TypeDocument;
import com.pl10.dermalif.enums.TypeSexo;

@Entity
@Table(name = "persona")
public class Person {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(unique = true, nullable = false, updatable = false)
	private String id;
	@Column(name = "tdocumento", nullable = false)
	private TypeDocument tdocumento;
	@Column(name = "ndocumento", nullable = false, length = 60)
	private String ndocumento;
	@Column(name = "firstname", nullable = true, length = 45)
	private String firstname;
	@Column(name = "firstname2", nullable = true, length = 45)
	private String firstname2;
	@Column(name = "lastname", nullable = true, length = 45)
	private String lastname;
	@Column(name = "lastname2", nullable = true, length = 45)
	private String lastname2;
	@Column(name = "birthdate", nullable = false)
	@Type(type = "date")
	private Date birthdate;
	@Column(name = "tsexo", nullable = false)
	private TypeSexo tsexo;
	@Column(name = "tcivilstatus", nullable = false)
	private TypeCivilStatus tcivilstatus;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cnacimiento", nullable = false)
	private City cnacimiento;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cresidencia", nullable = false)
	private City cresidencia;
	@Column(name = "direccion", nullable = true)
	private String direccion;
	@Column(name = "telefono", nullable = true)
	private String telefono;
	@Column(name = "email", nullable = true)
	private String email;
	@Column(name = "imageprofile", nullable = true)
	private String imageprofile;

	public Person() {
		super();
	}

	public Person(String id, TypeDocument tdocumento, String ndocumento, String firstname, String firstname2,
			String lastname, String lastname2, Date birthdate, TypeSexo tsexo, TypeCivilStatus tcivilstatus,
			City cnacimiento, City cresidencia, String direccion, String telefono, String email) {
		super();
		this.id = id;
		this.tdocumento = tdocumento;
		this.ndocumento = ndocumento;
		this.firstname = firstname;
		this.firstname2 = firstname2;
		this.lastname = lastname;
		this.lastname2 = lastname2;
		this.birthdate = birthdate;
		this.tsexo = tsexo;
		this.tcivilstatus = tcivilstatus;
		this.cnacimiento = cnacimiento;
		this.cresidencia = cresidencia;
		this.direccion = direccion;
		this.telefono = telefono;
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public TypeDocument getTdocumento() {
		return tdocumento;
	}

	public void setTdocumento(TypeDocument tdocumento) {
		this.tdocumento = tdocumento;
	}

	public String getNdocumento() {
		return ndocumento;
	}

	public void setNdocumento(String ndocumento) {
		this.ndocumento = ndocumento;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getFirstname2() {
		return firstname2;
	}

	public void setFirstname2(String firstname2) {
		this.firstname2 = firstname2;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getLastname2() {
		return lastname2;
	}

	public void setLastname2(String lastname2) {
		this.lastname2 = lastname2;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public TypeSexo getTsexo() {
		return tsexo;
	}

	public void setTsexo(TypeSexo tsexo) {
		this.tsexo = tsexo;
	}

	public TypeCivilStatus getTcivilstatus() {
		return tcivilstatus;
	}

	public void setTcivilstatus(TypeCivilStatus tcivilstatus) {
		this.tcivilstatus = tcivilstatus;
	}

	public City getCnacimiento() {
		return cnacimiento;
	}

	public void setCnacimiento(City cnacimiento) {
		this.cnacimiento = cnacimiento;
	}

	public City getCresidencia() {
		return cresidencia;
	}

	public void setCresidencia(City cresidencia) {
		this.cresidencia = cresidencia;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImageprofile() {
		return imageprofile;
	}

	public void setImageprofile(String imageprofile) {
		this.imageprofile = imageprofile;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", tdocumento=" + tdocumento + ", ndocumento=" + ndocumento + ", firstname="
				+ firstname + ", firstname2=" + firstname2 + ", lastname=" + lastname + ", lastname2=" + lastname2
				+ ", birthdate=" + birthdate + ", tsexo=" + tsexo + ", tcivilstatus=" + tcivilstatus + ", cnacimiento="
				+ cnacimiento + ", cresidencia=" + cresidencia + ", direccion=" + direccion + ", telefono=" + telefono
				+ ", email=" + email + ", imageprofile=" + imageprofile + "]";
	}

}
