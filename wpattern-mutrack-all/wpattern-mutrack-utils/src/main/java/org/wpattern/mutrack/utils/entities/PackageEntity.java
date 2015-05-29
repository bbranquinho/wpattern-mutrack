package org.wpattern.mutrack.utils.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.wpattern.mutrack.utils.BaseEntity;

@Entity
@Table(name = "tb_package")
public class PackageEntity extends BaseEntity<Long> {

	private static final long serialVersionUID = 201505091506L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "name", length = 60, nullable = false)
	private String name;

	@Column(name = "code", length = 20, nullable = false)
	private String code;

	@Column(name = "description", length = 150, nullable = true)
	private String description;

	@Column(name = "destination_cep", length = 12, nullable = true)
	private String destinationCep;

	@Column(name = "register_date", nullable = false)
	private Date registerDate;

	@ManyToOne
	@JoinColumn(name = "owner_id")
	private UserEntity user;

	public PackageEntity() {
	}

	public PackageEntity(String name, String code, String description,
			String destinationCep, Date registerDate, UserEntity user) {
		this.name = name;
		this.code = code;
		this.description = description;
		this.destinationCep = destinationCep;
		this.registerDate = registerDate;
		this.user = user;
	}

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDestinationCep() {
		return this.destinationCep;
	}

	public void setDestinationCep(String destinationCep) {
		this.destinationCep = destinationCep;
	}

	public Date getRegisterDate() {
		return this.registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public UserEntity getUser() {
		return this.user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

}
