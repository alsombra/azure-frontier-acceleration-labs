package com.contoso.rh.service;

import com.contoso.rh.entity.Reembolso;
import com.contoso.rh.repository.ReembolsoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class ReembolsoService {

    @Autowired
    private ReembolsoRepository repo;

    public Reembolso solicitar(Long funcId, String categoria, BigDecimal valor) {
        Reembolso r = new Reembolso();
        r.setFuncionarioId(funcId);
        r.setCategoria(categoria);
        r.setValor(valor);
        r.setStatus("PENDENTE");
        r.setSolicitadoEm(new Date());
        return repo.save(r);
    }

    public List<Reembolso> ultimos(Long funcId, String status) {
        return repo.ultimosReembolsos(funcId, status);
    }
}
