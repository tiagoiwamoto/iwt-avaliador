package br.com.tiagoiwamoto.core.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidatoComVoto {

    private Candidato candidato;
    private Integer likes;
    private Integer dislikes;

}
