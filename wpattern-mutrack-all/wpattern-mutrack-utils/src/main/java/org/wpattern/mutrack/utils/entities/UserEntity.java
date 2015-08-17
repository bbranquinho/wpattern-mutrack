package org.wpattern.mutrack.utils.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.wpattern.mutrack.utils.BaseEntity;

@Entity
@Table(name = "tb_user")
public class UserEntity extends BaseEntity<Long> {

	private static final long serialVersionUID = 201505091515L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "name", length = 120, nullable = false)
	private String name;

	@Column(name = "email", length = 255, nullable = false, unique = true)
	private String email;

	//@JsonIgnore
	@Column(name = "password", length = 128, nullable = false)
	private String password;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tb_user_permission", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "permission_id") )
	private List<PermissionEntity> permissions;

	public UserEntity() {
	}

	public UserEntity(Long id) {
		this.setId(id);
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

	public List<PermissionEntity> getPermissions() {
		return this.permissions;
	}

	public void setPermissions(List<PermissionEntity> permissions) {
		this.permissions = permissions;
	}

}
