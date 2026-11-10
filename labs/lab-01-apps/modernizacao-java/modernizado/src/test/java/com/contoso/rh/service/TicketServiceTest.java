package com.contoso.rh.service;

import com.contoso.rh.entity.Ticket;
import com.contoso.rh.repository.TicketRepository;
import org.junit.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TicketServiceTest {
    private final TicketRepository repository = mock(TicketRepository.class);
    private final Clock clock = Clock.fixed(Instant.parse("2027-02-01T00:00:00Z"), ZoneOffset.UTC);
    private final TicketService service = new TicketService(repository, clock);

    @Test
    public void recentes_bindsThirtyDayCutoff() {
        service.abertosRecentes(1L, 30);
        verify(repository).abertosRecentes(1L, Date.from(Instant.parse("2027-01-02T00:00:00Z")));
    }

    @Test
    public void recentes_preservesZeroAndNegativeDaySemantics() {
        service.abertosRecentes(1L, 0);
        service.abertosRecentes(1L, -1);
        verify(repository).abertosRecentes(1L, Date.from(clock.instant()));
        verify(repository).abertosRecentes(1L, Date.from(Instant.parse("2027-02-02T00:00:00Z")));
    }

    @Test
    public void abrir_usesClockAndPreservesSyntheticFields() {
        when(repository.save(any(Ticket.class))).thenAnswer(invocation -> invocation.getArgument(0));
        Ticket ticket = service.abrir(1L, "Synthetic", "Local only");
        assertEquals(Date.from(clock.instant()), ticket.getAbertoEm());
        assertEquals(Long.valueOf(1L), ticket.getFuncionarioId());
        assertEquals("ABERTO", ticket.getStatus());
        assertEquals("Synthetic", ticket.getAssunto());
        assertEquals("Local only", ticket.getDescricao());
    }
}