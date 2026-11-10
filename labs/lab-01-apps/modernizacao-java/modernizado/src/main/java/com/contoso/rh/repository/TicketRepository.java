package com.contoso.rh.repository;

import com.contoso.rh.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

        @Query("SELECT ticket FROM Ticket ticket " +
            "WHERE ticket.funcionarioId = :funcId AND ticket.abertoEm >= :desde")
    List<Ticket> abertosRecentes(@Param("funcId") Long funcId,
                         @Param("desde") Date desde);
}
