package br.com.edson.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.swing.JOptionPane;

import br.com.edson.repository.TurmasBD;
import br.com.edson.repository.UsuariosBD;

public class ValidaDadosCadastro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private  TurmasBD turmasBD;
	
	@Inject
	private UsuariosBD usersBD;
	
	@Inject
	public  ValidaDadosCadastro() {}
	
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
	 * cria o nome de usuário com o primeiro e o sobrenome unidos por ponto
	 * @param nome
	 * @return
	 */
	public String criaNomeUsuario(String nome) throws NegocioException{
				
		int primeiroEspaco = nome.indexOf(" ");
		if(primeiroEspaco == -1)
			throw new NegocioException("Informe o nome e sobrenome.");
		int ultimoEspaco = nome.lastIndexOf(" ");
		
		int tm = nome.length();
		
		String nomeUser = nome.substring(0, primeiroEspaco);
		
		nomeUser += ".";
		
		nomeUser += nome.substring(ultimoEspaco+1, tm);
				
		return nomeUser;
	}
	
	public boolean verificaSenha( String passWord) throws NegocioException {
		
		List<String> senhas = new ArrayList<String>();
		
		senhas = usersBD.todasSenhas();
		for (String string : senhas) {
			if(string.equals(passWord))
				throw new NegocioException("Senha existente, digite outra ou altere. ");
		}
		
		return true;
	}

}
