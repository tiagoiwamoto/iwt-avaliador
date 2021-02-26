package br.com.tiagoiwamoto.iwtavaliador.util;

import br.com.tiagoiwamoto.iwtavaliador.App;
import br.com.tiagoiwamoto.iwtavaliador.service.ProgramaMenu;

import java.sql.SQLException;

public class ClasseAuxiliar {
	public static void bemVindo() {
		// Imprime apenas a tela com o nome do programa em ASCII
		System.out.println("   ###    ##     ##    ###    ##       ####    ###    ########   #######  ########");
		System.out.println("  ## ##   ##     ##   ## ##   ##        ##    ## ##   ##     ## ##     ## ##     ##");
		System.out.println(" ##   ##  ##     ##  ##   ##  ##        ##   ##   ##  ##     ## ##     ## ##     ##");
		System.out.println("##     ## ##     ## ##     ## ##        ##  ##     ## ##     ## ##     ## ########");
		System.out.println("#########  ##   ##  ######### ##        ##  ######### ##     ## ##     ## ##   ##");
		System.out.println("##     ##   ## ##   ##     ## ##        ##  ##     ## ##     ## ##     ## ##    ##");
		System.out.println("##     ##    ###    ##     ## ######## #### ##     ## ########   #######  ##     ##");
		System.out.println("                                                                           V: 1.0.0");
	}
	
	public static void fechaPrograma() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		// Ao carregar este metodo ele perguta se o usuário quer encerrar o
		// programa ou voltar ao menu.
		int fecharPrograma = Leitor.readInt(
				App.usuarioAtual + " você deseja encerrar o programa ou voltar ao menu? [1]Encerra  [2]Volta Menu : ");
		if (fecharPrograma > 2) {
			System.out.println(App.usuarioAtual + " você não entendeu que só pode escolher 1 ou 2 ??");
			System.out.println("O sistema foi encerrado pela sua ignorancia !!");
		}
		// Se o valor digitado for 1 o programa mostra a mensagem e fecha o
		// programa.
		if (fecharPrograma == 1) {
			System.out.println("                       Obrigado e até logo " + App.usuarioAtual);
			System.out.println("|         ########  ##    ## ########    ########  ##    ## ########            |");
			System.out.println("|         ##     ##  ##  ##  ##          ##     ##  ##  ##  ##                  |");
			System.out.println("|         ##     ##   ####   ##          ##     ##   ####   ##                  |");
			System.out.println("|         ########     ##    ######      ########     ##    ######              |");
			System.out.println("|         ##     ##    ##    ##          ##     ##    ##    ##                  |");
			System.out.println("|         ##     ##    ##    ##          ##     ##    ##    ##                  |");
			System.out.println("|         ########     ##    ########    ########     ##    ########            |");
			System.exit(0);
		} else {
			// Se o usuário digitar qualquer outro valor o programa volta ao
			// menu
			ClasseAuxiliar.limpaTela();
			ClasseAuxiliar.bemVindo();
			ProgramaMenu.professorMenu();
			}

	}
	
	public static void limpaTela(){
		for(int i = 0; i < 50; i++){
			System.out.println("");
		}
	}
}
