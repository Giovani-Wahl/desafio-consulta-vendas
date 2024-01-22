package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.ReportDTO;
import com.devsuperior.dsmeta.dto.SummaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<Page<ReportDTO>> getReport(
			@RequestParam(defaultValue = "") String minDate,@RequestParam(defaultValue = "") String maxDate,
			@RequestParam(name = "name",defaultValue ="") String name,
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
		Page<ReportDTO> dto =service.getReport(minDate,maxDate,name, pageable);
		return ResponseEntity.ok(dto);
	}
	@GetMapping(value = "/summary")
	public ResponseEntity<Page<SummaryDTO>> getSummary(
			@RequestParam(defaultValue = "") String minDate,@RequestParam(defaultValue = "") String maxDate,
			@PageableDefault(page = 0, size = 10, sort = "seller.name", direction = Sort.Direction.ASC) Pageable pageable) {
		Page<SummaryDTO> dto =service.getSummary(minDate,maxDate,pageable);
		return ResponseEntity.ok(dto);
	}
}
