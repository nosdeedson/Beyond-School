package br.com.edson.repository;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.edson.Model.Funcionario;

public class FuncionariosBD implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private EntityManager em;

	@Inject
	public FuncionariosBD(EntityManager em) {
		this.em = em;
	}

	/**
	 * método usado para persistir professor que é cradastrado na hora que se cadastra turma
	 * primeiro verifica se o funcionario já existe no banco
	 * se sim retorna
	 * @param funcionario
	 * @return
	 */
	public Long salvarFuncionario( Funcionario funcionario) {
		
		this.em.merge(funcionario);
		
		String sql = "select max(idPessoa) from Funcionario";
		
		Long query = this.em.createQuery(sql, Long.class).getSingleResult();
		
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
