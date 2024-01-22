package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.ReportDTO;
import com.devsuperior.dsmeta.dto.SummaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;

	@Transactional(readOnly = true)
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	@Transactional(readOnly = true)
	public Page<ReportDTO> getReport(String minDate, String maxDate, String name, Pageable pageable){
		LocalDate parsedMinDate;
		LocalDate parsedMaxDate;
		if (minDate.isEmpty()){
			parsedMinDate = LocalDate.now().minusYears(1L);
		}else {
			parsedMinDate = LocalDate.parse(minDate, DateTimeFormatter.ISO_LOCAL_DATE);
		}

		if (maxDate.isEmpty()){
			parsedMaxDate = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		}else {
			parsedMaxDate = LocalDate.parse(maxDate, DateTimeFormatter.ISO_LOCAL_DATE);
		}

		Page<ReportDTO> result = repository.searchByName(parsedMinDate,parsedMaxDate,name, pageable);
		return result;
	}

	@Transactional(readOnly = true)
	public Page<SummaryDTO> getSummary(String minDate, String maxDate, Pageable pageable){
		LocalDate parsedMinDate;
		LocalDate parsedMaxDate;
		if (minDate.isEmpty()){
			parsedMinDate = LocalDate.now().minusYears(1L);
		}else {
			parsedMinDate = LocalDate.parse(minDate, DateTimeFormatter.ISO_LOCAL_DATE);
		}

		if (maxDate.isEmpty()){
			parsedMaxDate = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		}else {
			parsedMaxDate = LocalDate.parse(maxDate, DateTimeFormatter.ISO_LOCAL_DATE);
		}
		Page<SummaryDTO> result = repository.searchSummary(parsedMinDate,parsedMaxDate, pageable);
		return result;
	}
}
