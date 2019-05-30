package org.masterjung.dto;

import java.sql.Date;

public class UserDto {
	private int id;
	private String email;
	private String password;
	private String nick_name;
	private int phone_number;
	private String user_name;
	private String user_address;
	private String created_ip;
	private int user_auth;
	private String user_image_path;
	private int enabled; //default
	private Date date_created; //default
	private Date last_updated; //default
	
	public UserDto(){}
	
	public UserDto(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public UserDto(int id, String email, String password, String nick_name, String user_name, String user_address,
			String created_ip) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.nick_name = nick_name;
		this.user_name = user_name;
		this.user_address = user_address;
		this.created_ip = created_ip;
	}

	public UserDto(int id, String email, String password, String nick_name, String user_name, String user_address,
			String created_ip, Date last_updated) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.nick_name = nick_name;
		this.user_name = user_name;
		this.user_address = user_address;
		this.created_ip = created_ip;
		this.last_updated = last_updated;
	}
	
	public UserDto(int id, String email, String password, String nick_name, String user_name,
			String created_ip) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.nick_name = nick_name;
		this.user_name = user_name;
		this.created_ip = created_ip;
	}
	
	public UserDto(int id, String email, String password, String nick_name, String user_name,
			String created_ip, Date last_updated) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.nick_name = nick_name;
		this.user_name = user_name;
		this.created_ip = created_ip;
		this.last_updated = last_updated;
	}

	@Override
	public String toString() {
		return "userdto [id=" + id + ", email=" + email + ", password=" + password + ", nick_name=" + nick_name
				+ ", user_name=" + user_name + ", user_address=" + user_address + ", created_ip=" + created_ip
				+ ", enabled=" + enabled + ", date_created=" + date_created + ", last_updated=" + last_updated + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public int getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(int phone_number) {
		this.phone_number = phone_number;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_address() {
		return user_address;
	}

	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}

	public String getCreated_ip() {
		return created_ip;
	}

	public void setCreated_ip(String created_ip) {
		this.created_ip = created_ip;
	}

	public int getUser_auth() {
		return user_auth;
	}

	public void setUser_auth(int user_auth) {
		this.user_auth = user_auth;
	}

	public String getUser_image_path() {
		return user_image_path;
	}

	public void setUser_image_path(String user_image_path) {
		this.user_image_path = user_image_path;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public Date getDate_created() {
		return date_created;
	}

	public void setDate_created(Date date_created) {
		this.date_created = date_created;
	}

	public Date getLast_updated() {
		return last_updated;
	}

	public void setLast_updated(Date last_updated) {
		this.last_updated = last_updated;
	}
	
	
}
