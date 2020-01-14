package com.yotaku.exchanger.exchangerapi.domain;

import static com.yotaku.exchanger.exchangerapi.domain.User.TABLE_NAME;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity()
@Table(name = TABLE_NAME)
public class User implements UserDetails {

	public static final String TABLE_NAME = "users";

	public static class Properties {
		public static final String ID = "id";
		public static final String NAME = "name";
		public static final String ROLE = "roles";
		public static final String ACCOUNT = "account";
	}

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Positive
	private Long id;

	@Column(name = "name", nullable = false)
	@NotBlank
	@Size(max = 40)
	private String name;

	@Column(name = "username")
	@NotBlank
	@Size(max = 20)
	private String username;

	@Column(name = "password", columnDefinition = "char")
	@NotBlank
	@Size(max = 60)
	private String password;

	@NotNull
	@ManyToMany
	@JoinTable(name = "users_roles", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "role_id") })
	@JsonManagedReference
	private List<@Valid Role> roles = new ArrayList<>();

	@OneToOne(fetch = FetchType.LAZY, mappedBy = Account.Properties.USER)
	@Valid
	@JsonManagedReference
	private Account account;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles.clear();
		this.roles.addAll(roles);
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
