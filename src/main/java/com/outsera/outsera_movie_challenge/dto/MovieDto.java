package com.outsera.outsera_movie_challenge.dto;

import com.outsera.outsera_movie_challenge.entity.Movie;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
//@Builder
public class MovieDto {

	private Integer year;

	private String title;

	private List<String> studios = new ArrayList<>();

	private List<String> producers = new ArrayList<>();

	private boolean winner;

	/*public MovieDto(Integer year, String title, List<String> studios, List<String> producers, boolean winner) {
		this.year = year;
		this.title = title;
		this.winner = winner;
		studios.forEach(ms -> this.studios.add(ms));
		producers.forEach(mp -> this.producers.add(mp));
	}*/

	public MovieDto(Movie movie) {
		this.year = movie.getYear();
		this.title = movie.getTitle();
		this.winner = movie.isWinner();
		movie.getStudios().forEach(ms -> this.getStudios().add(ms.getStudio().getName()));
		movie.getProducers().forEach(mp -> this.producers.add(mp.getProducer().getName()));
	}
}
