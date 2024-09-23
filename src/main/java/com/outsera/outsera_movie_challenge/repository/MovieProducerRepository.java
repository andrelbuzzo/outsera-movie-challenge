package com.outsera.outsera_movie_challenge.repository;

import com.outsera.outsera_movie_challenge.entity.MovieProducer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieProducerRepository extends JpaRepository<MovieProducer, Long> {

	/*@Query(value="select mp from MovieProducer as mp join mp.movie as movie join mp.producer as producer "
			+ "where movie.winner = true order by producer.id, movie.year")*/
	List<MovieProducer> findByMovieWinnerOrderByProducerId(Boolean isWinner);

}
