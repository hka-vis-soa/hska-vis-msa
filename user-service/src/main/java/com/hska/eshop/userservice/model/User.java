package com.hska.eshop.userservice.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users", schema="userservice")
public class User {

	private Long id;
	private String firstname;
	private String lastname;

	/**
	 * Don't remove hibernate empty constructor
	 */
	public User() {
	}

	public User(Long id, String firstname, String lastname) {
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "firstname", nullable = false)
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	@Column(name = "lastname", nullable = false)
	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
}
