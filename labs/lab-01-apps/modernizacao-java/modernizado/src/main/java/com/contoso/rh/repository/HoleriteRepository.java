package com.contoso.rh.repository;

import com.contoso.rh.entity.Holerite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HoleriteRepository extends JpaRepository<Holerite, Long> {

    // Oracle DECODE + NVL
    @Query(value = "SELECT h.* FROM holerite h " +
                   "WHERE h.funcionario_id = :funcId " +
                   "  AND DECODE(h.competencia, :competencia, 1, 0) = 1 " +
                   "  AND NVL(h.liquido, 0) > 0",
           nativeQuery = true)
    List<Holerite> buscarPorCompetencia(@Param("funcId") Long funcId,
                                        @Param("competencia") String competencia);

    // Chama package PL/SQL (Oracle)
    @Query(value = "SELECT pkg_holerite.calcular_liquido(:bruto, :descontos) FROM dual",
           nativeQuery = true)
    java.math.BigDecimal calcularLiquidoPkg(@Param("bruto") java.math.BigDecimal bruto,
                                            @Param("descontos") java.math.BigDecimal descontos);

    List<Holerite> findByFuncionarioId(Long funcionarioId);
}
