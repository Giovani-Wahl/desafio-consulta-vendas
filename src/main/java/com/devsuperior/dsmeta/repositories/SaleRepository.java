package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.ReportDTO;
import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Query("SELECT new com.devsuperior.dsmeta.dto.ReportDTO(obj.id, obj.date, obj.amount) " +
            "FROM Sale obj " +
            "WHERE UPPER(obj.seller.name)" +
            "LIKE UPPER(CONCAT('%',:sellerName,'%')) ")
    Page<ReportDTO> searchByName(String sellerName, Pageable pageable);
}
