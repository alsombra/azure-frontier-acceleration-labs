package com.contoso.rh.repository;

import com.contoso.rh.entity.Beneficio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BeneficioRepository extends JpaRepository<Beneficio, Long> {

    // Oracle MERGE INTO (upsert)
    @Modifying
    @Transactional
    @Query(value = "MERGE INTO beneficio b " +
                   "USING (SELECT :funcId AS funcionario_id, :tipo AS tipo FROM dual) src " +
                   "ON (b.funcionario_id = src.funcionario_id AND b.tipo = src.tipo) " +
                   "WHEN MATCHED THEN UPDATE SET b.valor = :valor, b.ativo = 'S' " +
                   "WHEN NOT MATCHED THEN INSERT (id, funcionario_id, tipo, valor, ativo) " +
                   "  VALUES (seq_beneficio.NEXTVAL, :funcId, :tipo, :valor, 'S')",
           nativeQuery = true)
    int upsertBeneficio(@Param("funcId") Long funcId,
                        @Param("tipo") String tipo,
                        @Param("valor") java.math.BigDecimal valor);

    List<Beneficio> findByFuncionarioIdAndAtivo(Long funcionarioId, String ativo);
}
