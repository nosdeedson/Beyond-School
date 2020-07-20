package br.com.edson.repository;

import java.io.Serializable;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import br.com.edson.Model.Aluno;
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
		
		String sql = "select ar from AlunoResponsavel ar where ar.aluno.turma.codigoTurma= :codigoTurma and ar.aluno.deletado = false or ar.responsavel.deletado = false"
				+ " order by ar.responsavel.nomeCompleto";
		
		try {
			TypedQuery<AlunoResponsavel> alunosResps = this.em.createQuery(sql, AlunoResponsavel.class).setParameter("codigoTurma", codigoTurma);
			return alunosResps.getResultList();
		} catch ( PersistenceException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * retorna os alunos do responsável
	 * @param idResponsavel
	 * @return
	 */
	public List<AlunoResponsavel> buscaAlunosPorResponsavel( Long idResponsavel){
		
		String sql = "select ar from AlunoResponsavel ar where ar.responsavel.idPessoa= :idPessoa "
				+ "and ar.aluno.deletado= 0";
		try {
			TypedQuery<AlunoResponsavel> alunos = this.em.createQuery(sql, AlunoResponsavel.class)
					.setParameter("idPessoa", idResponsavel);
			return alunos.getResultList();
		} catch (PersistenceException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * retorna os responsaveis do aluno
	 * @param idPessoa
	 * @return
	 */
	public List<AlunoResponsavel> buscaResponsavelPorAluno(Long idAluno) {
		String sql = "select ar from AlunoResponsavel ar where ar.aluno.idPessoa= :idPessoa";
		try {
			TypedQuery<AlunoResponsavel> alunos = this.em.createQuery(sql, AlunoResponsavel.class)
					.setParameter("idPessoa", idAluno);
			return alunos.getResultList();
		} catch (PersistenceException e) {
			e.printStackTrace();
			return new ArrayList<AlunoResponsavel>();
		}
	}
	
//	public AlunoResponsavel existeAlunoResponsavel(Long idAluno, Long idResponsavel) {
//		
//		AlunoResponsavel alunoResp = null;
//		
//		String sql = "select ar from AlunoResponsavel ar where ar.aluno.idPessoa= :idPessoaAluno "
//				+ " and ar.responsavel.idPessoa= :idPessoaResponsavel";
//		
//		alunoResp = this.em.createQuery(sql, AlunoResponsavel.class).setParameter("idPessoaAluno", idAluno)
//				.setParameter("idPessoaResponsavel", idResponsavel).getSingleResult();
//		if( alunoResp != null )
//			return alunoResp;
//		
//		return alunoResp;
//	}
	
	/**
	 * deletada do banco de dados
	 * @param idAlunoResponsavel
	 * @throws NegocioException
	 * @throws Exception
	 */
	public void excluirAlunoResponsavel( Long idAlunoResponsavel) throws NegocioException, Exception {
		AlunoResponsavel ar = this.em.find(AlunoResponsavel.class,idAlunoResponsavel);
		try {
			this.em.remove(ar);
		} catch (Exception e) {
			throw new NegocioException("Falha ao excluir responsável");
		}
		
	}
	
	public void salvarAlunoResponsavel( AlunoResponsavel alunoResponsavel) throws PersistenceException, NegocioException {
		
		try {
			this.em.persist(alunoResponsavel);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new NegocioException(e.getMessage());
		}
		
	}

		

}
