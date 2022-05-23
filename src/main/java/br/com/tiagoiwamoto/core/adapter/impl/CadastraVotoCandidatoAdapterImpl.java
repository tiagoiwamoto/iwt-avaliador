package br.com.tiagoiwamoto.core.adapter.impl;

import br.com.tiagoiwamoto.config.DatabaseConnect;
import br.com.tiagoiwamoto.core.adapter.CadastraVotoCandidatoAdapter;
import br.com.tiagoiwamoto.core.domain.enums.VotoType;
import br.com.tiagoiwamoto.core.domain.model.Candidato;
import br.com.tiagoiwamoto.core.domain.model.CandidatoVoto;
import br.com.tiagoiwamoto.core.usecase.AdminUsecase;
import br.com.tiagoiwamoto.core.util.Constants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class CadastraVotoCandidatoAdapterImpl implements CadastraVotoCandidatoAdapter {
    @Override
    public CandidatoVoto registraVotoParaCandidato(String matricula, VotoType votoType) {
        try(Connection connection = DatabaseConnect.connection()){
            Candidato candidato = new ConsultaCandidatoAdapterImpl().buscaCandidatoPorMatricula(matricula);
            if(Objects.isNull(candidato)){
                System.out.println(Constants.NENHUM_CANDIDATO);
                AdminUsecase.homeAdmin();
            }
            PreparedStatement preparedStatement =
                    DatabaseConnect.preparedStatement(connection, "INSERT INTO TBL_CANDIDATO_VOTO (type, id_candidato) VALUES (?, ?)");
            preparedStatement.setString(1, votoType.name());
            preparedStatement.setLong(2, candidato.getId());
            int result = preparedStatement.executeUpdate();
            if(result == 1){
                return new CandidatoVoto(preparedStatement.getGeneratedKeys().getLong(1), votoType, candidato.getId());
            }else{
                System.out.println("Não foi possível votar neste candidato");
                return null;
            }
        }catch (SQLException e){
            return null;
        }
    }
}
