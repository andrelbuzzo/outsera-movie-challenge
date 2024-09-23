package com.outsera.outsera_movie_challenge.service;

import com.outsera.outsera_movie_challenge.dto.ProducerMinMaxPrizesDto;
import com.outsera.outsera_movie_challenge.dto.ProducerPrizesDto;
import com.outsera.outsera_movie_challenge.entity.Movie;
import com.outsera.outsera_movie_challenge.entity.MovieProducer;
import com.outsera.outsera_movie_challenge.entity.Producer;
import com.outsera.outsera_movie_challenge.repository.MovieProducerRepository;
import com.outsera.outsera_movie_challenge.repository.ProducerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
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

	public ProducerMinMaxPrizesDto getMaxAndMinPrizes() {
		List<MovieProducer> mpList = movieProducerRepository.findByMovieWinnerOrderByProducerId(true);

		ProducerMinMaxPrizesDto Dto = new ProducerMinMaxPrizesDto();
		Dto.addMin(filterMovieProducers(mpList, "min"));
		Dto.addMax(filterMovieProducers(mpList, "max"));

		return Dto;
	}

	private List<ProducerPrizesDto> filterMovieProducers(List<MovieProducer> mpList, String type) {
		var ref = new Object() {
			Integer minMaxInterval = type.equals("max") ? Integer.MIN_VALUE : Integer.MAX_VALUE;
		};

		List<ProducerPrizesDto> list = new ArrayList<>();
		mpList.forEach(actual -> mpList.forEach(next -> {
			if (!actual.equals(next)) { // skips if actual and next are the same element
				if (actual.getProducer().equals(next.getProducer())) {
					Integer interval = Math.abs(actual.getMovie().getYear() - next.getMovie().getYear());

					if ((type.equals("min") && interval < ref.minMaxInterval)
							|| (type.equals("max") && interval > ref.minMaxInterval)) {
						ProducerPrizesDto dto = new ProducerPrizesDto(actual.getProducer().getName(), interval,
								actual.getMovie().getYear(), next.getMovie().getYear());
						if (!list.contains(dto))
							list.add(dto);
					}
				}
			}
		}));

		// get the real min/max interval
		list.forEach(item -> {
			if (type.equals("min") && item.getInterval() < ref.minMaxInterval
					|| (type.equals("max") && item.getInterval() > ref.minMaxInterval)) {
				ref.minMaxInterval = item.getInterval();
			}
		});


		// Removes duplicated values in the list
		List<ProducerPrizesDto> listWithoutDuplicatedItems = list.stream()
				.filter(item -> item.getInterval() == ref.minMaxInterval)
				.collect(Collectors.toList());

		return listWithoutDuplicatedItems;
	}

}
