package br.com.balneariosoftware.model;

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
@Table(name = "carteirinha")
public class Carteirinha {

    @Id
    @SequenceGenerator(
            name = "id_carteirinha_table",
            sequenceName = "id_carteirinha_table",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "id_carteirinha_table"
    )
    private Long id;

    @SequenceGenerator(
            name = "id_carteirinha",
            sequenceName = "id_carteirinha",
            allocationSize = 10000000
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "id_carteirinha"
    )
    private String id_carteirinha;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date created_at = new Timestamp((new Date()).getTime());

    private Date update_at;

    @Column(unique = true, nullable = false, columnDefinition = "BIGINT")
    private String associadoId;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(nullable = false)
    private Date data_emissao;

    @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean ativo = Boolean.TRUE;
}
