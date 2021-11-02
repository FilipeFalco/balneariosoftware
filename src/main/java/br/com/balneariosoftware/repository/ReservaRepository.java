package br.com.balneariosoftware.repository;

import br.com.balneariosoftware.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM Reserva r where r.local like ?1 and r.data_reservada = ?2")
    boolean localReservado(String local, Date data);

    @Query("SELECT r FROM Reserva r WHERE r.ativo = true")
    List<Reserva> listAllAtivo();
}
