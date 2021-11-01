package br.com.balneariosoftware.repository;

import br.com.balneariosoftware.model.Carteirinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CarteirinhaRepository extends JpaRepository<Carteirinha, Long> {

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Carteirinha c WHERE c.usuarioId = ?1")
    Boolean associadoJaPossuiCarteirinha(Long id);

    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM Funcionario f where f.usuarioId = ?1")
    Boolean userEFuncionario(Long id);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Carteirinha c where c.id_carteirinha = ?1")
    Boolean validaIdCarteirinha(Long id);
}
