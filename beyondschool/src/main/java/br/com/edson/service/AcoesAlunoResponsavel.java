package br.com.edson.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;

import br.com.edson.Model.Aluno;
import br.com.edson.Model.AlunoResponsavel;
import br.com.edson.Model.Responsavel;
import br.com.edson.repository.AlunosBD;
import br.com.edson.repository.AlunosResponsaveisBD;
import br.com.edson.repository.ResponsaveisBD;

public class AcoesAlunoResponsavel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;
	
	@Inject
	private AlunosResponsaveisBD alunosRespsBD;
	
	@Inject
	private ResponsaveisBD respBD;
	
	@Inject
	private AlunosBD alunosBD;
	
	
	public AcoesAlunoResponsavel() {	}


	public void excluirAluno(Aluno alunoSerExcluido) throws Exception {
		
		alunosBD.excluirAluno(alunoSerExcluido);
		
	}
	
	public void excluiResponsavel( Responsavel responsavel, AlunoResponsavel ar) throws NegocioException, Exception {
		// excluir responsavel sem aluno
		List<AlunoResponsavel> alunosDoResponsavel = alunosRespsBD.buscaAlunosPorResponsavel(responsavel.getIdPessoa());
				
		if( alunosDoResponsavel.size() == 0) {
			respBD.removeResponsavel(responsavel.getIdPessoa());
			alunosRespsBD.excluirAlunoResponsavel(ar.getId_aluno_responsavel());
		}
		else if( alunosDoResponsavel.size() > 0 ) {
			
			List<AlunoResponsavel> responsaveisDosAluno = alunosRespsBD.buscaResponsavelPorAluno(alunosDoResponsavel.get(0).getAluno().getIdPessoa());
			//JOptionPane.showMessageDialog(null, "mais de um aluno." + responsaveisDosAluno.size());
			if(responsaveisDosAluno.size() == 1) {
				throw new NegocioException("Você não pode excluir todos os responsaveis de: "+ ar.getAluno().getNomeCompleto());
			}
			else {
				alunosRespsBD.excluirAlunoResponsavel(ar.getId_aluno_responsavel());
				respBD.removeResponsavel(responsavel.getIdPessoa());
				
			}
		}
		
	}
	
	public void excluirAlunoResponsavel( Aluno aluno, Responsavel responsavel, AlunoResponsavel ar) throws NegocioException, Exception{
				
		List<AlunoResponsavel> alunosDoResponsavel = alunosRespsBD.buscaAlunosPorResponsavel(responsavel.getIdPessoa());
		
		List<AlunoResponsavel> responsaveisDosAlunos = null;
		
		if(alunosDoResponsavel.size() == 0) {
			alunosRespsBD.excluirAlunoResponsavel(ar.getId_aluno_responsavel());
			respBD.removeResponsavel(responsavel.getIdPessoa());
		}
		else if(alunosDoResponsavel.size() == 1 ) {
			alunosRespsBD.excluirAlunoResponsavel(ar.getId_aluno_responsavel());
			respBD.removeResponsavel(responsavel.getIdPessoa());
			excluirAluno(aluno);
				
		}
		else if( alunosDoResponsavel.size() > 1) {
			responsaveisDosAlunos = alunosRespsBD.buscaResponsavelPorAluno(alunosDoResponsavel.get(0).getAluno().getIdPessoa());
			if( responsaveisDosAlunos.size() == 0 ) {
				excluirAluno(aluno);
			}
			else if( responsaveisDosAlunos.size() == 1) {
				throw new NegocioException("Você não pode excluir este responsável: "+ responsavel.getNomeCompleto() + " ele tem outro aluno matriculado.");
			}
			else {
				alunosRespsBD.excluirAlunoResponsavel(ar.getId_aluno_responsavel());
				respBD.removeResponsavel(responsavel.getIdPessoa());
				excluirAluno(aluno);
			}
		}

	}

}
