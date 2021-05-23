package br.com.edson.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.PersistenceException;

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
	
	
	public List<Aluno> buscarAlunosSemAvaliacao(String codigoTurma) throws NegocioException {
		
		bimestre = bimestresBD.buscarBimestreAtual();
		List<Aluno> alunos = alunosBD.buscaAlunosTurma(codigoTurma);
		return alunos;

	}
	
	public void salvarAvaliacao(Avaliacao avaliacao) throws NegocioException {
		
		try {
			bimestre = bimestresBD.buscarBimestreAtual();
			if (bimestre == null) {
				throw new NegocioException("Falha ao registrar avaliação. Tente novamente.");
			}
			
			avaliacao.setBimestre(bimestre);
			avaliacoesBD.salvarAvaliacao(avaliacao);
		} catch ( PersistenceException | NegocioException e) {
			throw new NegocioException("Falha ao registrar avaliação. Tente novamente.");
		}	
		
	}
	


}
