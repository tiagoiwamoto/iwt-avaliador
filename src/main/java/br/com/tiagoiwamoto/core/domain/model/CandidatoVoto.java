package br.com.tiagoiwamoto.core.domain.model;

import br.com.tiagoiwamoto.core.domain.enums.VotoType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidatoVoto {

    private Long id;
    private VotoType type;
    private Long candidato;

}
