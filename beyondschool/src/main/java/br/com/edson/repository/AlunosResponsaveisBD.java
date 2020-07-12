package br.com.edson.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.com.edson.Model.AlunoResponsavel;
import br.com.edson.service.NegocioException;

public class AlunosResponsaveisBD implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;

	@Inject
	public AlunosResponsaveisBD() {	}
	
	/**
	 * todos os alunos e responsáveis relacionados com a turma referente ao código
	 * @param codigoTurma
	 * @return
	 */
	public List<AlunoResponsavel> buscarAlunoResponsaveis( String codigoTurma ) {
		
		String sql = "select ar from AlunoResponsavel ar where ar.aluno.turma.codigoTurma= :codigoTurma order by"
				+ " ar.aluno.idPessoa";
		
		try {
			TypedQuery<AlunoResponsavel> alunosResps = this.em.createQuery(sql, AlunoResponsavel.class).setParameter("codigoTurma", codigoTurma);
			return alunosResps.getResultList();
		} catch ( PersistenceException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public AlunoResponsavel existeAlunoResponsavel(Long idAluno, Long idResponsavel) {
		
		AlunoResponsavel alunoResp = null;
		
		String sql = "select ar from AlunoResponsavel ar where ar.aluno.idPessoa= :idPessoaAluno "
				+ " and ar.responsavel.idPessoa= :idPessoaResponsavel";
		
		alunoResp = this.em.createQuery(sql, AlunoResponsavel.class).setParameter("idPessoaAluno", idAluno)
				.setParameter("idPessoaResponsavel", idResponsavel).getSingleResult();
		if( alunoResp != null )
			return alunoResp;
		
		return alunoResp;
	}
	
	public void excluirAlunoResponsavel( Long idAlunoResponsavel) throws NegocioException, Exception {
		AlunoResponsavel ar = this.em.find(AlunoResponsavel.class, idAlunoResponsavel);
		this.em.remove(ar);
	}
	
	public void salvarAlunoResponsavel( AlunoResponsavel alunoResponsavel) {
		this.em.merge(alunoResponsavel);
	}	

}
