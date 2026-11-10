package com.contoso.rh.service;

import com.contoso.rh.entity.Funcionario;
import com.contoso.rh.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repo;

    public Funcionario salvar(Funcionario f) {
        return repo.save(f);
    }

    public Funcionario porId(Long id) {
        return repo.findOne(id);
    }

    public List<Funcionario> ativos(int limite) {
        return repo.buscarAtivos(limite);
    }

    public Funcionario porMatricula(String matricula) {
        return repo.findByMatricula(matricula);
    }
}
