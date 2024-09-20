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
@Table(name = "MOVIE_PRODUCER" )
public class MovieProducer {

	@EmbeddedId
	private MovieProducerId id;

	@ManyToOne
	@MapsId("idMovie" )
	private Movie movie;

	@ManyToOne
	@MapsId("idProducer" )
	private Producer producer;

	public MovieProducer(Movie movie, Producer producer) {
		this.movie = movie;
		this.producer = producer;
		this.id = new MovieProducerId(movie.getId(), producer.getId());
	}

	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;

		MovieProducer other = (MovieProducer) obj;
		return Objects.equals(movie, other.getMovie()) &&
				Objects.equals(producer, other.getProducer());
	}

	@Override
	public int hashCode() {
		return Objects.hash(movie, producer);
	}

}
