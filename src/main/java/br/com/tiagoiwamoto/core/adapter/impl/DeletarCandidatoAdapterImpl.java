package br.com.tiagoiwamoto.core.adapter.impl;

import br.com.tiagoiwamoto.config.DatabaseConnect;
import br.com.tiagoiwamoto.core.adapter.DeletarCandidatoAdapter;
import br.com.tiagoiwamoto.core.domain.model.Candidato;
import br.com.tiagoiwamoto.core.usecase.AdminUsecase;
import br.com.tiagoiwamoto.core.util.Constants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Objects;

public class DeletarCandidatoAdapterImpl implements DeletarCandidatoAdapter {
    @Override
    public Boolean removerCandidato(String matricula) {
        try(Connection connection = DatabaseConnect.connection()) {
            Candidato candidato = new ConsultaCandidatoAdapterImpl().buscaCandidatoPorMatricula(matricula);
            if(Objects.isNull(candidato)){
                System.out.println(Constants.NENHUM_CANDIDATO);
                AdminUsecase.homeAdmin();
            }
            PreparedStatement preparedStatement = DatabaseConnect
                    .preparedStatement(connection, "DELETE FROM TBL_CANDIDATOS WHERE MATRICULA = ?");
            preparedStatement.setString(1, candidato.getMatricula());
            int result = preparedStatement.executeUpdate();
            if (result == 1) {
                System.out.println("Candidato foi removido com sucesso!");
                preparedStatement = DatabaseConnect
                        .preparedStatement(connection, "DELETE FROM TBL_CANDIDATO_VOTO WHERE ID_CANDIDATO = ?");
                preparedStatement.setLong(1, candidato.getId());
                preparedStatement.executeUpdate();
                System.out.println("Votos para este candidato foram removidos com sucesso.");
                return true;
            } else {
                System.out.println("Não foi possível remover este candidato");
                return false;
            }
        }catch (Exception e){
            System.out.println("Não foi possível remover este candidato");
            return false;
        }
    }
}
