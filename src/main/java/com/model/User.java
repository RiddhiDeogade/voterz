package com.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(unique = true, nullable = false)
	private String email;

	private String name;

	@Column(nullable = false)
	private String password;

	private int phone;

	private String status = "Not Voted"; // Default value

	@Column(nullable = false)
	private String role;
}
