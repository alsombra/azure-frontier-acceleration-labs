package com.contoso.rh.repository;

import com.contoso.rh.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    // Oracle SYSDATE
    @Query(value = "SELECT * FROM ticket t " +
                   "WHERE t.funcionario_id = :funcId " +
                   "  AND t.aberto_em >= (SYSDATE - :diasAtras)",
           nativeQuery = true)
    List<Ticket> abertosRecentes(@Param("funcId") Long funcId,
                                 @Param("diasAtras") int diasAtras);
}
