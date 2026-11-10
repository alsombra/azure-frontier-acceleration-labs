package com.contoso.rh.controller;

import com.contoso.rh.entity.Beneficio;
import com.contoso.rh.service.BeneficioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/beneficios")
public class BeneficioController {

    @Autowired
    private BeneficioService service;

    @PostMapping
    public int upsert(@RequestParam Long funcId,
                      @RequestParam String tipo,
                      @RequestParam BigDecimal valor) {
        return service.upsert(funcId, tipo, valor);
    }

    @GetMapping("/funcionario/{funcId}")
    public List<Beneficio> ativos(@PathVariable Long funcId) {
        return service.ativos(funcId);
    }
}
