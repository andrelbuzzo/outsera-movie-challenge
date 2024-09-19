package com.outsera.outsera_movie_challenge;

import com.outsera.outsera_movie_challenge.service.MovieService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OutseraMovieChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(OutseraMovieChallengeApplication.class, args);
	}

	// inserting data after application is up
	@Bean
	CommandLineRunner run(MovieService movieService) {
		return args -> {
			if (movieService.count() == 0) {
				movieService.saveFromCsv();
			}
		};
	}

}
