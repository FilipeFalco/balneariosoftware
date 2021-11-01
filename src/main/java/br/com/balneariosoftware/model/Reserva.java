package br.com.balneariosoftware.model;

import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

import static javax.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reserva")
public class Reserva {

    @Id
    @SequenceGenerator(
            name = "reserva_sequence",
            sequenceName = "reserva_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "reserva_sequence"
    )
    private Long id;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(nullable = false, columnDefinition = "DATE")
    private Date data_reservada;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date created_at = new Timestamp((new Date()).getTime());

    private Date update_at;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String local;

    @Column(unique = true, nullable = false, columnDefinition = "TEXT")
    private Long autorizadorId;

    @Column(unique = true, nullable = false, columnDefinition = "TEXT")
    private Long solicitanteId;

    @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean ativo = Boolean.TRUE;
}
