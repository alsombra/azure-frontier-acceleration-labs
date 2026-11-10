package com.contoso.rh.service;

import com.contoso.rh.entity.Ferias;
import com.contoso.rh.repository.FeriasRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FeriasServiceTest {

    @Mock
    private FeriasRepository repo;

    @InjectMocks
    private FeriasService service;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void solicitar_calculaDataFim() {
        when(repo.save(any(Ferias.class))).thenAnswer(inv -> inv.getArguments()[0]);

        Date hoje = new Date();
        Ferias f = service.solicitar(10L, hoje, 15);

        assertNotNull(f);
        Calendar c = Calendar.getInstance();
        c.setTime(hoje);
        c.add(Calendar.DAY_OF_MONTH, 15);
        assertEquals(c.getTime(), f.getDataFim());
        assertEquals(Integer.valueOf(15), f.getDias());
    }

    @Test
    public void ultimas_chamaRepositorioComLimite() {
        when(repo.ultimasFerias(anyLong(), any(Date.class), anyInt()))
                .thenReturn(Collections.singletonList(new Ferias()));

        List<Ferias> result = service.ultimas(7L, 3);

        assertEquals(1, result.size());
    }

    @Test
    public void porFuncionario_retornaLista() {
        when(repo.findByFuncionarioId(5L))
                .thenReturn(Arrays.asList(new Ferias(), new Ferias()));

        List<Ferias> result = service.porFuncionario(5L);

        assertEquals(2, result.size());
    }
}
