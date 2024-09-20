package com.outsera.outsera_movie_challenge.service;

import com.outsera.outsera_movie_challenge.dto.BaseResponse;
import com.outsera.outsera_movie_challenge.entity.Movie;
import com.outsera.outsera_movie_challenge.exception.InvalidIntervalTypeException;
import com.outsera.outsera_movie_challenge.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class MovieService {

	@Value("classpath:static/movielist.csv" )
	private Resource resource;

	final CsvService csvService;
	private final MovieRepository movieRepository;
	private final StudioService studioService;
	private final ProducerService producerService;

	public Long count() {
		return movieRepository.count();
	}

	public void saveFromCsv() throws IOException {
		Iterable<CSVRecord> records = null;
		try (InputStream inputStream = resource.getInputStream()) {
			records = parseCSV(inputStream);
//			log.info(records.size());

			records.forEach(record -> {
//				log.info(record);
				Movie movie = save(record);

				Optional.ofNullable(movie).ifPresent(m -> {
					String studios = record.get("studios" );
					studioService.saveStudios(m, studios);

					String producers = record.get("producers" );
					producerService.saveProducers(m, producers);
				});
			});
		} catch (IOException e) {
			log.error("IOException: {}", e.getMessage());
		} catch (Exception e) {
			log.error("General exception: {}", e.getMessage());
			e.printStackTrace();
		}
	}

	private Movie save(CSVRecord record) throws RuntimeException {
		Movie movie = new Movie(Integer.valueOf(record.get("year" )),
				record.get("title" ),
				record.get("winner" ));
//		log.info("movie:");
//		log.info(movie);
		try {
			movieRepository.save(movie);
		} catch (Exception e) {
			log.error("Error saving new Movie: {}", e.getMessage());
		} finally {
			return movie;
		}
	}

	private Iterable<CSVRecord> parseCSV(InputStream inputStream) throws IOException {
//		return csvService.parseCSV(inputStream, MovieDto.class);
		return csvService.newParser(inputStream);
	}

	public void populateMovies() throws IOException {
		try (InputStream inputStream = resource.getInputStream()) {
//			List<Movie> movies = parseCSV(inputStream);
//			movies.forEach(m -> {
//				log.info(m);
//			});
		}
	}

	public List<Movie> findAll() {
		return movieRepository.findAll();
	}

	public List<Movie> min(List<Movie> list) {
		return list;
	}

	public List<BaseResponse> getAwardInterval(String type) {
		List<Movie> list = findAll();
		List<Movie> filteredList = null;

		if (type.equals("min" )) {
			filteredList = getMinAwardInterval(list);
		} else if (type.equals("max" )) {
//			filteredList = getMaxAwardInterval(list);
		} else {
			throw new InvalidIntervalTypeException("Award Interval", type);
		}

		List<BaseResponse> response = null;

//		filteredList.forEach(r -> new BaseResponse(r.getProducers(), 99, 1900, 1999));

		return response;

		/*List<BaseResponse> response = new ArrayList<>() {{
			add(new BaseResponse("producer", 99, 1900, 1999));
			add(new BaseResponse("producer", 99, 2000, 2099));
		}};*/
	}

	public List<Movie> getMinAwardInterval(List<Movie> list) {
		log.info(list);
		List<Movie> winners = list.stream()
				.filter(Movie::isWinner)
				.collect(Collectors.toList());

		log.info(winners);

		return winners;
	}

//	public List<BaseResponse> getMaxAwardInterval(List<Movie> list) {
//		log.info(list);
//		return response;
//	}

}
