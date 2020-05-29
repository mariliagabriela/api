package com.desafio.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@NamedQueries({ @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u") })
@JsonInclude(Include.NON_NULL)
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String firstName;

	private String lastName;

	private String email;

	private Date birthday;

	private String login;

	private String password;

	private String phone;

	// @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval
	// = true)
	// @JoinColumn(name = "USUARIO_ID", referencedColumnName = "id")

	/*
	 * @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval
	 * = true)
	 * 
	 * @JoinColumn(name = "Usuario_ID", referencedColumnName = "id") //@Valid private
	 * List<Carro> cars;
	 */

	private Date lastLogin;

	// getters e setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean validarFields() {
		if (firstName == null || lastName == null || password == null || email == null || birthday == null
				|| login == null || phone == null)
			return false;

		return true;
	}

	@Transient
	public String toString() {
		return "firtsName = " + this.firstName + " | lastName = " + this.lastName + " | email = " + this.email
				+ " | birthday = " + this.birthday + " | login = " + this.login + " | password = " + this.password
				+ " | phone = " + this.phone;
	}

}
