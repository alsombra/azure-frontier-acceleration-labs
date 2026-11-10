package com.contoso.rh.controller;

import java.util.Date;
import jakarta.persistence.EntityManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.json.JsonMapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class FeriasJsonContractTest {
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private JsonMapper mapper;

    @Autowired
    private EntityManager entityManager;

    private MockMvc mvc;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void vacationPostAndBothGetsPreserveNumericDatesAndAllFields() throws Exception {
        String created = mvc.perform(post("/api/ferias")
                .param("funcId", "1").param("inicio", "1798761600000").param("dias", "10"))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        JsonNode expected = mapper.readTree("{\"id\":1,\"funcionarioId\":1,"
                + "\"dataInicio\":1798761600000,\"dataFim\":1799625600000,"
                + "\"dias\":10,\"status\":\"SOLICITADA\"}");
        assertRecord(expected, mapper.readTree(created));
        entityManager.flush();
        String body = mvc.perform(get("/api/ferias/funcionario/1")).andExpect(status().isOk())
            .andReturn().getResponse().getContentAsString();
        JsonNode records = mapper.readTree(body);
        assertTrue(records.isArray());
        assertEquals(1, records.size());
        assertRecord(expected, records.get(0));
        String latest = mvc.perform(get("/api/ferias/funcionario/1/ultimas?limite=5"))
            .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        int expectedLatestCount = new Date().before(new Date(1798761600000L)) ? 0 : 1;
        JsonNode latestRecords = mapper.readTree(latest);
        assertTrue(latestRecords.isArray());
        assertEquals(expectedLatestCount, latestRecords.size());
        if (expectedLatestCount == 1) {
            assertRecord(expected, latestRecords.get(0));
        }
    }

    @Test
    public void globalMapperWritesUtilAndSqlDatesAsEpochMilliseconds() {
        assertEquals("1798761600000", mapper.writeValueAsString(new Date(1798761600000L)));
        assertEquals("1799625600000", mapper.writeValueAsString(new java.sql.Date(1799625600000L)));
    }

    private void assertRecord(JsonNode expected, JsonNode actual) {
        assertTrue(actual.get("dataInicio").isIntegralNumber());
        assertTrue(actual.get("dataFim").isIntegralNumber());
        assertEquals(1798761600000L, actual.get("dataInicio").longValue());
        assertEquals(1799625600000L, actual.get("dataFim").longValue());
        assertEquals(expected, actual);
    }
}