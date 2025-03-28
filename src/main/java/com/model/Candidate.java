package com.model;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Table(name = "candidates")
public class Candidate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(unique = true, nullable = false)
	private String candidate;

	@Column(nullable = false)
	private int votes;
}
