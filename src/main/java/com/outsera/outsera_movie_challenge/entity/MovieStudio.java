package com.outsera.outsera_movie_challenge.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "MOVIE_STUDIO" )
public class MovieStudio {

	@EmbeddedId
	private MovieStudioId id;

	@ManyToOne
	@MapsId("idMovie" )
	private Movie movie;

	@ManyToOne
	@MapsId("idStudio" )
	private Studio studio;

	public MovieStudio(Movie movie, Studio studio) {
		this.movie = movie;
		this.studio = studio;
		this.id = new MovieStudioId(movie.getId(), studio.getId());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;

		MovieStudio other = (MovieStudio) obj;
		return Objects.equals(movie, other.getMovie()) &&
				Objects.equals(studio, other.getStudio());
	}

	@Override
	public int hashCode() {
		return Objects.hash(movie, studio);
	}

}
