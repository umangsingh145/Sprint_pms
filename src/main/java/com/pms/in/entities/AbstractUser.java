package com.pms.in.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "user_details")
public class AbstractUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int uid;

	@Column(unique = true, nullable=false)
	private String userName;

	@Column(unique = true, nullable=false)
	private String password;
	
	@Column
	private Role role;
	
	public AbstractUser() {
		super();
	}

	public AbstractUser(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public AbstractUser(String userName, String password, Role role) {
		super();
		this.userName = userName;
		this.password = password;
		this.role = role;
	}

	public AbstractUser(int uid, String userName, String password) {
		super();
		this.uid = uid;
		this.userName = userName;
		this.password = password;
	}

	public AbstractUser(int uid, String userName, String password, Role role) {
		super();
		this.uid = uid;
		this.userName = userName;
		this.password = password;
		this.role = role;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof AbstractUser)) {
			return false;
		}
		AbstractUser other = (AbstractUser) obj;
		if (password == null) {
			if (other.password != null) {
				return false;
			}
		} else if (!password.equals(other.password)) {
			return false;
		}
		if (role != other.role) {
			return false;
		}
		if (userName == null) {
			if (other.userName != null) {
				return false;
			}
		} else if (!userName.equals(other.userName)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "AppUser [uid=" + uid + ", userName=" + userName + ", password=" + password + ", role=" + role + "]";
	}


	}