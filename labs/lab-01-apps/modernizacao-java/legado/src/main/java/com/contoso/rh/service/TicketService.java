package com.contoso.rh.service;

import com.contoso.rh.entity.Ticket;
import com.contoso.rh.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository repo;

    public Ticket abrir(Long funcId, String assunto, String descricao) {
        Ticket t = new Ticket();
        t.setFuncionarioId(funcId);
        t.setAssunto(assunto);
        t.setDescricao(descricao);
        t.setStatus("ABERTO");
        t.setAbertoEm(new Date());
        return repo.save(t);
    }

    public List<Ticket> abertosRecentes(Long funcId, int diasAtras) {
        return repo.abertosRecentes(funcId, diasAtras);
    }
}
