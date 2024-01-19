package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.ReportDTO;
import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Query("SELECT new com.devsuperior.dsmeta.dto.ReportDTO(obj.id, obj.date, obj.amount, obj.seller.name) " +
            "FROM Sale obj " +
            "WHERE obj.date BETWEEN :parsedMinDate AND :parsedMaxDate " +
            "AND UPPER(obj.seller.name) LIKE UPPER(CONCAT('%',:name,'%')) " +
            "GROUP BY obj.seller.name, obj.date")
    Page<ReportDTO> searchByName(LocalDate parsedMinDate, LocalDate parsedMaxDate, String name, Pageable pageable);
}
