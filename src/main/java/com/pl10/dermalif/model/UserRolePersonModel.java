package com.pl10.dermalif.model;

public class UserRolePersonModel {

	private String username;
	private boolean usersystem;
	private boolean pacrole;
	private boolean citrole;
	private boolean ingrole;
	private boolean hclrole;
	private boolean facrole;
	private boolean docrole;

	public UserRolePersonModel() {
		super();
	}

	public UserRolePersonModel(String username, boolean usersystem, boolean pacrole, boolean citrole, boolean ingrole,
			boolean hclrole, boolean facrole, boolean docrole) {
		super();
		this.username = username;
		this.usersystem = usersystem;
		this.pacrole = pacrole;
		this.citrole = citrole;
		this.ingrole = ingrole;
		this.hclrole = hclrole;
		this.facrole = facrole;
		this.docrole = docrole;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isUsersystem() {
		return usersystem;
	}

	public void setUsersystem(boolean usersystem) {
		this.usersystem = usersystem;
	}

	public boolean isPacrole() {
		return pacrole;
	}

	public void setPacrole(boolean pacrole) {
		this.pacrole = pacrole;
	}

	public boolean isCitrole() {
		return citrole;
	}

	public void setCitrole(boolean citrole) {
		this.citrole = citrole;
	}

	public boolean isIngrole() {
		return ingrole;
	}

	public void setIngrole(boolean ingrole) {
		this.ingrole = ingrole;
	}

	public boolean isHclrole() {
		return hclrole;
	}

	public void setHclrole(boolean hclrole) {
		this.hclrole = hclrole;
	}

	public boolean isFacrole() {
		return facrole;
	}

	public void setFacrole(boolean facrole) {
		this.facrole = facrole;
	}

	public boolean isDocrole() {
		return docrole;
	}

	public void setDocrole(boolean docrole) {
		this.docrole = docrole;
	}

	@Override
	public String toString() {
		return "UserRolePersonModel [username=" + username + ", usersystem=" + usersystem + ", pacrole=" + pacrole
				+ ", citrole=" + citrole + ", ingrole=" + ingrole + ", hclrole=" + hclrole + ", facrole=" + facrole
				+ ", docrole=" + docrole + "]";
	}

}
