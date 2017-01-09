package com.inlighten.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="SingleSignOnUserApps")
public class UserApplication {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;	
	
	@ManyToOne
	@JoinColumn(name="userId", nullable=false)
	public User getUser() { return user; }
	public void setUser(User user) {
		this.user = user;
	}
	private User user;
	
	@NotEmpty
	@Column(name="application_id", nullable=false)
	private Integer application_id;
	
	@NotEmpty
	@Column(name="application_name", nullable=false)
	private String application_name;
	
	@NotEmpty
	@Column(name="application_code", nullable=false)
	private String application_code;
	
	@NotEmpty
	@Column(name="app_username", nullable=false)
	private String app_username;
	
	@NotEmpty
	@Column(name="app_password", nullable=false)
	private String app_password;
	
	private Boolean verified = false;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getVerified() {
		return verified;
	}
	public void setVerified(Boolean verified) {
		this.verified = verified;
	}
	
	public Integer getApplication_id() {
		return application_id;
	}

	public void setApplication_id(Integer application_id) {
		this.application_id = application_id;
	}

	public String getApplication_name() {
		return application_name;
	}

	public void setApplication_name(String application_name) {
		this.application_name = application_name;
	}

	public String getApplication_code() {
		return application_code;
	}

	public void setApplication_code(String application_code) {
		this.application_code = application_code;
	}

	public String getApp_username() {
		return app_username;
	}

	public void setApp_username(String app_username) {
		this.app_username = app_username;
	}

	public String getApp_password() {
		return app_password;
	}

	public void setApp_password(String app_password) {
		this.app_password = app_password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		//result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof UserApplication))
			return false;
		UserApplication other = (UserApplication) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserProfile [id=" + id + "]";
	}
}
