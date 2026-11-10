package com.contoso.rh.service;

import com.contoso.rh.entity.Holerite;
import com.contoso.rh.repository.HoleriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class HoleriteService {

    @Autowired
    private HoleriteRepository repo;

    public Holerite emitir(Long funcId, String competencia, BigDecimal bruto, BigDecimal descontos) {
        Holerite h = new Holerite();
        h.setFuncionarioId(funcId);
        h.setCompetencia(competencia);
        h.setBruto(bruto);
        h.setDescontos(descontos);
        h.setLiquido(calcularLiquido(bruto, descontos));
        h.setEmitidoEm(new Date());

        try {
            return repo.save(h);
        } catch (Exception e) {
            // swallow proposital
            e.printStackTrace();
            return null;
        }
    }

    public BigDecimal calcularLiquido(BigDecimal bruto, BigDecimal descontos) {
        BigDecimal b = bruto == null ? BigDecimal.ZERO : bruto;
        BigDecimal d = descontos == null ? BigDecimal.ZERO : descontos;
        return b.subtract(d);
    }

    public List<Holerite> porCompetencia(Long funcId, String competencia) {
        return repo.buscarPorCompetencia(funcId, competencia);
    }
}
