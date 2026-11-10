package com.contoso.rh.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "beneficio")
public class Beneficio {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_beneficio")
    @SequenceGenerator(name = "seq_beneficio", sequenceName = "seq_beneficio", allocationSize = 1)
    private Long id;

    @Column(name = "funcionario_id", nullable = false)
    private Long funcionarioId;

    @Column(nullable = false, length = 40)
    private String tipo;

    private BigDecimal valor;

    @Column(length = 1)
    private String ativo = "S";

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getFuncionarioId() { return funcionarioId; }
    public void setFuncionarioId(Long funcionarioId) { this.funcionarioId = funcionarioId; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }
    public String getAtivo() { return ativo; }
    public void setAtivo(String ativo) { this.ativo = ativo; }
}
