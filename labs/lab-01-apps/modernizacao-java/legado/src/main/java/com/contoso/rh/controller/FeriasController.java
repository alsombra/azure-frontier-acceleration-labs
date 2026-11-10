package com.contoso.rh.controller;

import com.contoso.rh.entity.Ferias;
import com.contoso.rh.service.FeriasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/ferias")
public class FeriasController {

    @Autowired
    private FeriasService service;

    @PostMapping
    public Ferias solicitar(@RequestParam Long funcId,
                            @RequestParam("inicio") long inicioMillis,
                            @RequestParam int dias) {
        return service.solicitar(funcId, new Date(inicioMillis), dias);
    }

    @GetMapping("/funcionario/{funcId}")
    public List<Ferias> porFuncionario(@PathVariable Long funcId) {
        return service.porFuncionario(funcId);
    }

    @GetMapping("/funcionario/{funcId}/ultimas")
    public List<Ferias> ultimas(@PathVariable Long funcId,
                                @RequestParam(defaultValue = "5") int limite) {
        return service.ultimas(funcId, limite);
    }
}
