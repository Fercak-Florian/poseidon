package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import com.nnk.springboot.validators.PasswordConstraint;
import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class User {

	public User() {
	}

	public User(String username, String password, String fullname, String role) {
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.role = role;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@NotBlank(message = "Username is mandatory")
	@Column(name = "username")
	private String username;

	@PasswordConstraint
	@Column(name = "password")
	private String password;

	@NotBlank(message = "FullName is mandatory")
	@Column(name = "fullname")
	private String fullname;

	@NotBlank(message = "Role is mandatory")
	@Column(name = "role")
	private String role;
}
