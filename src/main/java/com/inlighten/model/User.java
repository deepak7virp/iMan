package com.inlighten.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="SingleSignOnUsers")
public class User implements Serializable{

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer userId;
	
	@NotEmpty
	@Column(name="userPassword", nullable=false)
	private String userPassword;
		
	@NotEmpty
	@Column(name="userEmail", nullable=false)
	private String userEmail;
	
	@OneToMany(mappedBy = "user")
	private List<UserApplication> userApplications;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String password) {
		this.userPassword = password;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public List<UserApplication> getUserApplications() {
		return userApplications;
	}

	public void setUserApplication(List<UserApplication> userApplications) {
		this.userApplications = userApplications;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		//result = prime * result + ((ssoId == null) ? 0 : ssoId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (userEmail == null) {
			if (other.userEmail != null)
				return false;
		} else if (!userEmail.equals(other.userEmail))
			return false;
		return true;
	}

	/*
	 * DO-NOT-INCLUDE passwords in toString function.
	 * It is done here just for convenience purpose.
	 */
	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + userPassword
				+ ", email=" + userEmail + "]";
	}


	
}
