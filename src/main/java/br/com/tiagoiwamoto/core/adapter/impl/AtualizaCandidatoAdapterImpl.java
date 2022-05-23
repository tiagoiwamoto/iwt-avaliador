package br.com.tiagoiwamoto.core.adapter.impl;

import br.com.tiagoiwamoto.config.DatabaseConnect;
import br.com.tiagoiwamoto.core.adapter.AtualizaCandidatoAdapter;
import br.com.tiagoiwamoto.core.adapter.CadastrarCandidatoAdapter;
import br.com.tiagoiwamoto.core.domain.model.Candidato;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AtualizaCandidatoAdapterImpl implements AtualizaCandidatoAdapter {
    @Override
    public Candidato atualizarCandidato(Candidato candidato) {
        try(Connection connection = DatabaseConnect.connection()){
            PreparedStatement preparedStatement = DatabaseConnect
                    .preparedStatement(connection, "UPDATE TBL_CANDIDATOS SET NOME = ?, ENDERECO = ?, DATA_NASCIMENTO = ?, MATERIA = ? WHERE MATRICULA = ?");
            preparedStatement.setString(1, candidato.getNome());
            preparedStatement.setString(2, candidato.getEndereco());
            preparedStatement.setDate(3, Date.valueOf(candidato.getDataNascimento()));
            preparedStatement.setString(4, candidato.getMateria());
            preparedStatement.setString(5, candidato.getMatricula());
            int result = preparedStatement.executeUpdate();
            if(result == 1){
                return candidato;
            }else{
                System.out.println("Não foi possível atualizar este candidato");
                return null;
            }
        }catch (SQLException e){
            System.out.println("Não foi possível atualizar este candidato");
            return null;
        }
    }
}
