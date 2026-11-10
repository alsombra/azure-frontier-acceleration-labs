package com.contoso.rh.repository;

import com.contoso.rh.entity.Ferias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FeriasRepository extends JpaRepository<Ferias, Long> {

    // Oracle ROWNUM + NVL + SYSDATE
    @Query(value = "SELECT * FROM ( " +
                   "  SELECT f.* FROM ferias f " +
                   "  WHERE f.funcionario_id = :funcId " +
                   "    AND f.data_inicio <= NVL(:ate, SYSDATE) " +
                   "  ORDER BY f.data_inicio DESC " +
                   ") WHERE ROWNUM <= :limite",
           nativeQuery = true)
    List<Ferias> ultimasFerias(@Param("funcId") Long funcId,
                               @Param("ate") java.util.Date ate,
                               @Param("limite") int limite);

    List<Ferias> findByFuncionarioId(Long funcionarioId);
}
