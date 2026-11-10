package com.contoso.rh.service;

import com.contoso.rh.entity.Beneficio;
import com.contoso.rh.repository.BeneficioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BeneficioService {

    @Autowired
    private BeneficioRepository repo;

    public int upsert(Long funcId, String tipo, BigDecimal valor) {
        try {
            return repo.upsertBeneficio(funcId, tipo, valor);
        } catch (Exception e) {
            // swallow proposital
            e.printStackTrace();
            return 0;
        }
    }

    public List<Beneficio> ativos(Long funcId) {
        return repo.findByFuncionarioIdAndAtivo(funcId, "S");
    }

    public Beneficio salvar(Beneficio b) {
        return repo.save(b);
    }
}
