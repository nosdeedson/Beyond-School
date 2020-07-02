package br.com.edson.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

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
	 * busca os alunos relacionados com uma turma espeçifica.
	 * @param codigoTurma
	 * @return
	 */
	public List<Aluno> buscaAlunosTurma(String codigoTurma){
		
		try { 
			
			String sql = " select a from Aluno a, Turma t where t.codigoTurma= :codigoTurma and a.deletado = 0";  	
			
			TypedQuery<Aluno> alunos = this.em.createQuery(sql, Aluno.class)
					.setParameter("codigoTurma", codigoTurma);
			return alunos.getResultList();
		} catch (PersistenceException | IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}		
	}
	
	/**
	 * retorna todos os alunos
	 * @return
	 */
	public List<Aluno> buscaAlunos(){
		try {
			TypedQuery<Aluno> alunos = this.em.createQuery("from Aluno a where a.deletado= 0", Aluno.class);
			return alunos.getResultList();
		} catch (PersistenceException e) {
			return null;
		}

	}
	
	/**
	 * retorn aluno pelo nome
	 * @param nome
	 * @return
	 */
	public Aluno buscaAlunoPeloNome( String nome) {
		Aluno aluno;
		try {
			String sql = "select a from Aluno a where nomeCompleto = :nomeCompleto";
			aluno = this.em.createQuery(sql, Aluno.class).setParameter("nomeCompleto", nome).getSingleResult();
		} catch (PersistenceException e) {
			return null;
		}
		return aluno;
	}
	
	/**
	 * pega a ultima matricula tirar ao terminar de criar
	 * @return
	 */
	public Integer buscaMatricula() {
		
		String sql = " select max(a.matricula) from Aluno a";
		Integer mat = new Integer(100);
		
		try {
			mat = this.em.createQuery(sql, Integer.class).getSingleResult();
			return mat;
		} catch ( PersistenceException e) {
			return null;
		}
		
	}
	
	public Long salvarAluno(Aluno aluno) {
		this.em.merge(aluno);
		
		String sql = "select a.idPessoa from Aluno a where nomeCompleto = :nomeCompleto";
		
		String nome = aluno.getNomeCompleto();
		
		Long idAluno = this.em.createQuery(sql, Long.class).setParameter("nomeCompleto", nome).getSingleResult();
		
		return idAluno;
	}
	
	/**
	 * método para salvar aluno quando o responsável faz o cadastro primeiro apenas o nome é necessário
	 * @param aluno
	 */
	public void salvarAlunosRespCadPrimerio( Aluno aluno) {
		this.em.merge(aluno);
	}

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
				int res = usersBD.excluirUser(alunoSerExcluido.getIdPessoa());
				if(res == 0) {
					throw new NegocioException("Falha ao excluir o aluno. Tente novamente.");
				}
				alunoSerExcluido.setDeletado(true);
				this.em.merge(alunoSerExcluido);
			} catch (PersistenceException e) {
				throw new NegocioException("Falha ao excluir aluno. Tente novamente.");
			}
			
	}
}
