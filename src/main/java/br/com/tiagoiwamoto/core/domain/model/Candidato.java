package br.com.tiagoiwamoto.core.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class Candidato {

    private Long id;
    private String matricula;
    private String nome;
    private String endereco;
    private LocalDate dataNascimento;
    private String materia;

}
