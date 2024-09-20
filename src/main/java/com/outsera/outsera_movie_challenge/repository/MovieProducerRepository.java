package com.outsera.outsera_movie_challenge.repository;

import com.outsera.outsera_movie_challenge.entity.MovieProducer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieProducerRepository extends JpaRepository<MovieProducer, Long> {
}
