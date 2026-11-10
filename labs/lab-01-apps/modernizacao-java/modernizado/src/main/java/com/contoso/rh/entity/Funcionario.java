package com.contoso.rh.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "funcionario")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_funcionario")
    @SequenceGenerator(name = "seq_funcionario", sequenceName = "seq_funcionario", allocationSize = 1)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String matricula;

    @Column(nullable = false, length = 120)
    private String nome;

    @Column(nullable = false, length = 14)
    private String cpf;

    @Column(length = 80)
    private String cargo;

    private Double salario;

    @Temporal(TemporalType.DATE)
    private Date admissao;

    @Column(length = 1)
    private String ativo = "S";

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }
    public Double getSalario() { return salario; }
    public void setSalario(Double salario) { this.salario = salario; }
    public Date getAdmissao() { return admissao; }
    public void setAdmissao(Date admissao) { this.admissao = admissao; }
    public String getAtivo() { return ativo; }
    public void setAtivo(String ativo) { this.ativo = ativo; }
}
