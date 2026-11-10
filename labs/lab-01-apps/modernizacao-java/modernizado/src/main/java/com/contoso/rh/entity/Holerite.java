package com.contoso.rh.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "holerite")
@NamedNativeQueries({
    @NamedNativeQuery(
        name = "Holerite.calcularLiquidoPkg",
        query = "SELECT pkg_holerite.calcular_liquido(:bruto, :descontos) AS liquido FROM dual"
    )
})
public class Holerite {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_holerite")
    @SequenceGenerator(name = "seq_holerite", sequenceName = "seq_holerite", allocationSize = 1)
    private Long id;

    @Column(name = "funcionario_id", nullable = false)
    private Long funcionarioId;

    @Column(nullable = false, length = 7)
    private String competencia;

    private BigDecimal bruto;
    private BigDecimal descontos;
    private BigDecimal liquido;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "emitido_em")
    private Date emitidoEm;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getFuncionarioId() { return funcionarioId; }
    public void setFuncionarioId(Long funcionarioId) { this.funcionarioId = funcionarioId; }
    public String getCompetencia() { return competencia; }
    public void setCompetencia(String competencia) { this.competencia = competencia; }
    public BigDecimal getBruto() { return bruto; }
    public void setBruto(BigDecimal bruto) { this.bruto = bruto; }
    public BigDecimal getDescontos() { return descontos; }
    public void setDescontos(BigDecimal descontos) { this.descontos = descontos; }
    public BigDecimal getLiquido() { return liquido; }
    public void setLiquido(BigDecimal liquido) { this.liquido = liquido; }
    public Date getEmitidoEm() { return emitidoEm; }
    public void setEmitidoEm(Date emitidoEm) { this.emitidoEm = emitidoEm; }
}
