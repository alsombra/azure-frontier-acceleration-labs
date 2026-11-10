package com.contoso.rh.controller;

import com.contoso.rh.entity.Ticket;
import com.contoso.rh.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService service;

    @PostMapping
    public Ticket abrir(@RequestParam Long funcId,
                        @RequestParam String assunto,
                        @RequestParam String descricao) {
        return service.abrir(funcId, assunto, descricao);
    }

    @GetMapping("/funcionario/{funcId}")
    public List<Ticket> recentes(@PathVariable Long funcId,
                                 @RequestParam(defaultValue = "30") int diasAtras) {
        return service.abertosRecentes(funcId, diasAtras);
    }
}
