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
@Table(name = "funcionario")
public class Funcionario {

    @Id
    @SequenceGenerator(
            name = "funcionario_sequence",
            sequenceName = "funcionario_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "funcionario_sequence"
    )
    private Long id;

    @Column(nullable = false, columnDefinition = "VARCHAR(16)")
    private String password;

    @Column(unique = true, nullable = false, columnDefinition = "BIGINT")
    private Long usuarioId;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date created_at = new Timestamp((new Date()).getTime());

    private Date update_at;

    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean liberador_acesso = Boolean.FALSE;
}
