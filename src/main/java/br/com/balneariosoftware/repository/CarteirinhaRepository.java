package br.com.balneariosoftware.repository;

import br.com.balneariosoftware.model.Carteirinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarteirinhaRepository extends JpaRepository<Carteirinha, Long> {


}
