package com.contoso.rh.service;

import com.contoso.rh.entity.Beneficio;
import com.contoso.rh.repository.BeneficioRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BeneficioServiceTest {

    @Mock
    private BeneficioRepository repo;

    @InjectMocks
    private BeneficioService service;

    @Test
    public void upsert_delegaParaRepositorio() {
        when(repo.upsertBeneficio(1L, "VR", new BigDecimal("500"))).thenReturn(1);

        int rows = service.upsert(1L, "VR", new BigDecimal("500"));

        assertEquals(1, rows);
    }

    @Test
    public void ativos_filtraPorFlagS() {
        when(repo.findByFuncionarioIdAndAtivo(42L, "S"))
                .thenReturn(Collections.singletonList(new Beneficio()));

        List<Beneficio> result = service.ativos(42L);

        assertEquals(1, result.size());
    }
}
