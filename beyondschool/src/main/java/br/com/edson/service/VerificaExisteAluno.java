package br.com.edson.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.edson.Model.Aluno;
import br.com.edson.repository.AlunosBD;

public class VerificaExisteAluno implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private AlunosBD alunosBD;

	@Inject
	public VerificaExisteAluno(AlunosBD alunosBD) {	}
	
	public Aluno buscaAluno(String nomeCompleto) {
		List<Aluno> alunos = alunosBD.buscaAlunos();
		
		for (Aluno aluno : alunos) {
			if(nomeCompleto.equals(aluno.getNomeCompleto())) {
				return aluno;
			}
		}
		return null;
	}

	
}
