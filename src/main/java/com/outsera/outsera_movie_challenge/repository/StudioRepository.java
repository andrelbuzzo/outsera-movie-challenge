package com.outsera.outsera_movie_challenge.repository;

import com.outsera.outsera_movie_challenge.entity.Studio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudioRepository extends JpaRepository<Studio, Long> {
	Studio findByName(String name);
}
