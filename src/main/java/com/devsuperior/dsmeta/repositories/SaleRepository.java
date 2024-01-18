package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.ReportDTO;
import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Query("SELECT new com.devsuperior.dsmeta.dto.ReportDTO(obj.id, obj.date, SUM(obj.amount), obj.seller.name) " +
            "FROM Sale obj " +
            "WHERE obj.date BETWEEN :minDate AND :maxDate " +
            "AND UPPER(obj.seller.name) LIKE UPPER(CONCAT('%',:name,'%')) " +
            "GROUP BY obj.seller.name ")
    Page<ReportDTO> searchByName(String minDate, String maxDate, String name, Pageable pageable);
}
