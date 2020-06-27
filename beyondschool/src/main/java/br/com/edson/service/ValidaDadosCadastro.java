package br.com.edson.service;

import java.io.Serializable;

import javax.inject.Inject;
import javax.swing.JOptionPane;

import br.com.edson.Model.Usuario;
import br.com.edson.repository.TurmasBD;
import br.com.edson.repository.UsuariosBD;

public class ValidaDadosCadastro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private TurmasBD turmasBD;
	
	@Inject
	private UsuariosBD usersBD;
	
	@Inject
	public ValidaDadosCadastro() {}
	
	/**
	 * verifica se o codigo informado pelo usuário existe
	 * @param codigo
	 * @throws NegocioException
	 */
	public boolean validarCodigo( String codigo) throws NegocioException {
				
		boolean flag = turmasBD.verificaCodigoGerado(codigo);
		if(!flag) {
			throw new NegocioException("Código inválido!! Digite novamente.");
		}
		return flag;
	}
	
	/**
	 * verifica se as senhas digitadas são iguais
	 * @param senha
	 * @param confirmeSenha
	 * @throws NegocioException
	 */
	public void verificaSenha(String senha, String confirmeSenha)throws NegocioException{
		
		if(!senha.equals(confirmeSenha)) {
			throw new NegocioException("Senhas não conferem!! Digite novamente.");
		}
	}
	
	/**
	 * cria o nome de usuário com o primeiro e o sobrenome unidos por ponto
	 * @param nome
	 * @return
	 */
	public String criaNomeUsuario(String nome) throws NegocioException{
		
		Long idUsuario = usersBD.buncaUltimoUser();
		
		int primeiroEspaco = nome.indexOf(" ");
		if(primeiroEspaco == -1)
			throw new NegocioException("Informe o nome e sobrenome.");
		int ultimoEspaco = nome.lastIndexOf(" ");
		
		int tm = nome.length();
		
		String nomeUser = nome.substring(0, primeiroEspaco);
		
		nomeUser += ".";
		
		nomeUser += nome.substring(ultimoEspaco+1, tm);
		
		nomeUser += (idUsuario+1);
		
		return nomeUser;
	}

}
