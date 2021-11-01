package br.com.balneariosoftware.repository;

import br.com.balneariosoftware.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    @Query("SELECT f FROM Funcionario f where f.usuarioId = ?1")
    Funcionario searchFunciorioByUserId(Long id);

    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM Funcionario f where f.usuarioId = ?1")
    Boolean funcionarioExistsByUserId(Long id);
}
