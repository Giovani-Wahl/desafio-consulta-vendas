package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Sale;

import java.time.LocalDate;

public class ReportDTO {
    private Long id;
    private LocalDate date;
    private Double amount;


    public ReportDTO(Long id, LocalDate date, Double amount ) {
        this.id = id;
        this.date = date;
        this.amount = amount;

    }
    public ReportDTO(Sale entity) {
        id = entity.getId();
        date = entity.getDate();
        amount = entity.getAmount();

    }

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public Double getAmount() {
        return amount;
    }

}
