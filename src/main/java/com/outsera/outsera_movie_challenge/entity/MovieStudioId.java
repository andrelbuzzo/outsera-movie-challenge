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
public class MovieStudioId implements Serializable {

	private static final long serialVersionUID = -5522529450406784648L;

	private Long idMovie;

	private Long idStudio;

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;

		MovieStudioId other = (MovieStudioId) obj;
		return Objects.equals(idMovie, other.getIdMovie()) &&
				Objects.equals(idStudio, other.getIdStudio());
	}

	@Override
	public int hashCode() {
		return Objects.hash(idMovie, idStudio);
	}

}
