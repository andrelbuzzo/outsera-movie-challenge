package com.outsera.outsera_movie_challenge.service;

import com.outsera.outsera_movie_challenge.dto.StudioDto;
import com.outsera.outsera_movie_challenge.entity.Movie;
import com.outsera.outsera_movie_challenge.entity.MovieStudio;
import com.outsera.outsera_movie_challenge.entity.Studio;
import com.outsera.outsera_movie_challenge.repository.MovieStudioRepository;
import com.outsera.outsera_movie_challenge.repository.StudioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class StudioService {

	private final StudioRepository studioRepository;
	private final MovieStudioRepository movieStudioRepository;

	public void saveStudios(Movie movie, String studios) {
		for (String strStudio : studios.split(",|\\ and " )) {
			Studio studio = new Studio(strStudio.trim());

			Example<Studio> example = Example.of(studio);

			if (studioRepository.exists(example)) {
				studio = studioRepository.findByName(strStudio.trim());
			} else {
				studio = studioRepository.save(studio);
			}


			movieStudioRepository.save(new MovieStudio(movie, studio));
		}
	}

	public StudioDto getGreatestWinners() {
		return new StudioDto(studioRepository.findByWinners());
	}

}
