package com.outsera.outsera_movie_challenge.repository;

import com.outsera.outsera_movie_challenge.dto.YearWinnerMovieDto;
import com.outsera.outsera_movie_challenge.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

	List<Movie> findByYear(Integer year);

	@Query(value = "select new com.outsera.outsera_movie_challenge.dto.YearWinnerMovieDto(movie.year, count(movie.winner)) "
			+ "from Movie as movie where movie.winner=true group by movie.year having count(movie.winner) > 1" )
	List<YearWinnerMovieDto> findYearsWithMoreThanOneWinner();

}
