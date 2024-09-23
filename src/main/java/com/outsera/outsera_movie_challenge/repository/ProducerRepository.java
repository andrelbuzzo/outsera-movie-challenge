package com.outsera.outsera_movie_challenge.repository;

import com.outsera.outsera_movie_challenge.entity.Producer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, Long> {
	Producer findByName(String name);
}
