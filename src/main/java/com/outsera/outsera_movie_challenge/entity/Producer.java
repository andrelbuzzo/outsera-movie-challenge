package com.outsera.outsera_movie_challenge.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "producers" )
public class Producer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NonNull
	private String name;

	@OneToMany(mappedBy = "producer", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MovieProducer> movies = new ArrayList<>();

	public Producer(String name) {
		this.name = name;
	}

}
