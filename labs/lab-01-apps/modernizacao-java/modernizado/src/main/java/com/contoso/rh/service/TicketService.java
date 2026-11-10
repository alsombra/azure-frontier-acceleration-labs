package com.contoso.rh.service;

import com.contoso.rh.entity.Ticket;
import com.contoso.rh.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Service
public class TicketService {

    private final TicketRepository repo;
    private final Clock clock;

    @Autowired
    public TicketService(TicketRepository repo) {
        this(repo, Clock.systemUTC());
    }

    TicketService(TicketRepository repo, Clock clock) {
        this.repo = repo;
        this.clock = clock;
    }

    public Ticket abrir(Long funcId, String assunto, String descricao) {
        Ticket t = new Ticket();
        t.setFuncionarioId(funcId);
        t.setAssunto(assunto);
        t.setDescricao(descricao);
        t.setStatus("ABERTO");
        t.setAbertoEm(Date.from(clock.instant()));
        return repo.save(t);
    }

    public List<Ticket> abertosRecentes(Long funcId, int diasAtras) {
        Date desde = Date.from(clock.instant().minus(diasAtras, ChronoUnit.DAYS));
        return repo.abertosRecentes(funcId, desde);
    }
}
