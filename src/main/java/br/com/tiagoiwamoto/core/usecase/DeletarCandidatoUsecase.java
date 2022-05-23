package br.com.tiagoiwamoto.core.usecase;

import br.com.tiagoiwamoto.App;
import br.com.tiagoiwamoto.core.adapter.impl.ConsultaCandidatoAdapterImpl;
import br.com.tiagoiwamoto.core.adapter.impl.DeletarCandidatoAdapterImpl;
import br.com.tiagoiwamoto.core.domain.model.Candidato;
import br.com.tiagoiwamoto.core.util.Leitor;

import java.util.List;

public class DeletarCandidatoUsecase {

    public static void deletarCandidato() {
        List<Candidato> candidatos = new ConsultaCandidatoAdapterImpl().buscarCandidados();
        if(candidatos.isEmpty()){
            System.out.println("Desculpe, ainda não temos candidatos cadastrados...");
        }
        System.out.println("Matricula                         Nome                     Materia");
        System.out.println("        ▼                            ▼                           ▼");
        candidatos.forEach(candidato -> {
            System.out.format("%9s    %25s        %20s%n",candidato.getMatricula(), candidato.getNome(), candidato.getMateria());
            System.out.println("---------    -------------------------        --------------------");
        });

        String matricula = Leitor.readString("Informe a matricula do candidato que deseja remover: ");
        new DeletarCandidatoAdapterImpl().removerCandidato(matricula);
        App.home();

    }

}
