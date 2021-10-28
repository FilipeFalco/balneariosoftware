package br.com.balneariosoftware.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private Date data_reservada;

    private Date created_at;

    private Date update_at;

    @NotNull
    private String updated_by;

    @NotNull
    private Long autorizadorId;

    @NotNull
    private Long solicitanteId;

    private Boolean ativo;
}
