package com.contoso.rh.repository;

import com.contoso.rh.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    // Oracle ROWNUM
    @Query(value = "SELECT * FROM ( " +
                   "  SELECT f.* FROM funcionario f " +
                   "  WHERE f.ativo = 'S' " +
                   "  ORDER BY f.nome " +
                   ") WHERE ROWNUM <= :limite",
           nativeQuery = true)
    List<Funcionario> buscarAtivos(@Param("limite") int limite);

    Funcionario findByMatricula(String matricula);
}
