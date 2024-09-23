package com.outsera.outsera_movie_challenge.controller;

import com.outsera.outsera_movie_challenge.dto.StudioDto;
import com.outsera.outsera_movie_challenge.service.StudioService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/studio" )
public class StudioController {

	private final StudioService studioService;

	@GetMapping("/winners" )
	@Operation(summary = "Get studios winners ordered by win count")
	public ResponseEntity<StudioDto> getGreatestWinners() {
		StudioDto dto = studioService.getGreatestWinners();

		HttpStatus status = HttpStatus.OK;
		if (Objects.isNull(dto.studios()) || dto.studios().isEmpty()) {
			status = HttpStatus.NO_CONTENT;
		}

		return new ResponseEntity<>(dto, status);
	}

}
