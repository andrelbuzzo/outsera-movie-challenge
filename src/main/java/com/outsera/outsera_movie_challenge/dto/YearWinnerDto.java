package com.outsera.outsera_movie_challenge.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class YearWinnerDto {
	private List<YearWinnerMovieDto> years;

	public YearWinnerDto(List<YearWinnerMovieDto> years) {
		this.years = new ArrayList<>();
		this.years.addAll(years);
	}
}
