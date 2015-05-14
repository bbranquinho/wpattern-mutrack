package org.wpattern.mutrack.utils.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.wpattern.mutrack.utils.BaseEntity;

@Entity
@Table(name = "tb_user")
@XmlRootElement
public class UserEntity extends BaseEntity<Long> {

	private static final long serialVersionUID = 201505091515L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "name", length = 120, nullable = false)
	private String name;

	@Column(name = "email", length = 255, nullable = false, unique = true)
	private String email;

	@Column(name = "password", length = 128, nullable = false)
	private String password;

	// This property was removed to simplify the management of the project.
	// @OneToMany(fetch = FetchType.EAGER, targetEntity = PackageEntity.class)
	// @JoinColumn(name = "owner_id")
	// private List<PackageEntity> packages;

	public UserEntity() {
	}

	public UserEntity(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
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

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// public List<PackageEntity> getPackages() {
	// return this.packages;
	// }
	//
	// public void setPackages(List<PackageEntity> packages) {
	// this.packages = packages;
	// }

}
