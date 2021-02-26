package br.com.tiagoiwamoto.iwtavaliador;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 26/02/2021 | 06:22
 */

import br.com.tiagoiwamoto.iwtavaliador.dao.AvaliadorConsoleDao;
import br.com.tiagoiwamoto.iwtavaliador.model.ProfessorConsole;
import br.com.tiagoiwamoto.iwtavaliador.service.ProgramaMenu;
import br.com.tiagoiwamoto.iwtavaliador.util.ClasseAuxiliar;
import br.com.tiagoiwamoto.iwtavaliador.util.Leitor;

import java.sql.SQLException;
import java.util.List;

public class App {

    public static String usuarioAtual;

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        //Chamo o metodo auxiliar bemvindo e o sistema de autenticação
        ClasseAuxiliar.bemVindo();
        programaProtegido();
    }




    static void programaProtegido() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
        // Defino uma senha para acessar o programa
        int palavraChave = 123456;
        // Gravo o nome do usuário na variavel GLOBAL
        usuarioAtual = Leitor.readString("Qual o seu nome? ");

        int senha = Leitor.readInt("Digite a senha de administrador para continuar: ");

        // Se a senha digitada for diferente da senha definida o programa
        // mostra msg de erro e encerra o programa.
        if (senha != palavraChave) {
            System.out.println("Ops! Moiooo! Senha incorreta!");
            System.exit(0);
        } else {
            // Se a senha for correta o menu principal será exibido !!
            ClasseAuxiliar.limpaTela();
            ClasseAuxiliar.bemVindo();
            ProgramaMenu.professorMenu();
        }
    }

    public static void votoProfessor() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
        //Listo todos os professores para mostrar as opções de voto
        listaProfessores();
        System.out.println();
        int votoSelecionado;
        //Para poder utilizar os atributos crio um objeto da classe ProfessorConsole
        ProfessorConsole avaliando = new ProfessorConsole();
        //Pergunta qual professor será avaliado
        long Matricula = Leitor.readInt(usuarioAtual + " selecione o professor que deseja avaliar: ");
        System.out.println();
        //em seguida pergunta se o mesmo é bom ou ruim
        votoSelecionado = Leitor.readInt("[1]BOM  [2]RUIM: ");
        if(votoSelecionado > 3){
            System.out.println(usuarioAtual + " você não está usando o sistema corretamente...");
            ClasseAuxiliar.fechaPrograma();
        }
        //Chama o metodo responsável por avaliar e passa o objeto
        //AvaliadorConsoleDao.processaVotos(avaliando);
        System.out.println(AvaliadorConsoleDao.processaVotos(Matricula, votoSelecionado));
        ClasseAuxiliar.fechaPrograma();

    }

    public static void registrarProfessor() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
        //Antes de inserir o sistema oferece a opção de voltar ao menu ou encerrar o sistema
        System.out.println(usuarioAtual + " você vai inserir um novo professor !!");
        System.out.println("Para voltar ao menu principal ou encerrar digite 0");
        //Antes de inicar o registro o sistema confirma mais uma vez
        int novoRegistro = Leitor.readInt("Inserir novo registro ? [1]Sim [2]Não : ");
        if(novoRegistro == 1){
            //Crio um novo objeto para a classe ProfessorConsole
            ProfessorConsole professor = new ProfessorConsole();
            professor.Matricula = Leitor.readLong("Matricula: ");
            professor.Nome = Leitor.readString("     Nome: ");
            professor.Endereco = Leitor.readString(" Endereço: ");
            professor.Idade = Leitor.readInt("    Idade: ");
            professor.Materia = Leitor.readString("  Matéria: ");

            //Chamo a classe e passo o objeto

            System.out.println(AvaliadorConsoleDao.inserirProfessor(professor));


            //Chamo a classe que retorna ao menu ou encerra
            ClasseAuxiliar.fechaPrograma();
        }else{
            //Se o mesmo não confirmar o registro encerra ou volta ao menu
            ClasseAuxiliar.fechaPrograma();
        }

    }

    public static void atualizaProfessor() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
        //Listo todos os professores
        //para melhor visualização de qual será atualizado.
        listaProfessores();
        System.out.println();
        //Se o usuário quiser voltar ao menu basta digitar 0
        System.out.println("Para voltar ao menu principal ou encerrar o programa digite 0");

        //Pergunto qual professor será atualizado
        int Matricula = Leitor.readInt(usuarioAtual + " informe o numero da matricula do professor que deseja alterar: ");

        if(Matricula == 0){
            //Se for 0 chama a classe que encerra ou retorna ao menu.
            ClasseAuxiliar.fechaPrograma();
        }
        //Crio um novo objeto de ProfessorConsole
        ProfessorConsole professor = new ProfessorConsole();
        professor.Nome = Leitor.readString("Informe o novo nome: ");
        professor.Endereco = Leitor.readString("Informe o novo endereço: ");
        professor.Idade = Leitor.readInt("Informe uma nova idade: ");
        professor.Materia = Leitor.readString("Informe a nova matéria: ");

        //Chamo o metodo que atualiza e passo o objeto.
        System.out.println(AvaliadorConsoleDao.atualizaProfessor(professor, Matricula));

        //Chamo o metodo que atualiza ou retorna ao menu
        ClasseAuxiliar.fechaPrograma();
    }

    public static void excluirProfessor() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
        //Listo todos os professores
        listaProfessores();
        //Usuário pode voltar ao menu ou encerrar o programa
        //digitando 0
        System.out.println("Para retornar ao menu ou encerrar o programa digite 0");
        int Matricula = Leitor.readInt(usuarioAtual + " informe o numero da matricula do professor que deseja excluir: ");

        //Digitando 0 a classe que encerra ou retorna ao menu é chamada
        if(Matricula == 0){
            ClasseAuxiliar.fechaPrograma();
        }
        //Chamo a classe que exclui e passo o numero da matricula
        //que será deletado
        System.out.println(AvaliadorConsoleDao.excluiProfessor(Matricula));
        //Depois de excluir ou executar a classe excluir
        //o programa chama a classe que encerra ou retorna ao menu.
        ClasseAuxiliar.fechaPrograma();
    }

    public static void rankingProfessores() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
        //Inicia o filtro com padrão 1
        String filtro = "1";
        //1 filtra pelos professores melhores classificados
        //2 filtra pelos professores menos classificados
        System.out.println("[1]Melhores Professores  [2]Piores Professores");
        filtro = Leitor.readString(usuarioAtual + " o filtro para exibir os professores em ordem: ");

        //Digitando 1 seta o filtro com VOTO_BOM
        if(filtro.equals("1")){
            filtro = "VOTO_BOM";
        }
        //Digitando 2 seta o filtro com VOTO_RUIM
        if(filtro.equals("2")){
            filtro = "VOTO_RUIM";
        }

        //qualquer outro valor digitado lista com o filtro 1 = voto_bom

        //Chamo o metodo que lista passando o filtro desejado.
        List<ProfessorConsole> lista = AvaliadorConsoleDao.rankingProfessores(filtro);
        if(lista.isEmpty()){
            System.out.println("Desculpe, ainda não temos professores cadastrados...");
        }
        System.out.println("Matricula                         Nome                 Materia     Nota Boa     Nota Ruim");
        System.out.println("        ▼                            ▼                       ▼         ▼            ▼");
        for (ProfessorConsole professorConsole : lista) {
            System.out.format("%9d    %25s         %15s         %d            %d%n",professorConsole.Matricula,professorConsole.Nome,professorConsole.Materia,professorConsole.votoBom,professorConsole.votoRuim);
            System.out.println("----------    -------------------------         ---------------       --           --");
        }
        //Encerra ou retorna ao menu
        ClasseAuxiliar.fechaPrograma();
    }

    public static void procuraProfessor() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
        //Pergunta o que deseja procurar
        String Nome = Leitor.readString(usuarioAtual + " informe o nome do professor que deseja buscar: ");
        //Se nada for digitado mostra a mensagem...
        if(Nome.equals("")){
            System.out.println("Para buscar algo preciso saber o que é né...");
            //Encerra ou retorna ao menu...
            ClasseAuxiliar.fechaPrograma();
        }
        //Chamo a classe que faz a busca e passo o nome digitado...
        List<ProfessorConsole> lista = AvaliadorConsoleDao.buscaProfessor(Nome);
        if(lista.isEmpty()){
            System.out.println("Desculpe, não encontramos nenhum professor...");
        }
        for (ProfessorConsole professorConsole : lista) {
            System.out.println("Matricula: " + professorConsole.Matricula);
            System.out.println("     Nome: " + professorConsole.Nome);
            System.out.println(" Endereço: " + professorConsole.Endereco);
            System.out.println("    Idade: " + professorConsole.Idade);
            System.out.println("  Materia: " + professorConsole.Materia);
            System.out.println("    Likes: " + professorConsole.votoBom);
            System.out.println(" Dislikes: " + professorConsole.votoRuim);
            System.out.println("------------------------------------------------------------");
        }
        //Encerra ou retorna ao menu...
        ClasseAuxiliar.fechaPrograma();
    }

    public static void listaProfessores() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
        List<ProfessorConsole> lista = AvaliadorConsoleDao.listaTodos();
        if(lista.isEmpty()){
            System.out.println("Desculpe, ainda não temos professores cadastrados...");
        }
        System.out.println("Matricula                         Nome                    Endereço                    Materia     Idade");
        System.out.println("        ▼                            ▼                           ▼                          ▼        ▼");
        for (ProfessorConsole professorConsole : lista) {

            System.out.format("%9d    %25s        %20s       %20s       %d%n",professorConsole.Matricula,professorConsole.Nome,professorConsole.Endereco,professorConsole.Materia,professorConsole.Idade);
            System.out.println("---------    -------------------------        --------------------       --------------------       --");


        }

    }

}
