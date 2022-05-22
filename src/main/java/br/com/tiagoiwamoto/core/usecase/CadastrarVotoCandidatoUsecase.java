package br.com.tiagoiwamoto.core.usecase;

import br.com.tiagoiwamoto.core.adapter.impl.CadastraVotoCandidatoAdapterImpl;
import br.com.tiagoiwamoto.core.adapter.impl.ConsultaCandidatoAdapterImpl;
import br.com.tiagoiwamoto.core.domain.enums.VotoType;
import br.com.tiagoiwamoto.core.domain.model.Candidato;
import br.com.tiagoiwamoto.core.domain.model.CandidatoVoto;
import br.com.tiagoiwamoto.core.util.Leitor;

import java.util.List;
import java.util.Objects;

public class CadastrarVotoCandidatoUsecase {

    public static void votarCandidato() {
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

        String matricula = Leitor.readString("Informe a matricula do candidato que deseja avaliar: ");
        int votoSelecionado = Leitor.readInt("[1]LIKE  [2]DISLIKE: ");
        VotoType votoType;
        if(votoSelecionado == 1){
            votoType = VotoType.LIKE;
        }else {
            votoType = VotoType.DISLIKE;
        }
        CandidatoVoto candidatoVoto = new CadastraVotoCandidatoAdapterImpl().registraVotoParaCandidato(matricula, votoType);
        if(Objects.isNull(candidatoVoto)){
            System.out.println("Não foi possível registrar seu voto");
        }else{
            System.out.println("Voto registrado com sucesso!");
        }
        AdminUsecase.homeAdmin();

    }

}
