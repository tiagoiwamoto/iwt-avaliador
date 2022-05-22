package br.com.tiagoiwamoto.core.adapter;

import br.com.tiagoiwamoto.core.domain.model.Candidato;

import java.util.List;

public interface ConsultaCandidatoAdapter {

    List<Candidato> buscarCandidados();
    Candidato buscaCandidatoPorMatricula(String matricula);

}
