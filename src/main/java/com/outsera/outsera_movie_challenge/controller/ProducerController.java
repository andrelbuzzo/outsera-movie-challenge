package com.outsera.outsera_movie_challenge.controller;

import com.outsera.outsera_movie_challenge.dto.ProducerMinMaxPrizesDto;
import com.outsera.outsera_movie_challenge.service.ProducerService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/producer" )
public class ProducerController {

	private final ProducerService producerService;

	@GetMapping("interval-prizes" )
	@Operation(summary = "Get winner producers with min and max interval between prizes")
	public ResponseEntity<ProducerMinMaxPrizesDto> getMaxAndMinPrizes() {
		ProducerMinMaxPrizesDto dto = producerService.getMaxAndMinPrizes();

		HttpStatus status = HttpStatus.OK;
		if (dto.getMax().isEmpty() && dto.getMin().isEmpty()) {
			status = HttpStatus.NO_CONTENT;
		}

		return new ResponseEntity<>(dto, status);
	}

}
