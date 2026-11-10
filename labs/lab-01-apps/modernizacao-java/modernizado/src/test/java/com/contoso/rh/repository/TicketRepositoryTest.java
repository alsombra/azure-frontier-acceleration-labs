package com.contoso.rh.repository;

import com.contoso.rh.entity.Ticket;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = {
    "spring.datasource.url=jdbc:h2:mem:tickets_regression;MODE=Oracle;DB_CLOSE_DELAY=-1",
    "spring.datasource.driver-class-name=org.h2.Driver",
    "spring.datasource.username=sa",
    "spring.datasource.password=",
    "spring.jpa.hibernate.ddl-auto=create-drop",
    "spring.sql.init.mode=never",
    "spring.h2.console.enabled=false"
})
@Transactional
public class TicketRepositoryTest {
    @Autowired
    private TicketRepository repository;

    @Test
    public void recentes_filtersEmployeeAndDateWithoutNativeArithmetic() {
        save(1L, "2027-01-01T23:59:59Z");
        Ticket recent = save(1L, "2027-01-02T00:00:00Z");
        save(2L, "2027-01-03T00:00:00Z");

        List<Ticket> result = repository.abertosRecentes(1L, Date.from(Instant.parse("2027-01-02T00:00:00Z")));

        assertEquals(1, result.size());
        assertEquals(recent.getId(), result.get(0).getId());
    }

    private Ticket save(Long employeeId, String timestamp) {
        Ticket ticket = new Ticket();
        ticket.setFuncionarioId(employeeId);
        ticket.setAssunto("Synthetic regression");
        ticket.setAbertoEm(Date.from(Instant.parse(timestamp)));
        return repository.saveAndFlush(ticket);
    }
}