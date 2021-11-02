package br.com.balneariosoftware.model;

import lombok.*;
import org.hibernate.annotations.GenerationTime;
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

    @Column(updatable = false, nullable = false)
    private String id_carteirinha;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date created_at = new Timestamp((new Date()).getTime());

    private Date update_at;

    @Column(nullable = false, columnDefinition = "BIGINT")
    private Long usuarioId;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(insertable = false, updatable = false, columnDefinition = "DATE DEFAULT CURRENT_TIMESTAMP")
    private Date data_emissao = new Timestamp((new Date()).getTime());

    @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean ativo = Boolean.TRUE;
}
