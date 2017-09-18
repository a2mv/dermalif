package com.pl10.dermalif.model;

import java.util.List;

public class FacturaJsonObject {
	int iTotalRecords;

	int iTotalDisplayRecords;

	String sEcho;

	String sColumns;

	List<FacturaModel> aaData;

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

	public List<FacturaModel> getAaData() {
		return aaData;
	}

	public void setAaData(List<FacturaModel> aaData) {
		this.aaData = aaData;
	}
}
