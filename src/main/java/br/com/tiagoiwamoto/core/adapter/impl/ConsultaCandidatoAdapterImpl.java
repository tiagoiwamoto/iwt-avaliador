package br.com.tiagoiwamoto.core.adapter.impl;

import br.com.tiagoiwamoto.config.DatabaseConnect;
import br.com.tiagoiwamoto.core.adapter.ConsultaCandidatoAdapter;
import br.com.tiagoiwamoto.core.converter.LocalDateConverter;
import br.com.tiagoiwamoto.core.domain.model.Candidato;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsultaCandidatoAdapterImpl implements ConsultaCandidatoAdapter {
    @Override
    public List<Candidato> buscarCandidados() {
        try(Connection connection = DatabaseConnect.connection()){
            ResultSet resultSet = DatabaseConnect.resultSet(connection, "SELECT * FROM TBL_CANDIDATOS");
            List<Candidato> candidatos = getCandidatos(resultSet);
            return candidatos;
        }catch (SQLException e){
            return List.of();
        }
    }

    @Override
    public Candidato buscaCandidatoPorMatricula(String matricula) {
        try(Connection connection = DatabaseConnect.connection()){
            String query = String.format("SELECT * FROM TBL_CANDIDATOS WHERE matricula = '%s'", matricula);
            ResultSet resultSet = DatabaseConnect.resultSet(connection, query);
            List<Candidato> candidatos = this.getCandidatos(resultSet);
            return candidatos.stream().findFirst().get();
        }catch (SQLException e){
            return null;
        }
    }

    public List<Candidato> buscarCandidadosComFiltro(String filtro) {
        try(Connection connection = DatabaseConnect.connection()){
            ResultSet resultSet = DatabaseConnect.resultSet(connection, "SELECT * FROM TBL_CANDIDATOS ORDER BY ".concat(filtro).concat(" DESC"));
            List<Candidato> candidatos = getCandidatos(resultSet);
            return candidatos;
        }catch (SQLException e){
            return List.of();
        }
    }

    private List<Candidato> getCandidatos(ResultSet resultSet) throws SQLException {
        List<Candidato> candidatos = new ArrayList<>();
        while (resultSet.next()){
            Candidato candidato = new Candidato();
            candidato.setId(resultSet.getLong("ID"));
            candidato.setNome(resultSet.getString("NOME"));
            candidato.setMatricula(resultSet.getString("MATRICULA"));
            candidato.setEndereco(resultSet.getString("ENDERECO"));
            candidato.setDataNascimento(LocalDateConverter.toLocaldate(resultSet.getDate("DATA_NASCIMENTO")));
            candidato.setMateria(resultSet.getString("MATERIA"));
            candidatos.add(candidato);
        }
        return candidatos;
    }
}
