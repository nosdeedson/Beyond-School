package br.com.edson.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.com.edson.Model.Funcionario;

public class FuncionariosBD implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;

	@Inject
	public FuncionariosBD() {
	}

	public Funcionario buscaFuncionarioPeloNome(String nome) {
		
		Funcionario func = new Funcionario();
		try {
			String sql = "from Funcionario f where f.nomeCompleto = :nomeCompleto";
			func = this.em.createQuery(sql, Funcionario.class).setParameter("nomeCompleto", nome).getSingleResult();
		} catch (PersistenceException  e) {
			return null;
		}
		return func;
		
	}
	
	public List<Funcionario> buscaFuncionarios() {
		
		TypedQuery<Funcionario> funcionarios = em.createQuery("from Funcionario", Funcionario.class);
		
		return funcionarios.getResultList();
	}
	
	
	
	/**
	 * método usado para persistir professor ou admin que é cradastrado na hora que se cadastra turma
	 * primeiro verifica se o funcionario já existe no banco
	 * se sim retorna
	 * @param funcionario
	 * @return
	 */
	public Long salvarFuncionario( Funcionario funcionario) {
		this.em.merge(funcionario);
		
		String sql = "select idPessoa from Funcionario f where f.nomeCompleto = :nomeCompleto";
		
		Long query = this.em.createQuery(sql, Long.class).setParameter("nomeCompleto", funcionario.getNomeCompleto()).getSingleResult();
		
		return query;
	}
	
	/**
	 * usado para atualizar o cadastro do professor usado na hora de cadastrar turma 
	 * @param funcionario
	 */
	public void salvarFuncionarioCadastro( Funcionario funcionario) {
		this.em.merge(funcionario);
	}
	
	
}
