package com.contoso.rh.controller;

import com.contoso.rh.entity.Reembolso;
import com.contoso.rh.service.ReembolsoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/reembolsos")
public class ReembolsoController {

    @Autowired
    private ReembolsoService service;

    @PostMapping
    public Reembolso solicitar(@RequestParam Long funcId,
                               @RequestParam String categoria,
                               @RequestParam BigDecimal valor) {
        return service.solicitar(funcId, categoria, valor);
    }

    @GetMapping("/funcionario/{funcId}")
    public List<Reembolso> ultimos(@PathVariable Long funcId,
                                   @RequestParam(required = false) String status) {
        return service.ultimos(funcId, status);
    }
}
