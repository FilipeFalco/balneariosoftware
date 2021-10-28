package br.com.balneariosoftware.repository;

import br.com.balneariosoftware.model.Funcionario;
import br.com.balneariosoftware.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    @Query("SELECT u FROM User u WHERE u.id = :id")
    public User findFuncionarioUser(@Param("id") Long id);
}
