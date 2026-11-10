package com.contoso.rh.service;

import com.contoso.rh.entity.Holerite;
import com.contoso.rh.repository.HoleriteRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HoleriteServiceTest {

    @Mock
    private HoleriteRepository repo;

    @InjectMocks
    private HoleriteService service;

    @Test
    public void calcularLiquido_subtraiDescontos() {
        BigDecimal liq = service.calcularLiquido(new BigDecimal("5000.00"), new BigDecimal("1200.00"));
        assertEquals(new BigDecimal("3800.00"), liq);
    }

    @Test
    public void emitir_persisteHoleriteComLiquidoCalculado() {
        when(repo.save(any(Holerite.class))).thenAnswer(inv -> inv.getArguments()[0]);

        Holerite h = service.emitir(1L, "2024-01", new BigDecimal("1000"), new BigDecimal("300"));

        assertNotNull(h);
        assertEquals(new BigDecimal("700"), h.getLiquido());
        assertEquals("2024-01", h.getCompetencia());
    }
}
