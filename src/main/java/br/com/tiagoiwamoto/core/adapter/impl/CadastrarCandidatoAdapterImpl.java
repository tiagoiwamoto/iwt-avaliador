package br.com.tiagoiwamoto.core.adapter.impl;

import br.com.tiagoiwamoto.config.DatabaseConnect;
import br.com.tiagoiwamoto.core.adapter.CadastrarCandidatoAdapter;
import br.com.tiagoiwamoto.core.domain.model.Candidato;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CadastrarCandidatoAdapterImpl implements CadastrarCandidatoAdapter {
    @Override
    public Candidato cadastrarCandidato(Candidato candidato) {
        try(Connection connection = DatabaseConnect.connection()){
            PreparedStatement preparedStatement = DatabaseConnect
                    .preparedStatement(connection, "INSERT INTO TBL_CANDIDATOS (MATRICULA, NOME, ENDERECO, DATA_NASCIMENTO, MATERIA) VALUES(?, ?, ?, ?, ?)");
            preparedStatement.setString(1, candidato.getMatricula());
            preparedStatement.setString(2, candidato.getNome());
            preparedStatement.setString(3, candidato.getEndereco());
            preparedStatement.setDate(4, Date.valueOf(candidato.getDataNascimento()));
            preparedStatement.setString(5, candidato.getMateria());
            int result = preparedStatement.executeUpdate();
            if(result == 1){
                candidato.setId(preparedStatement.getGeneratedKeys().getLong(1));
                return candidato;
            }else{
                System.out.println("Não foi possível cadastrar este candidato");
                return null;
            }
        }catch (SQLException e){
            System.out.println("Não foi possível cadastrar este candidato");
            return null;
        }
    }
}
