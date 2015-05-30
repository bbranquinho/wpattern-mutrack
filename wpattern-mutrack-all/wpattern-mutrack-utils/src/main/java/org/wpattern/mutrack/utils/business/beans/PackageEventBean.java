package org.wpattern.mutrack.utils.business.beans;

import java.util.Date;

import org.wpattern.mutrack.utils.BaseBean;

public class PackageEventBean extends BaseBean {

	private static final long serialVersionUID = 201505301355L;

	private String type;

	private String status;

	private Date date;

	private String description;

	private String comment;

	private String location;

	private String cepUnity;

	private String city;

	private String uf;

	public PackageEventBean() {
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCepUnity() {
		return this.cepUnity;
	}

	public void setCepUnity(String cepUnity) {
		this.cepUnity = cepUnity;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getUf() {
		return this.uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

}
