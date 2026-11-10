package com.contoso.rh.service;

import com.contoso.rh.entity.Ferias;
import com.contoso.rh.repository.FeriasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class FeriasService {

    @Autowired
    private FeriasRepository repo;

    public Ferias solicitar(Long funcId, Date inicio, int dias) {
        Ferias f = new Ferias();
        f.setFuncionarioId(funcId);
        f.setDataInicio(inicio);
        f.setDias(dias);

        // calcula dataFim
        Calendar c = Calendar.getInstance();
        c.setTime(inicio);
        c.add(Calendar.DAY_OF_MONTH, dias);
        f.setDataFim(c.getTime());

        try {
            return repo.save(f);
        } catch (Exception e) {
            // swallow proposital (mau exemplo)
            e.printStackTrace();
            return null;
        }
    }

    public List<Ferias> ultimas(Long funcId, int limite) {
        return repo.ultimasFerias(funcId, new Date(), limite);
    }

    public List<Ferias> porFuncionario(Long funcId) {
        return repo.findByFuncionarioId(funcId);
    }
}
