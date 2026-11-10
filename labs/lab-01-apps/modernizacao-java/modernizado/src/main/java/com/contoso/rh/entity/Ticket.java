package com.contoso.rh.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_ticket")
    @SequenceGenerator(name = "seq_ticket", sequenceName = "seq_ticket", allocationSize = 1)
    private Long id;

    @Column(name = "funcionario_id", nullable = false)
    private Long funcionarioId;

    @Column(length = 200)
    private String assunto;

    @Lob
    private String descricao;

    @Column(length = 20)
    private String status = "ABERTO";

    @Column(name = "aberto_em")
    @Temporal(TemporalType.TIMESTAMP)
    private Date abertoEm;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getFuncionarioId() { return funcionarioId; }
    public void setFuncionarioId(Long funcionarioId) { this.funcionarioId = funcionarioId; }
    public String getAssunto() { return assunto; }
    public void setAssunto(String assunto) { this.assunto = assunto; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Date getAbertoEm() { return abertoEm; }
    public void setAbertoEm(Date abertoEm) { this.abertoEm = abertoEm; }
}
