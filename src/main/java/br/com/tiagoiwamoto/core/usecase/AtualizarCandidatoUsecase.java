package br.com.tiagoiwamoto.core.usecase;

import br.com.tiagoiwamoto.App;
import br.com.tiagoiwamoto.core.adapter.impl.AtualizaCandidatoAdapterImpl;
import br.com.tiagoiwamoto.core.adapter.impl.CadastrarCandidatoAdapterImpl;
import br.com.tiagoiwamoto.core.adapter.impl.ConsultaCandidatoAdapterImpl;
import br.com.tiagoiwamoto.core.domain.model.Candidato;
import br.com.tiagoiwamoto.core.util.Constants;
import br.com.tiagoiwamoto.core.util.Leitor;
import br.com.tiagoiwamoto.core.util.LimpaTela;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

public class AtualizarCandidatoUsecase {

    public static void atualizarCandidato(){
        AtualizaCandidatoAdapterImpl atualizaCandidatoAdapter = new AtualizaCandidatoAdapterImpl();
        System.out.println(App.usuarioLogado.concat(" você vai inserir um novo professor !!"));
        System.out.println("Para voltar ao menu principal digite 0");

        List<Candidato> candidatos = new ConsultaCandidatoAdapterImpl().buscarCandidados();
        if(candidatos.isEmpty()){
            LimpaTela.execute();
            System.out.println("Desculpe, ainda não temos candidatos cadastrados...");
            App.home();
        }
        System.out.println("Matricula                         Nome                     Materia");
        System.out.println("        ▼                            ▼                           ▼");
        candidatos.forEach(candidato -> {
            System.out.format("%9s    %25s        %20s%n",candidato.getMatricula(), candidato.getNome(), candidato.getMateria());
            System.out.println("---------    -------------------------        --------------------");
        });

        Candidato candidatoParaAtualizar = new Candidato();
        System.out.println("===== INFORME OS DADOS A SEREM ATUALIZADOS =====");
        candidatoParaAtualizar.setMateria(Leitor.readString(Constants.MATERIA));
        candidatoParaAtualizar.setEndereco(Leitor.readString(Constants.ENDERECO));
        candidatoParaAtualizar.setNome(Leitor.readString(Constants.NOME));
        candidatoParaAtualizar.setDataNascimento(LocalDate.parse(Leitor.readString(Constants.DATA_NASCIMENTO), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        candidatoParaAtualizar.setMatricula(Leitor.readString(Constants.MATRICULA));
        Candidato candidatoCadastrado = atualizaCandidatoAdapter.atualizarCandidato(candidatoParaAtualizar);
        if(Objects.nonNull(candidatoCadastrado)){
            LimpaTela.execute();
            System.out.println("====== Candidato atualizado com sucesso ! ======");
            App.home();
        }


    }

}
