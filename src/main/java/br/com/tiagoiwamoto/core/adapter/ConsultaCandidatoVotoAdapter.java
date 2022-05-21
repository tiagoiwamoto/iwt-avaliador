package br.com.tiagoiwamoto.core.adapter;

import br.com.tiagoiwamoto.core.domain.enums.VotoType;
import br.com.tiagoiwamoto.core.domain.model.CandidatoVoto;

import java.util.List;

public interface ConsultaCandidatoVotoAdapter {

    Integer buscaVotosParaCandidato(Long idCandidato, VotoType type);

}
