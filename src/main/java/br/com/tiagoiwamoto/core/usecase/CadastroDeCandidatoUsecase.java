package br.com.tiagoiwamoto.core.usecase;

import br.com.tiagoiwamoto.App;
import br.com.tiagoiwamoto.core.adapter.impl.CadastrarCandidatoAdapterImpl;
import br.com.tiagoiwamoto.core.domain.model.Candidato;
import br.com.tiagoiwamoto.core.util.AppUtils;
import br.com.tiagoiwamoto.core.util.Constants;
import br.com.tiagoiwamoto.core.util.Leitor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class CadastroDeCandidatoUsecase {

    public static void cadastrarCandidato(){
        CadastrarCandidatoAdapterImpl cadastrarCandidatoAdapter = new CadastrarCandidatoAdapterImpl();
        System.out.println(App.usuarioLogado.concat(" você vai inserir um novo professor !!"));
        System.out.println("Para voltar ao menu principal digite 0");

        int inserirNovoRegistro = Leitor.readInt("Inserir novo registro ? [1]Sim [2]Não : ");

        if(inserirNovoRegistro == 1){
            Candidato candidato = new Candidato();
            candidato.setMateria(Leitor.readString(Constants.MATERIA));
            candidato.setEndereco(Leitor.readString(Constants.ENDERECO));
            candidato.setNome(Leitor.readString(Constants.NOME));
            candidato.setDataNascimento(LocalDate.parse(Leitor.readString(Constants.DATA_NASCIMENTO), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            candidato.setMatricula(Leitor.readString(Constants.MATRICULA));
            Candidato candidatoCadastrado = cadastrarCandidatoAdapter.cadastrarCandidato(candidato);
            if(Objects.nonNull(candidatoCadastrado)){
                AppUtils.limpaTela();
                System.out.println("====== Candidato cadastrado com sucesso ! ======");
                App.home();
            }
        }else{
            AppUtils.limpaTela();
            System.out.println("====== Não foi possível cadastrar o candidato ======");
            App.home();
        }


    }

}
