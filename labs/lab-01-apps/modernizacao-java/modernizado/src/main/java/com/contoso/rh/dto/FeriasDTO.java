package com.contoso.rh.dto;

import java.util.Date;

public class FeriasDTO {
    private Long funcionarioId;
    private Date dataInicio;
    private Date dataFim;
    private Integer dias;
    private String status;

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
