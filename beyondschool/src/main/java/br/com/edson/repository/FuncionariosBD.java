package br.com.edson.repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import org.hibernate.NonUniqueResultException;

import br.com.edson.Model.Funcionario;
import br.com.edson.Model.PapelEnum;
import br.com.edson.service.NegocioException;

public class FuncionariosBD implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;

	@Inject
	public FuncionariosBD() {
	}

	
	/**
	 * usado para atualizar o cadastro do professor usado na hora de cadastrar turma 
	 * @param funcionario
	 */
	public void atualizaFuncionario( Funcionario funcionario) {
		this.em.merge(funcionario);
	}
	
	/**
	 * talvez tirar
	 * @param nome
	 * @return
	 */
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
	
	public Funcionario buscaFuncionarioPeloNomeTipoAcesso(String nome, PapelEnum p) {
		
		Funcionario func = new Funcionario();
		try {
			String sql = "from Funcionario f where f.nomeCompleto = :nomeCompleto and f.tipoAcesso= :tipoAcesso";
			func = this.em.createQuery(sql, Funcionario.class).setParameter("nomeCompleto", nome)
					.setParameter("tipoAcesso", p).getSingleResult();
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
	public void salvarFuncionario( Funcionario funcionario) throws PersistenceException, NegocioException {
		this.em.persist(funcionario);
	}


	public Funcionario porId(Long idPessoa) {
		try {
			return this.em.find(Funcionario.class, idPessoa);
		} catch (PersistenceException e) {
			e.printStackTrace();
			return null;
		}
	}

	
	
}
