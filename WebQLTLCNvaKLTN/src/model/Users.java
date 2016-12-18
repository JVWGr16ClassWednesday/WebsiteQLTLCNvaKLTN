package model;

public class Users {
	private int id;
	private String username;
	private String password;
	private String myname;
	private int accessright;
	private String masv;
	private int khoatk;
	public int getKhoatk() {
		return khoatk;
	}
	public void setKhoatk(int khoatk) {
		this.khoatk = khoatk;
	}
	public String getMasv() {
		return masv;
	}
	public void setMasv(String masv) {
		this.masv = masv;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMyname() {
		return myname;
	}
	public void setMyname(String myname) {
		this.myname = myname;
	}
	public int getAccessright() {
		return accessright;
	}
	public void setAccessright(int accessright) {
		this.accessright = accessright;
	}
	

}
