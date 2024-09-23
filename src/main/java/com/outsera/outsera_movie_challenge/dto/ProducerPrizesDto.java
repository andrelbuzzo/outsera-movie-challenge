package com.outsera.outsera_movie_challenge.dto;

import com.outsera.outsera_movie_challenge.entity.MovieProducer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProducerPrizesDto {
	private String producer;

	private Integer interval;

	private Integer previousWin;

	private Integer followingWin;

	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;

		ProducerPrizesDto other = (ProducerPrizesDto) obj;
		return Objects.equals(producer, other.getProducer());
	}

}
