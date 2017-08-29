package com.pl10.dermalif.model;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.pl10.dermalif.enums.TypeCivilStatus;
import com.pl10.dermalif.enums.TypeDocument;
import com.pl10.dermalif.enums.TypeSexo;

public class PersonModel {

	private String id;
	private TypeDocument tdocumento;
	@NotBlank(message = "Este campo no puede estar vacío")
	@NotNull(message = "Este campo no puede ser nulo")
	private String ndocumento;
	@NotBlank(message = "Este campo no puede estar vacío")
	@NotNull(message = "Este campo no puede ser nulo")
	@Size(min = 2, max = 20, message = "El tamaño debe ser entre 2 y 20")
	private String firstname;
	private String firstname2;
	@NotBlank(message = "Este campo no puede estar vacío")
	@NotNull(message = "Este campo no puede ser nulo")
	@Size(min = 2, max = 20, message = "El tamaño debe ser entre 2 y 20")
	private String lastname;
	private String lastname2;
	@NotNull(message = "Este campo no puede ser nulo")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date birthdate;
	private TypeSexo tsexo;
	private TypeCivilStatus tcivilstatus;
	private CityModel cnacimiento;
	private CityModel cresidencia;
	private String direccion;
	private String telefono;
	private String email;
	private String imageprofile;

	public PersonModel() {
	}

	public PersonModel(String id, TypeDocument tdocumento, String ndocumento, String firstname, String firstname2,
			String lastname, String lastname2, Date birthdate, TypeSexo tsexo, TypeCivilStatus tcivilstatus,
			CityModel cnacimiento, CityModel cresidencia, String direccion, String telefono, String email) {
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

	public CityModel getCnacimiento() {
		return cnacimiento;
	}

	public void setCnacimiento(CityModel cnacimiento) {
		this.cnacimiento = cnacimiento;
	}

	public CityModel getCresidencia() {
		return cresidencia;
	}

	public void setCresidencia(CityModel cresidencia) {
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
		return "PersonModel [id=" + id + ", tdocumento=" + tdocumento + ", ndocumento=" + ndocumento + ", firstname="
				+ firstname + ", firstname2=" + firstname2 + ", lastname=" + lastname + ", lastname2=" + lastname2
				+ ", birthdate=" + birthdate + ", tsexo=" + tsexo + ", tcivilstatus=" + tcivilstatus + ", cnacimiento="
				+ cnacimiento + ", cresidencia=" + cresidencia + ", direccion=" + direccion + ", telefono=" + telefono
				+ ", email=" + email + ", imageprofile=" + imageprofile + "]";
	}

}
