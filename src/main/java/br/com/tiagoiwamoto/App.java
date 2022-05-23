package br.com.tiagoiwamoto;

import br.com.tiagoiwamoto.core.domain.enums.Opcao;
import br.com.tiagoiwamoto.core.usecase.AdminUsecase;
import br.com.tiagoiwamoto.core.usecase.CadastrarVotoCandidatoUsecase;
import br.com.tiagoiwamoto.core.usecase.ResultadoParcialUsecase;
import br.com.tiagoiwamoto.core.util.AppUtils;
import br.com.tiagoiwamoto.core.util.Leitor;

public class App {

    public static String usuarioLogado = "";

    public static void main(String[] args) {
        home();
    }

    public static void home() {
        header();
        int opcaoSelecionada = Leitor.readInt("Selecione entre as opções 1, 2, 3 e 4: ");

        Opcao opcao = Opcao.value(opcaoSelecionada);
        if(AppUtils.validarOpcaoSelecionada(opcaoSelecionada)){
            AppUtils.limpaTela();
            System.out.printf("Opção selecionada %d é inválida", opcaoSelecionada);
            System.out.println();
            header();
        }

        switch (opcao){
            case AVALIAR_CANDIDADO -> CadastrarVotoCandidatoUsecase.votarCandidato();
            case RESULTADO_PARCIAL -> ResultadoParcialUsecase.resultadoParcial();
            case MODO_ADMIN -> AdminUsecase.homeAdmin();
            default -> closeApp();
        }
    }

    public static void header() {
        AppUtils.lerArquivo("src/main/resources/main.txt");
    }

    public static void closeApp() {
        AppUtils.limpaTela();
        AppUtils.lerArquivo("src/main/resources/end.txt");
        System.exit(0);
    }

}
