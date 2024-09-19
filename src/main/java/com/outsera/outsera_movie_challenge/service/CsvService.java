package com.outsera.outsera_movie_challenge.service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.outsera.outsera_movie_challenge.util.CSVMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CsvService<T, K> {
	// the Mapper is injected to the service
	final CSVMapper<T, K> csvMapper;


	final public List<T> parseCSV(InputStream stream, Class<K> clazz) throws IOException {

		try (Reader reader = new BufferedReader(new InputStreamReader(stream))) {
			// creating the strategy object
			HeaderColumnNameMappingStrategy<K> strategy = new HeaderColumnNameMappingStrategy<>();
			// setting the format of the data representation in the header
			strategy.setType(clazz);
			// Creating instance of CSVTOBEAN class responsable of the mapping
			CsvToBean<K> csvToBean = new CsvToBeanBuilder<K>(reader)
					// Setting the strategy
					.withMappingStrategy(strategy)
					// Ignore empty lines and leading spaces
					.withIgnoreEmptyLine(true)
					.withIgnoreLeadingWhiteSpace(true)
					.withSkipLines(0)
					.withSeparator(';')
					.build();
			// parse the data into the Objects with type T
			return csvToBean.parse()
					.stream()
					.map(csvMapper::mapTo)
					.collect(Collectors.toList());
		}
	}
//  .... other functions to implement
}