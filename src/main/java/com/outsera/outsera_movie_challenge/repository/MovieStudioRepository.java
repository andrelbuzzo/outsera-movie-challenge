package com.outsera.outsera_movie_challenge.repository;

import com.outsera.outsera_movie_challenge.entity.MovieStudio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieStudioRepository extends JpaRepository<MovieStudio, Long> {
}
