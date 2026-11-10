package com.contoso.rh.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ferias")
@NamedNativeQueries({
    @NamedNativeQuery(
        name = "Ferias.findUltimasPorFuncionario",
        query = "SELECT * FROM ( " +
                "  SELECT f.* FROM ferias f " +
                "  WHERE f.funcionario_id = :funcId " +
                "  ORDER BY f.data_inicio DESC " +
                ") WHERE ROWNUM <= :limite",
        resultClass = Ferias.class
    )
})
public class Ferias {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_ferias")
    @SequenceGenerator(name = "seq_ferias", sequenceName = "seq_ferias", allocationSize = 1)
    private Long id;

    @Column(name = "funcionario_id", nullable = false)
    private Long funcionarioId;

    @Column(name = "data_inicio", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataInicio;

    @Column(name = "data_fim", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataFim;

    @Column(nullable = false)
    private Integer dias;

    @Column(length = 20)
    private String status = "SOLICITADA";

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getFuncionarioId() { return funcionarioId; }
    public void setFuncionarioId(Long funcionarioId) { this.funcionarioId = funcionarioId; }
    public Date getDataInicio() { return dataInicio; }
    public void setDataInicio(Date dataInicio) { this.dataInicio = dataInicio; }
    public Date getDataFim() { return dataFim; }
    public void setDataFim(Date dataFim) { this.dataFim = dataFim; }
    public Integer getDias() { return dias; }
    public void setDias(Integer dias) { this.dias = dias; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
