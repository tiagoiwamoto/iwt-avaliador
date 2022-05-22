package br.com.tiagoiwamoto.core.usecase;

import br.com.tiagoiwamoto.App;
import br.com.tiagoiwamoto.core.adapter.impl.ConsultaCandidatoAdapterImpl;
import br.com.tiagoiwamoto.core.adapter.impl.ConsultaCandidatoVotoAdapterImpl;
import br.com.tiagoiwamoto.core.domain.enums.VotoType;
import br.com.tiagoiwamoto.core.domain.model.Candidato;
import br.com.tiagoiwamoto.core.domain.model.CandidatoComVoto;
import br.com.tiagoiwamoto.core.domain.model.CandidatoVoto;
import br.com.tiagoiwamoto.core.util.Leitor;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ResultadoParcialUsecase {

    public static void resultadoParcial() {
        List<CandidatoComVoto> candidatoComVotos = new ArrayList<>();
        ConsultaCandidatoAdapterImpl consultaCandidatoAdapter = new ConsultaCandidatoAdapterImpl();
        List<Candidato> candidatos = consultaCandidatoAdapter.buscarCandidadosComFiltro("MATRICULA");
        System.out.println("Matricula                         Nome                    Endereço                    Materia     Data de nascimento     Likes   Dislikes");
        candidatos.forEach(candidato -> {
            CandidatoComVoto candidatoComVoto =
                    new CandidatoComVoto(candidato,
                            new ConsultaCandidatoVotoAdapterImpl().buscaVotosParaCandidato(candidato.getId(), VotoType.LIKE),
                            new ConsultaCandidatoVotoAdapterImpl().buscaVotosParaCandidato(candidato.getId(), VotoType.DISLIKE));
            candidatoComVotos.add(candidatoComVoto);
        });
        candidatoComVotos.forEach(candidatoComVoto -> {
            System.out.format("%9s    %25s        %20s       %20s              %s       %3d        %3d%n",
                    candidatoComVoto.getCandidato().getMatricula(),
                    candidatoComVoto.getCandidato().getNome(),
                    candidatoComVoto.getCandidato().getEndereco(),
                    candidatoComVoto.getCandidato().getMateria(),
                    DateTimeFormatter.ofPattern("dd/MM/yyyy").format(candidatoComVoto.getCandidato().getDataNascimento()),
                    candidatoComVoto.getLikes(),
                    candidatoComVoto.getDislikes()
                    );
            System.out.println("---------    -------------------------         --------------------       --------------------             ----------       ---        ---");
            System.out.println();
        });
        int opcaoSelecionada = Leitor.readInt("Qual ação deseja realizar? [1] Voltar || [2] Sair: ");
        if(opcaoSelecionada == 1){
            App.home();
        }else{
            App.closeApp();
        }
    }

}
