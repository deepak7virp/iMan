package com.inlighten.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "applications")
public class Application {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer application_id;
	
	@NotEmpty
	@Column(name="application_code", nullable=false)
	private String application_code;
		
	@NotEmpty
	@Column(name="application_name", nullable=false)
	private String application_name;

	public Integer getApplication_id() {
		return application_id;
	}

	public void setApplication_id(Integer application_id) {
		this.application_id = application_id;
	}

	public String getApplication_code() {
		return application_code;
	}

	public void setApplication_code(String application_code) {
		this.application_code = application_code;
	}

	public String getApplication_name() {
		return application_name;
	}

	public void setApplication_name(String application_name) {
		this.application_name = application_name;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((application_id == null) ? 0 : application_id.hashCode());
		//result = prime * result + ((ssoId == null) ? 0 : ssoId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Application))
			return false;
		Application other = (Application) obj;
		if (application_id == null) {
			if (other.application_id != null)
				return false;
		} else if (!application_id.equals(other.application_id))
			return false;
		if (application_name == null) {
			if (other.application_name != null)
				return false;
		} else if (!application_name.equals(other.application_name))
			return false;
		return true;
	}

	/*
	 * DO-NOT-INCLUDE passwords in toString function.
	 * It is done here just for convenience purpose.
	 */
	@Override
	public String toString() {
		return "Application [application_id=" + application_id + ", name=" + application_name
				+ ", code=" + application_code + "]";
	}
}
