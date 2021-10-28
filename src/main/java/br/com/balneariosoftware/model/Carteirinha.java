package br.com.balneariosoftware.model;

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
@Table(name = "carteirinha")
public class Carteirinha {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String id_carteirinha;

    private Date created_at;

    private Date update_at;

    @OneToOne
    @JoinColumn(name = "id", nullable = false)
    private User associado;

    private Date data_emissao;

    private Boolean ativo;
}
