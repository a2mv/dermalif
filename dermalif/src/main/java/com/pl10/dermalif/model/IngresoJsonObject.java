package com.pl10.dermalif.model;

import java.util.List;

public class IngresoJsonObject {

	int iTotalRecords;

	int iTotalDisplayRecords;

	String sEcho;

	String sColumns;

	List<IngresoModel> aaData;

	public int getiTotalRecords() {
		return iTotalRecords;
	}

	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}

	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

	public String getsEcho() {
		return sEcho;
	}

	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}

	public String getsColumns() {
		return sColumns;
	}

	public void setsColumns(String sColumns) {
		this.sColumns = sColumns;
	}

	public List<IngresoModel> getAaData() {
		return aaData;
	}

	public void setAaData(List<IngresoModel> aaData) {
		this.aaData = aaData;
	}
}
