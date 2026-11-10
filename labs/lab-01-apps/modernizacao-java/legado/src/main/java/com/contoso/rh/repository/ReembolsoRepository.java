package com.contoso.rh.repository;

import com.contoso.rh.entity.Reembolso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReembolsoRepository extends JpaRepository<Reembolso, Long> {

    // Oracle NVL + ROWNUM
    @Query(value = "SELECT * FROM ( " +
                   "  SELECT r.* FROM reembolso r " +
                   "  WHERE r.funcionario_id = :funcId " +
                   "    AND r.status = NVL(:status, r.status) " +
                   "  ORDER BY r.solicitado_em DESC " +
                   ") WHERE ROWNUM <= 50",
           nativeQuery = true)
    List<Reembolso> ultimosReembolsos(@Param("funcId") Long funcId,
                                      @Param("status") String status);
}
