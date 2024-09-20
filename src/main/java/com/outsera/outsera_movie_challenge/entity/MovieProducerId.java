package com.outsera.outsera_movie_challenge.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class MovieProducerId implements Serializable {

	private static final long serialVersionUID = -5332423598071950748L;

	private Long idMovie;

	private Long idProducer;

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;

		MovieProducerId other = (MovieProducerId) obj;
		return Objects.equals(idMovie, other.getIdMovie()) &&
				Objects.equals(idProducer, other.getIdProducer());
	}

	@Override
	public int hashCode() {
		return Objects.hash(idMovie, idProducer);
	}

}
