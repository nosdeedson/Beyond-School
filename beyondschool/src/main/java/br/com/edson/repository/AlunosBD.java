package br.com.edson.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.edson.Model.Aluno;
import br.com.edson.Model.Avaliacao;
import br.com.edson.service.NegocioException;

public class AlunosBD implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;
	
	@Inject
	private UsuariosBD usersBD;
	
	@Inject
	public AlunosBD() {}
	
	public void atualizaAluno( Aluno aluno) {
		this.em.merge(aluno);
	}
	
	/**
	 * busca os alunos relacionados com uma turma especifica.
	 * @param codigoTurma
	 * @return
	 */
	public List<Aluno> buscaAlunosTurma(String codigoTurma){
		
		try { 					  
			
			String sql = " SELECT a FROM Aluno a WHERE a.turma.codigoTurma= :codigoTurma and  a NOT IN "
					+ " (SELECT ava.aluno FROM Avaliacao ava JOIN ava.aluno a1 "
					+ " where a1.turma.codigoTurma= :codigoTurma )";
			TypedQuery<Aluno> alunos = this.em.createQuery(sql, Aluno.class)
					.setParameter("codigoTurma", codigoTurma);
			
			return alunos.getResultList();
			
		} catch (PersistenceException | IllegalArgumentException e) {
			return new ArrayList<Aluno>();
		}		
	}
		
	
	/**
	 * retorna todos os alunos
	 * @return
	 */
	public List<Aluno> buscaAlunos(){
		try {
			TypedQuery<Aluno> alunos = this.em.createQuery("from Aluno a where a.deletado= 0 order by a.nomeCompleto", Aluno.class);
			return alunos.getResultList();
		} catch (PersistenceException e) {
			return new ArrayList<Aluno>();
		}

	}
	
	/**
	 * retorna aluno pelo nome usado no cadastro para ver se o aluno já é cadastrado 
	 * pois o mesmo pode ter sido cadastrado no momento que o resposável se cadastrou, 
	 * visto que o responsável fez o cadastro primeiro 
	 * @param nome
	 * @return
	 */
	public Aluno buscaAlunoPeloNome( String nome) {
		Aluno aluno = null;
		try {
			String sql = "select a from Aluno a where nomeCompleto = :nomeCompleto";
			aluno = this.em.createQuery(sql, Aluno.class).setParameter("nomeCompleto", nome).getSingleResult();
		} catch (PersistenceException e) {
			return null;
		}
		return aluno;
	}
	
	
	public List<Aluno> buscaAlunoPorResponsavel(Long idResponsavel){
		String sql = "select a from Aluno a, AlunoResponsavel ar where a.idPessoa = ar.aluno.idPessoa "
				+ " and ar.responsavel.idPessoa= :idPessoa order by a.nomeCompleto";
		
		try {
			TypedQuery<Aluno> alunos = this.em.createQuery(sql, Aluno.class).setParameter("idPessoa", idResponsavel);
			return alunos.getResultList();
		} catch ( PersistenceException e) {
			e.printStackTrace();
			return null;
		}

	}
	
	/**
	 * pega a ultima matricula tirar ao terminar de criar
	 * @return
	 */
	public Integer buscaMatricula() {
		
		String sql = " select max(a.matricula) from Aluno a";
		Integer mat = 1;
		
		try {
			mat = this.em.createQuery(sql, Integer.class).getSingleResult();
			return mat;
		} catch ( PersistenceException e) {
			return null;
		}
		
	}
	
	public void salvarAluno(Aluno aluno) throws NegocioException, PersistenceException {
		this.em.persist(aluno);
	}
	
	/**
	 * método para salvar aluno quando o responsável faz o cadastro primeiro apenas o nome é necessário
	 * @param aluno
	 */
	public void salvarAlunosRespCadPrimerio( Aluno aluno) {
		this.em.merge(aluno);
	}

	/**
	 * busca aluno pela idPessoa
	 * @param id
	 * @return
	 */
	public Aluno porId(Long id) {
		return this.em.find(Aluno.class, id);
	}

	/**
	 * primeiro exclui o usuário depois seta o atributo deletado para true possibilitando reativar o aluno
	 * não remove a pessoa em si do banco 
	 * @param alunoSerExcluido
	 * @throws NegocioException
	 */
	public void excluirAluno(Aluno alunoSerExcluido) throws NegocioException{
			try {
				
				alunoSerExcluido = this.em.find(Aluno.class, alunoSerExcluido.getIdPessoa());
				
				
				int res = usersBD.excluirUser(alunoSerExcluido.getIdPessoa());
				
				if(res == 0) {
					throw new NegocioException("Falha ao excluir o aluno. Tente novamente.");
				}
				alunoSerExcluido.setDeletado(true);
				this.em.merge(alunoSerExcluido);
			} catch (PersistenceException e) {
				e.printStackTrace();
				throw new NegocioException("Falha ao excluir aluno. Tente novamente.");
			}
			
	}
	
	public int tranferirAluno( Aluno aluno, String codigoTurma) throws NegocioException {
		
		String sql = "update Aluno a set a.turma.codigoTurma= :codigoTurma where a.idPessoa= :idPessoa";
		Long id = aluno.getIdPessoa();
		try {
			int a = this.em.createQuery(sql)
					.setParameter("codigoTurma", codigoTurma)
					.setParameter("idPessoa", id).executeUpdate();
			return a;
		} catch ( PersistenceException e) {
			throw new NegocioException("Falha ao transferir aluno.");
		}
		
		
	}
	
	
	
	
	
	
	
	
	
}
