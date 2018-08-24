package beans;

import java.io.Serializable;

public class UserDataBeans implements Serializable{
	private int id;
	private String name;
	private String address;
	private String email;
	private String password;

	public UserDataBeans() {
		this.name = "";
		this.address = "";
		this.email = "";
		this.password ="";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
