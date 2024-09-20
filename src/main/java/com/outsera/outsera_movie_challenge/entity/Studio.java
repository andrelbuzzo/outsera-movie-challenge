package com.outsera.outsera_movie_challenge.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "studios" )
public class Studio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String name;

	@OneToMany(mappedBy = "studio", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MovieStudio> movies = new ArrayList<>();

	public Studio(String name) {
		this.name = name;
	}

}
