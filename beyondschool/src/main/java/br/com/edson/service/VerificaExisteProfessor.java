package br.com.edson.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.edson.Model.Funcionario;
import br.com.edson.repository.FuncionariosBD;

public class VerificaExisteProfessor implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@Inject
	public VerificaExisteProfessor() { }


	@Inject
	private FuncionariosBD funcs;
	
	
	public  Funcionario existeProfessor(String nome) {
		List< Funcionario> workers = new ArrayList<Funcionario>();
		
		workers = funcs.buscaFuncionarios();
		
		for (Funcionario funcionario : workers) {
			if(nome.equals(funcionario.getNomeCompleto()))
				return funcionario;
		}
		
		return null;
	}

}
