package com.outsera.outsera_movie_challenge.repository;

import com.outsera.outsera_movie_challenge.dto.StudioWinDto;
import com.outsera.outsera_movie_challenge.entity.Studio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudioRepository extends JpaRepository<Studio, Long> {

	Studio findByName(String name);

	@Query(value="select new com.outsera.outsera_movie_challenge.dto.StudioWinDto(studio.name, count(movie.winner)) "
			+ "from MovieStudio as ms join ms.movie as movie join ms.studio as studio "
			+ "where movie.winner=true group by studio.name order by 2 desc")
	List<StudioWinDto> findByWinners();

}
