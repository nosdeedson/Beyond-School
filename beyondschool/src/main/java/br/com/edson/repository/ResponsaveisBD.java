package br.com.edson.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.com.edson.Model.Aluno;
import br.com.edson.Model.Responsavel;

public class ResponsaveisBD implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;

	@Inject
	public ResponsaveisBD() {
	}
	
	public List<Responsavel> buscaTodos(){
		TypedQuery<Responsavel> responsaveis = em.createQuery("from Responsavel ", Responsavel.class);
		return responsaveis.getResultList();
	}
	
	public Responsavel buscaResponsavelPeloNome(String nome) {
		Responsavel resp;
		try {
			String sql = "select r from Responsavel r where nomeCompleto = :nomeCompleto";
			resp = this.em.createQuery(sql, Responsavel.class).setParameter("nomeCompleto", nome).getSingleResult();
		} catch (PersistenceException e) {
			return null;
		}
		return resp;
	}
	
	public Long salvarResponsavel( Responsavel responsavel) {
		this.em.merge(responsavel);
		String sql = "select max(idPessoa) from Responsavel";
		
		Long idResp = this.em.createQuery(sql, Long.class).getSingleResult();
		return idResp;
	}
	
	public void salvarResponsavelCadastro(Responsavel responsavel) {
		
		this.em.merge(responsavel);		
		
	}
	
	

}
