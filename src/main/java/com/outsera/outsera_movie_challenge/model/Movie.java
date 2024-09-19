package com.outsera.outsera_movie_challenge.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movies")
public class Movie implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Digits(integer = 4, fraction = 0)
	@Column(name = "`YEAR`")
	private Integer year;
	private String title;
	private String studios;
	private String producers;

	@Nullable
	@JsonIgnore
	private boolean winner;

}
