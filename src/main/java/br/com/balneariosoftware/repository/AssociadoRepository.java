package br.com.balneariosoftware.repository;

import br.com.balneariosoftware.model.Associado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociadoRepository extends JpaRepository<Associado, Long> {

    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Associado a WHERE a.usuarioId = ?1")
    Boolean userJaAssociado(Long id);

    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM Funcionario f where f.usuarioId = ?1")
    Boolean associadoEFuncionario(Long id);
}
