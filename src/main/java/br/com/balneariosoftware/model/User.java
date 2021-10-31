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
@Table(name = "users")
public class User {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "user_sequence"
    )
    @Column(updatable = false)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String nome;

    @Column(nullable = false, columnDefinition = "TEXT", unique = true, updatable = false)
    private String username;

    @Column(nullable = false, columnDefinition = "TEXT", unique = true)
    private String email;

    @Column(nullable = false, columnDefinition = "VARCHAR(11)", unique = true, updatable = false)
    private String cpf;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(nullable = false, columnDefinition = "DATE")
    private Date data_nascimento;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date created_at = new Timestamp((new Date()).getTime());

    private Date update_at;

    @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean ativo = Boolean.TRUE;

}