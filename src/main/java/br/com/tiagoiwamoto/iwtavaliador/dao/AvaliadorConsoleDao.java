package br.com.tiagoiwamoto.iwtavaliador.dao;

import br.com.tiagoiwamoto.iwtavaliador.App;
import br.com.tiagoiwamoto.iwtavaliador.model.ProfessorConsole;
import br.com.tiagoiwamoto.iwtavaliador.util.DBClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AvaliadorConsoleDao {
	
	public static String processaVotos(long matricula, int votoSelecionado) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		Connection conn = null;
		String msg;
		try{
			conn = DBClass.getConnection();
			ResultSet verificaExiste = DBClass.getResultSet(conn, "SELECT MATRICULA FROM TBL_PROFESSORES WHERE MATRICULA = " + matricula);
			if(!verificaExiste.next()){
				msg = "Não existe professor com está matricula!!";
				return msg;
			}
				switch(votoSelecionado){
					case 1:
						PreparedStatement preparaVotoBom = DBClass.getPreparedStatement(conn, "UPDATE TBL_PROFESSORES SET VOTO_BOM = VOTO_BOM +1 WHERE MATRICULA = ?");
						preparaVotoBom.setLong(1, matricula);
							if(preparaVotoBom.executeUpdate() == 1){
								msg =  App.usuarioAtual + " você votou + obrigado";

							}else{
								msg = App.usuarioAtual + " algo deu errado...";

							}
							break;
					case 2:
						PreparedStatement preparaVotoRuim = DBClass.getPreparedStatement(conn, "UPDATE TBL_PROFESSORES SET VOTO_RUIM = VOTO_RUIM +1 WHERE MATRICULA = ?");
						preparaVotoRuim.setLong(1, matricula);
							if(preparaVotoRuim.executeUpdate() == 1){
								msg =  App.usuarioAtual + " você votou - obrigado";

							}else{
								msg = App.usuarioAtual + " algo deu errado...";

							}
						break;
					default:
						msg = App.usuarioAtual + " você não está sabendo utilizar nosso sistema...";

				}
					
				return msg;
		}finally{
			if(conn != null){
				conn.close();
			}
		}
		
	}
	
	public static String inserirProfessor(ProfessorConsole professor) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		Connection conn = null;
		String msg;
		try{
			conn = DBClass.getConnection();
			ResultSet verificaMatricula = DBClass.getResultSet(conn, "SELECT MATRICULA FROM TBL_PROFESSORES WHERE MATRICULA = " + professor.Matricula);
				if(verificaMatricula.next()){
					msg = "Desculpe mas já existe professor com esta matricula !";
					return msg;
				}
			PreparedStatement preparaRegistro = DBClass.getPreparedStatement(conn, "INSERT INTO TBL_PROFESSORES (MATRICULA,NOME,ENDERECO,IDADE,MATERIA,VOTO_BOM,VOTO_RUIM) VALUES (?, ?, ?, ?, ?, ?, ?)");
			preparaRegistro.setLong(1, professor.Matricula);
			preparaRegistro.setString(2, professor.Nome);
			preparaRegistro.setString(3, professor.Endereco);
			preparaRegistro.setInt(4, professor.Idade);
			preparaRegistro.setString(5, professor.Materia);
			preparaRegistro.setInt(6, 0);
			preparaRegistro.setInt(7, 0);	
			
				if(preparaRegistro.executeUpdate() != 0){
					msg = App.usuarioAtual + " o registro foi inserido com sucesso !!!";
				}else{
					msg = "Ops! Moiooo!! Algo deu errado " + App.usuarioAtual;
				}	
				
		}finally{
			if(conn != null){
				conn.close();
			}
		}
		return msg;
	}
	
	public static String atualizaProfessor(ProfessorConsole professor, int Matricula) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		Connection conn = null;
		String msg;
		try{
			conn = DBClass.getConnection();
			ResultSet verificaMatricula = DBClass.getResultSet(conn, "SELECT MATRICULA FROM TBL_PROFESSORES WHERE MATRICULA = " + Matricula);
			if(!verificaMatricula.next()){
				msg = "Desculpe mas esta Matricula não existe!";
				return msg;
			}
			PreparedStatement preparaUpdate = DBClass.getPreparedStatement(conn, "UPDATE TBL_PROFESSORES SET NOME = ?, ENDERECO = ?, IDADE = ?, MATERIA = ? WHERE MATRICULA = ?");
			preparaUpdate.setString(1, professor.Nome);
			preparaUpdate.setString(2, professor.Endereco);
			preparaUpdate.setInt(3, professor.Idade);
			preparaUpdate.setString(4, professor.Materia);
			preparaUpdate.setLong(5, Matricula);
				if(preparaUpdate.executeUpdate() != 0){
					msg = App.usuarioAtual + " o registro foi atualizado com sucesso !!";
					
				}else{
					msg = "Ops!! Moiooo!!! algo não deu certo " + App.usuarioAtual;
					
				}
		}finally{
			if(conn != null){
				conn.close();
			}
		}
		return msg;
	}
	
	public static String excluiProfessor(int Matricula) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		Connection conn = null;
		String msg;
		try{
			conn = DBClass.getConnection();
			ResultSet verificaExiste = DBClass.getResultSet(conn, "SELECT MATRICULA FROM TBL_PROFESSORES WHERE MATRICULA = " + Matricula);
				if(!verificaExiste.next()){
					msg = "Não existe professor com está matricula!!";
					return msg;
				}
			PreparedStatement preparaExcluir = DBClass.getPreparedStatement(conn, "DELETE FROM TBL_PROFESSORES WHERE MATRICULA = ?");
			preparaExcluir.setInt(1, Matricula);
				if(preparaExcluir.executeUpdate() != 0){
					msg= App.usuarioAtual + " o professor excluido com sucesso !!!";
				}else{
					msg = "Ops! Moiooo! a matricula que você digitou estava certa ?";
				}
		}finally{
			if(conn != null){
				conn.close();
			}
		}
		return msg;
	}
	
	public static List<ProfessorConsole>  buscaProfessor(String Nome) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		Connection conn = null;
		List<ProfessorConsole> lista = new ArrayList<>();
		try{
			
			
			conn = DBClass.getConnection();
			//ResultSet exibindoBusca = DBClass.getResultSet(conn, "SELECT * FROM TBL_PROFESSORES WHERE NOME = " + "'" + Nome + "'");
			ResultSet exibindoBusca = DBClass.getResultSet(conn, "SELECT * FROM TBL_PROFESSORES WHERE NOME LIKE '%" + Nome + "%'");
			while(exibindoBusca.next()){
				
				ProfessorConsole professorConsole = new ProfessorConsole();
				
				professorConsole.Matricula = exibindoBusca.getLong("MATRICULA");
				professorConsole.Nome = exibindoBusca.getString("NOME");
				professorConsole.Endereco = exibindoBusca.getString("ENDERECO");
				professorConsole.Idade = exibindoBusca.getInt("IDADE");
				professorConsole.Materia = exibindoBusca.getString("MATERIA");
				professorConsole.votoBom = exibindoBusca.getInt("VOTO_BOM");
				professorConsole.votoRuim = exibindoBusca.getInt("VOTO_RUIM");
				
				lista.add(professorConsole);

			}
		}finally{
			if(conn != null){
				conn.close();
			}
		}
		return lista;
	}
	
	public static List<ProfessorConsole> listaTodos() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		Connection conn = null;
		List<ProfessorConsole> lista = new ArrayList<>();
		try{
			conn = DBClass.getConnection();
			ResultSet resultado = DBClass.getResultSet(conn, "SELECT * FROM TBL_PROFESSORES");
			
			while(resultado.next()){
				ProfessorConsole professorConsole = new ProfessorConsole();
				
				professorConsole.Matricula = resultado.getLong("MATRICULA");
				professorConsole.Nome = resultado.getString("NOME");
				professorConsole.Endereco = resultado.getString("ENDERECO");
				professorConsole.Idade = resultado.getInt("IDADE");
				professorConsole.Materia = resultado.getString("MATERIA");
				
				lista.add(professorConsole);

			}
			return lista;
		}finally{
			if(conn != null){
				conn.close();
			}
		}
		
	}
	
	public static List<ProfessorConsole> rankingProfessores(String filtro) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		Connection conn = null;
		List<ProfessorConsole> lista = new ArrayList<>();
		try{
			conn = DBClass.getConnection();
			ResultSet resultado = DBClass.getResultSet(conn, "SELECT * FROM TBL_PROFESSORES ORDER BY " + filtro + " DESC");
				
			while(resultado.next()){
				ProfessorConsole professorConsole = new ProfessorConsole();
				
				professorConsole.Matricula = resultado.getLong("MATRICULA");
				professorConsole.Nome = resultado.getString("NOME");
				professorConsole.Endereco = resultado.getString("ENDERECO");
				professorConsole.Idade = resultado.getInt("IDADE");
				professorConsole.Materia = resultado.getString("MATERIA");
				professorConsole.votoBom = resultado.getInt("VOTO_BOM");
				professorConsole.votoRuim = resultado.getInt("VOTO_RUIM");
				lista.add(professorConsole);
				
			}
			return lista;
		}finally{
			if(conn != null){
				conn.close();
			}
		}
	}

}
