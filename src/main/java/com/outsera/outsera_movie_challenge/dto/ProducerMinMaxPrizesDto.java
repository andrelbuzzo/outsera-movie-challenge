package com.outsera.outsera_movie_challenge.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProducerMinMaxPrizesDto {

	private List<ProducerPrizesDto> min = new ArrayList<>();

	private List<ProducerPrizesDto> max = new ArrayList<>();

	public ProducerMinMaxPrizesDto(LinkedList<ProducerPrizesDto> list) {
		this.min.add(list.getFirst());
		this.max.add(list.getLast());
	}

	public void addMin(List<ProducerPrizesDto> min) {
		this.getMin().addAll(min);
	}

	public void addMax(List<ProducerPrizesDto> max) {
		this.getMax().addAll(max);
	}

}
