package com.outsera.outsera_movie_challenge.service;

import com.outsera.outsera_movie_challenge.entity.Movie;
import com.outsera.outsera_movie_challenge.entity.MovieProducer;
import com.outsera.outsera_movie_challenge.entity.Producer;
import com.outsera.outsera_movie_challenge.repository.MovieProducerRepository;
import com.outsera.outsera_movie_challenge.repository.ProducerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProducerService {

	private final ProducerRepository producerRepository;
	private final MovieProducerRepository movieProducerRepository;

	public void saveProducers(Movie movie, String producers) {
		for (String strProducer : producers.split(",|\\ and ")) {
			Producer producer = new Producer(strProducer.trim());

			Example<Producer> example = Example.of(producer);

			if (producerRepository.exists(example)) {
				producer = producerRepository.findByName(strProducer.trim());
			} else {
				producer = producerRepository.save(producer);
			}

			movieProducerRepository.save(new MovieProducer(movie, producer));
		}
	}

}
