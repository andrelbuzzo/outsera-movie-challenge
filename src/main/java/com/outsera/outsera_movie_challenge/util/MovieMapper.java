package com.outsera.outsera_movie_challenge.util;

import com.outsera.outsera_movie_challenge.dto.MovieDto;
import com.outsera.outsera_movie_challenge.model.Movie;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class MovieMapper implements CSVMapper<Movie, MovieDto> {

	@Override
	public Movie mapTo(MovieDto k) {
		return Movie.builder()
				.year(k.getYear())
				.title(k.getTitle())
				.studios(k.getStudios())
				.producers(k.getProducers())
				.winner(Optional.ofNullable(k.isWinner()).orElse(false))
				.build();
	}

	@Override
	public MovieDto unmapFrom(Movie t) {
		return MovieDto.builder()
				.year(t.getYear())
				.title(t.getTitle())
				.studios(t.getStudios())
				.producers(t.getProducers())
				.winner(Optional.ofNullable(t.isWinner()).orElse(false))
				.build();
	}

}
