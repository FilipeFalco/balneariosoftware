package br.com.balneariosoftware.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
@Table(name = "associado")
public class Associado {

    @Id
    @SequenceGenerator(
            name = "associado_sequence",
            sequenceName = "associado_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "associado_sequence"
    )
    private Long id;

    @Column(unique = true, nullable = false, columnDefinition = "BIGINT")
    private Long usuarioId;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date created_at = new Timestamp((new Date()).getTime());

    private Date update_at;
}
