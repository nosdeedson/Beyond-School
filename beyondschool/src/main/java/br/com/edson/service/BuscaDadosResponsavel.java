package br.com.edson.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.swing.JOptionPane;

import br.com.edson.Model.Aluno;
import br.com.edson.Model.Responsavel;
import br.com.edson.repository.AlunosBD;
import br.com.edson.repository.ResponsaveisBD;

public class BuscaDadosResponsavel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;
	
	@Inject
	private Responsavel resp;
	
	@Inject
	private ResponsaveisBD respsBD;
	
	private List<Aluno> alunos = new ArrayList<Aluno>();
	
	@Inject
	private AlunosBD alunosBD;
	
	@Inject
	public BuscaDadosResponsavel() {	}



	public List<Aluno> buscaAlunosDoResponsavel( Long idPessoa ) throws NegocioException {
		
		List<Aluno> alunos = null;
		try {
			resp = respsBD.porId(idPessoa);
			if(resp == null)
				throw new NegocioException("Falha ao buscar resp.");
			
			alunos = alunosBD.buscaAlunoPorResponsavel(resp.getIdPessoa());
			return alunos;
			
		} catch ( PersistenceException  e) {
			return null;
		}
	}
}
