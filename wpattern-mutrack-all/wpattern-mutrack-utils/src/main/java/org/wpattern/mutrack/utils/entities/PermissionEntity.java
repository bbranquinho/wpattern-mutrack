package org.wpattern.mutrack.utils.entities;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.wpattern.mutrack.utils.BaseEntity;
import org.wpattern.mutrack.utils.entities.converters.PermissionConverter;
import org.wpattern.mutrack.utils.entities.types.PermissionType;

@Entity
@Table(name = "tb_permission")
@XmlRootElement
public class PermissionEntity extends BaseEntity<Long> {

	private static final long serialVersionUID = 201505091608L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "role")
	@Convert(converter = PermissionConverter.class)
	private PermissionType permission;

	public PermissionEntity() {
	}

	@Override
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PermissionType getPermission() {
		return this.permission;
	}

	public void setPermission(PermissionType permission) {
		this.permission = permission;
	}

}
