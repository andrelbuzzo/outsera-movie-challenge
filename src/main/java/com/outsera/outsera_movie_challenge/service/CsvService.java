package com.outsera.outsera_movie_challenge.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

@Service
@RequiredArgsConstructor
public class CsvService {

	final public Iterable<CSVRecord> newParser(InputStream in) throws IOException {
		Reader reader = new InputStreamReader(in);
		Iterable<CSVRecord> records = CSVFormat.EXCEL
				.withSkipHeaderRecord()
				.withDelimiter(';')
				.withHeader("year", "title", "studios", "producers", "winner" )
				.parse(reader);

		return records;
	}

}
