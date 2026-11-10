package com.contoso.rh.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "reembolso")
public class Reembolso {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_reembolso")
    @SequenceGenerator(name = "seq_reembolso", sequenceName = "seq_reembolso", allocationSize = 1)
    private Long id;

    @Column(name = "funcionario_id", nullable = false)
    private Long funcionarioId;

    @Column(length = 40)
    private String categoria;

    private BigDecimal valor;

    @Column(length = 20)
    private String status = "PENDENTE";

    @Column(name = "solicitado_em")
    @Temporal(TemporalType.TIMESTAMP)
    private Date solicitadoEm;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getFuncionarioId() { return funcionarioId; }
    public void setFuncionarioId(Long funcionarioId) { this.funcionarioId = funcionarioId; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Date getSolicitadoEm() { return solicitadoEm; }
    public void setSolicitadoEm(Date solicitadoEm) { this.solicitadoEm = solicitadoEm; }
}
