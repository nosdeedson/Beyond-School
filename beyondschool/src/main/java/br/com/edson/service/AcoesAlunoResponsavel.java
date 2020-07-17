package br.com.edson.service;

import java.io.Serializable;
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


	public void excluiResponsavel( Responsavel responsavel) throws NegocioException, Exception {
				
		List<AlunoResponsavel> alunosDoResponsavel = alunosRespsBD.buscaAlunosPorResponsavel(responsavel.getIdPessoa());
		
		for (int i = 0; i < alunosDoResponsavel.size(); i++) {
			List<AlunoResponsavel> responsaveisDoAluno = alunosRespsBD.buscaResponsavelPorAluno(alunosDoResponsavel.get(i).getAluno().getIdPessoa());
			if( responsaveisDoAluno.size() == 1)
				throw new NegocioException("O aluno deve ter ao menos um responsável. Caso vá excluir o aluno, exclua este primeiro ou click em ambos.");
			if (alunosDoResponsavel.get(i).getAluno().isDeletado()) {
				respBD.removeResponsavel(responsavel.getIdPessoa());
				alunosRespsBD.excluirAlunoResponsavel(alunosDoResponsavel.get(i).getResponsavel().getIdPessoa());
			}
			else {
				respBD.excluirResponsavel(responsavel.getIdPessoa());
			}
		}
		
	}


	public void excluirAluno(Aluno alunoSerExcluido) throws Exception {
		List<AlunoResponsavel> responsaveisPorAluno = alunosRespsBD.buscaResponsavelPorAluno(alunoSerExcluido.getIdPessoa());
		
		JOptionPane.showMessageDialog(null, "aluno");
		for (int i = 0; i < responsaveisPorAluno.size(); i++) {
			
			List<AlunoResponsavel> alunosDoResp = alunosRespsBD.buscaAlunosPorResponsavel(responsaveisPorAluno.get(i).getResponsavel().getIdPessoa());
			
			if (responsaveisPorAluno.get(i).getResponsavel().isDeletado() && alunosDoResp.size() == 1 ) {
				alunosBD.excluirAluno(alunoSerExcluido);
				respBD.removeResponsavel(responsaveisPorAluno.get(i).getResponsavel().getIdPessoa());
				alunosRespsBD.excluirAlunoResponsavel(responsaveisPorAluno.get(i).getResponsavel().getIdPessoa());
			}
			else if( responsaveisPorAluno.get(i).getResponsavel().isDeletado() == false && alunosDoResp.size() == 1 ) {
				
				alunosBD.excluirAluno(alunoSerExcluido);
				respBD.removeResponsavel(responsaveisPorAluno.get(i).getResponsavel().getIdPessoa());
				alunosRespsBD.excluirAlunoResponsavel(responsaveisPorAluno.get(i).getResponsavel().getIdPessoa());
				
			}
			else if( responsaveisPorAluno.get(i).getResponsavel().isDeletado() && alunosDoResp.size() > 1 ) {
				alunosBD.excluirAluno(alunoSerExcluido);
			}
			else if( responsaveisPorAluno.get(i).getResponsavel().isDeletado() == false && alunosDoResp.size() > 1 ) {
				alunosBD.excluirAluno(alunoSerExcluido);
				respBD.excluirResponsavel(responsaveisPorAluno.get(i).getResponsavel().getIdPessoa());
			}
			
			
		}// fim for
		
	}

}
