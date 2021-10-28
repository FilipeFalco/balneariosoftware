package br.com.balneariosoftware.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "funcionario")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String password;

    @NotNull
    @Column(unique = true, name = "usuario")
    private Long usuarioId;

    private Date created_at;

    private Date update_at;

    private String updated_by;

    private Boolean liberador_acesso;
}
