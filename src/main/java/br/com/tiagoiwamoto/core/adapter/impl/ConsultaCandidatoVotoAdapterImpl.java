package br.com.tiagoiwamoto.core.adapter.impl;

import br.com.tiagoiwamoto.config.DatabaseConnect;
import br.com.tiagoiwamoto.core.adapter.ConsultaCandidatoVotoAdapter;
import br.com.tiagoiwamoto.core.domain.enums.VotoType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultaCandidatoVotoAdapterImpl implements ConsultaCandidatoVotoAdapter {
    @Override
    public Integer buscaVotosParaCandidato(Long idCandidato, VotoType type) {
        try(Connection connection = DatabaseConnect.connection()){
            String query = String.format("SELECT count(id) FROM TBL_CANDIDATO_VOTO WHERE id_candidato = %s AND type = '%s'", idCandidato, type.name());
            ResultSet resultSet = DatabaseConnect.resultSet(connection, query);
            return resultSet.getInt(1);
        }catch (SQLException e){
            return 0;
        }
    }
}
