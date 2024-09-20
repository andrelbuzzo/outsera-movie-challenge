package com.outsera.outsera_movie_challenge.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "movies" )
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Digits(integer = 4, fraction = 0)
	@Column(name = "`YEAR`" )
	private Integer year;
	private String title;

	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@Nullable
	private Set<MovieStudio> studios = new HashSet<>();

	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@Nullable
	private Set<MovieProducer> producers = new HashSet<>();

	@Nullable
	@JsonIgnore
	private boolean winner;

	public Movie(Integer year, String title, String winner) {
		this.year = year;
		this.title = title;
		this.winner = (winner != null && "yes".equalsIgnoreCase(winner));
	}

	@Override
	public String toString() {
		return "Year: " + getYear() + " - Title: " + getTitle() + " - Winner: " + isWinner();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;

		Movie other = (Movie) obj;
		return Objects.equals(id, other.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, year);
	}

}
