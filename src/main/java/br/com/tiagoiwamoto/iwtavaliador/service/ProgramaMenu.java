package br.com.tiagoiwamoto.iwtavaliador.service;

import java.sql.SQLException;
import br.com.tiagoiwamoto.iwtavaliador.App;
import br.com.tiagoiwamoto.iwtavaliador.util.ClasseAuxiliar;
import br.com.tiagoiwamoto.iwtavaliador.util.Leitor;

public class ProgramaMenu {
	public static void professorMenu() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		// Mostro o menu principal !!!
				System.out.println("|¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯|");
				System.out.println("                   Olá " + App.usuarioAtual + " Selecione uma opção                ");
				System.out.println("|-------------------------------------------------------------------------------|");
				System.out.println("|                                                                               |");
				System.out.println("|                [1] Avaliar professores     [2] Resultado parcial              |");
				System.out.println("|                                                                               |");
				System.out.println("|                [3] Cadastrar Professores   [4] Alterar Professores            |");
				System.out.println("|                                                                               |");
				System.out.println("|                [5] Excluir Professores     [6] Buscar Professores             |");
				System.out.println("|                                                                               |");
				System.out.println("|                                     [7] SAIR                                  |");
				System.out.println("|                       Obrigado por utilizar nosso sistema                     |");
				System.out.println("|_______________________________________________________________________________|");

				int selecao = Leitor.readInt(App.usuarioAtual + " selecione a opção desejada: ");

				// Se for maior que 7 o programa chama o menu novamente.
				if (selecao > 7) {
					ClasseAuxiliar.limpaTela();
					ClasseAuxiliar.bemVindo();
					professorMenu();
				}

				// Faço uma chave seletora.
				switch (selecao) {
				case 1:
					App.votoProfessor();
					break;
				case 2:
					App.rankingProfessores();
					break;
				case 3:
					App.registrarProfessor();
					break;
				case 4:
					App.atualizaProfessor();
					break;
				case 5:
					App.excluirProfessor();
					break;
				case 6:
					App.procuraProfessor();
					break;
				case 7:
					ClasseAuxiliar.fechaPrograma();
					break;
				default:
					System.out.println(App.usuarioAtual + " este sistema não tem opção superior a 6");
					System.out.println("O sistema será encerrado!");
					ClasseAuxiliar.fechaPrograma();
				}

	}
}
