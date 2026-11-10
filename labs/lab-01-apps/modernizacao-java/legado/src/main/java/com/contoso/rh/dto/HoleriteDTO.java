package com.contoso.rh.dto;

import java.math.BigDecimal;

public class HoleriteDTO {
    private Long funcionarioId;
    private String competencia;
    private BigDecimal bruto;
    private BigDecimal descontos;
    private BigDecimal liquido;

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
}
