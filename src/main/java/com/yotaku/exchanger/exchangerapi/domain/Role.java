package com.yotaku.exchanger.exchangerapi.domain;

import static com.yotaku.exchanger.exchangerapi.domain.Role.TABLE_NAME;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.security.core.GrantedAuthority;

@Entity()
@Table(name = TABLE_NAME)
public class Role implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	public static final String TABLE_NAME = "roles";

	public static class Properties {
		public static final String ID = "id";
		public static final String NAME = "name";
	}

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Positive
	private Long id;

	@Column(name = "name")
	@NotNull
	@Enumerated(EnumType.STRING)
	private UserRole name;

	public Long getId() {
		return id;
	}

	public UserRole getName() {
		return name;
	}

	public void setName(UserRole name) {
		this.name = name;
	}

	@Override
	public String getAuthority() {
		return name.name();
	}

}
