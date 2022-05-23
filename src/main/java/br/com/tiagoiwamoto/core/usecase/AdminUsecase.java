package br.com.tiagoiwamoto.core.usecase;

import br.com.tiagoiwamoto.App;
import br.com.tiagoiwamoto.core.domain.enums.Opcao;
import br.com.tiagoiwamoto.core.util.Leitor;
import br.com.tiagoiwamoto.core.util.AppUtils;

public class AdminUsecase {

     static String password = "123456";

    public static void homeAdmin(){
        adminHeader();
        System.out.println();
        String senha = Leitor.readString("Digite a senha de administrador para continuar: ");
        if(senha.equals(password)){
            AppUtils.lerArquivo("src/main/resources/admin-opcoes.txt");
            System.out.println();
            int opcaoSelecionada = Leitor.readInt("Selecione a opção desejada: ");
            Opcao opcao = Opcao.value(opcaoSelecionada);
            switch (opcao){
                case CADASTRAR_CANDIDATO -> CadastroDeCandidatoUsecase.cadastrarCandidato();
                case ATUALIZAR_CANDIDATO -> AtualizarCandidatoUsecase.atualizarCandidato();
                case EXCLUIR_CANDIDATO -> DeletarCandidatoUsecase.deletarCandidato();
                default -> App.home();
            }
        }else{
            System.out.println("===== Credenciais inválidas =====");
            AppUtils.delay();
            App.closeApp();
        }
    }

    private static void adminHeader(){
        AppUtils.lerArquivo("src/main/resources/admin.txt");
    }

}
