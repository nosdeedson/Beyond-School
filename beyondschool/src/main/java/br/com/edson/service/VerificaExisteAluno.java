package br.com.edson.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.swing.JOptionPane;

import br.com.edson.Model.Aluno;
import br.com.edson.repository.AlunosBD;

public class VerificaExisteAluno implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private AlunosBD alunosBD;

	@Inject
	public VerificaExisteAluno(AlunosBD alunosBD) {	}
	
	public Aluno buscaAluno(String nomeCompleto) {
//		List<Aluno> alunos = alunosBD.buscaAlunos();
//		JOptionPane.showMessageDialog(null, "verifica " +  alunos.size());
//		for (Aluno aluno : alunos) {
//			if(nomeCompleto.equals(aluno.getNomeCompleto())) {
//				JOptionPane.showMessageDialog(null, aluno.getNomeCompleto());
//				return aluno;
//			}
//		}
//		return null;
		
		return alunosBD.buscaAlunoPeloNome(nomeCompleto);
	}

	
}
