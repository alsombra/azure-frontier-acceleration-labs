package com.contoso.rh.controller;

import com.contoso.rh.entity.Holerite;
import com.contoso.rh.service.HoleriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/holerites")
public class HoleriteController {

    @Autowired
    private HoleriteService service;

    @PostMapping
    public Holerite emitir(@RequestParam Long funcId,
                           @RequestParam String competencia,
                           @RequestParam BigDecimal bruto,
                           @RequestParam BigDecimal descontos) {
        return service.emitir(funcId, competencia, bruto, descontos);
    }

    @GetMapping("/{funcId}/{competencia}")
    public List<Holerite> porCompetencia(@PathVariable Long funcId,
                                         @PathVariable String competencia) {
        return service.porCompetencia(funcId, competencia);
    }
}
