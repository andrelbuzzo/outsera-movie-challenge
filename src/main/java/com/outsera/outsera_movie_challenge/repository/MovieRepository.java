package com.outsera.outsera_movie_challenge.repository;

import com.outsera.outsera_movie_challenge.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
