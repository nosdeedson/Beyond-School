package br.com.edson.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.swing.JOptionPane;

import br.com.edson.Model.Aluno;
import br.com.edson.Model.Avaliacao;
import br.com.edson.Model.Bimestre;
import br.com.edson.repository.AlunosBD;
import br.com.edson.repository.AvaliacoesBD;
import br.com.edson.repository.BimestresBD;

public class RegistrarAvaliacao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Bimestre bimestre;

	@Inject
	private BimestresBD bimestresBD;
	
	@Inject
	private AvaliacoesBD avaliacoesBD;
	
	@Inject
	private AlunosBD alunosBD;
	
	@Inject
	public RegistrarAvaliacao() { 	}
	
	public void salvarAvaliacao(Avaliacao avaliacao) throws NegocioException {
		
		try {
			if (bimestre == null) {
				throw new NegocioException("Falha ao registrar avaliação. Tente novamente.");
			}
			bimestre = bimestresBD.buscarBimestreAtual();
			avaliacao.setBimestre(bimestre);
			avaliacoesBD.salvarAvaliacao(avaliacao);
		} catch ( PersistenceException | NegocioException e) {
			throw new NegocioException("Falha ao registrar avaliação. Tente novamente.");
		}	
	}
	
	public List<Aluno> buscarAlunosSemAvaliacao(String codigoTurma) {
		List<Avaliacao> avaliacoes = avaliacoesBD.buscarAvaliacoes();
		List<Aluno> alunos = alunosBD.buscaAlunosTurma(codigoTurma);
		for (int i = 0; i < avaliacoes.size(); i++) {
			for (int j = 0; j < alunos.size(); j++) {
				if(alunos.get(j).getIdPessoa() == avaliacoes.get(i).getAluno().getIdPessoa()) {
					alunos.remove(j);
					j=0;
				}
			}
		}
		return alunos;
	}
	

}
