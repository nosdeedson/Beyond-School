package br.com.edson.manageBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.swing.JOptionPane;

import br.com.edson.Model.Aluno;
import br.com.edson.Model.AlunoResponsavel;
import br.com.edson.Model.Responsavel;
import br.com.edson.Model.Turma;
import br.com.edson.repository.AlunosBD;
import br.com.edson.repository.AlunosResponsaveisBD;
import br.com.edson.repository.ResponsaveisBD;
import br.com.edson.service.NegocioException;

@Named
@javax.faces.view.ViewScoped
public class PaisAlunosMBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<AlunoResponsavel> alunosResponsaveis = new ArrayList<AlunoResponsavel>();
	
	@Inject
	private Aluno alunoSerExcluido;

	@Inject
	private AlunosBD alunosBD;
	
	@Inject
	private Responsavel responsavelSerExcluido;
	
	@Inject
	private ResponsaveisBD responsaveisBD;
	
	@Inject
	private AlunoResponsavel alunoResponsavelSerExcluido;
	
	@Inject
	private AlunosResponsaveisBD alunosRespBD;
	
	@Inject
	private Turma turma;
	
	@Inject
	private EntityManager em;
	
	public void buscarAlunosResponsaveis() {
		alunosResponsaveis = alunosRespBD.buscarAlunoResponsaveis(turma.getCodigoTurma());
	}
	
	public void excluirResponsavel() throws Exception {
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		EntityTransaction et = this.em.getTransaction();
		 
		try {
			et.begin();
				alunosRespBD.excluirAlunoResponsavel(alunoResponsavelSerExcluido.getId_aluno_responsavel());
				responsaveisBD.excluirResponsavel(responsavelSerExcluido.getIdPessoa());
			et.commit();
			context.addMessage(null, new FacesMessage("Excluído com sucesso"));
		} catch (PersistenceException | NegocioException e) {
			et.rollback();
			FacesMessage msg = new FacesMessage("Falha na exclusão");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, msg);
		}
		
	}
	
	public void excluirAluno() {
		JOptionPane.showMessageDialog(null, alunoSerExcluido.getNomeCompleto());
	}
	
	public void ambos() {
		JOptionPane.showMessageDialog(null, responsavelSerExcluido.getNomeCompleto());
		JOptionPane.showMessageDialog(null, alunoSerExcluido.getNomeCompleto());
	}
	
	// getters and setters
	public List<AlunoResponsavel> getAlunosResponsaveis() {
		return alunosResponsaveis;
	}

	public void setAlunosResponsaveis(List<AlunoResponsavel> alunosResponsaveis) {
		this.alunosResponsaveis = alunosResponsaveis;
	}

	public Aluno getAlunoSerExcluido() {
		return alunoSerExcluido;
	}

	public void setAlunoSerExcluido(Aluno alunoSerExcluido) {
		this.alunoSerExcluido = alunoSerExcluido;
	}

	public Responsavel getResponsavelSerExcluido() {
		return responsavelSerExcluido;
	}

	public void setResponsavelSerExcluido(Responsavel responsavelSerExcluido) {
		this.responsavelSerExcluido = responsavelSerExcluido;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public AlunoResponsavel getAlunoResponsavelSerExcluido() {
		return alunoResponsavelSerExcluido;
	}

	public void setAlunoResponsavelSerExcluido(AlunoResponsavel alunoResponsavelSerExcluido) {
		this.alunoResponsavelSerExcluido = alunoResponsavelSerExcluido;
	}
	
	
	
	

}
