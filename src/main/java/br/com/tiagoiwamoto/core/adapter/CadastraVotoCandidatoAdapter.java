package br.com.tiagoiwamoto.core.adapter;

import br.com.tiagoiwamoto.core.domain.enums.VotoType;
import br.com.tiagoiwamoto.core.domain.model.CandidatoVoto;

public interface CadastraVotoCandidatoAdapter {

    CandidatoVoto registraVotoParaCandidato(String matricula, VotoType votoType);

}
