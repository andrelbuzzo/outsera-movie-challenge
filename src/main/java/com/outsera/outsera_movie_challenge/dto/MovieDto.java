package com.outsera.outsera_movie_challenge.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto {

	@CsvBindByName(column = "year")
	private Integer year;

	@CsvBindByName(column = "title")
	private String title;

	@CsvBindByName(column = "studios")
	private String studios;

	@CsvBindByName(column = "producers")
	private String producers;

	@CsvBindByName(column = "winner")
	private boolean winner;
}
