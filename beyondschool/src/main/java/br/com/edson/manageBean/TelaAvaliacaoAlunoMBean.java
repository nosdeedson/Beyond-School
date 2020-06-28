package br.com.edson.manageBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlOutputLabel;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.swing.JOptionPane;

import br.com.edson.Model.Aluno;
import br.com.edson.Model.Avaliacao;
import br.com.edson.Model.ConceitoEnum;
import br.com.edson.Model.Turma;
import br.com.edson.repository.AlunosBD;
import br.com.edson.repository.AvaliacoesBD;
import br.com.edson.service.NegocioException;
import br.com.edson.service.RegistrarAvaliacao;


@Named
@javax.faces.view.ViewScoped
public class TelaAvaliacaoAlunoMBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Aluno aluno;
		
	private List<Aluno> alunos = new ArrayList<Aluno>();
	
	@Inject
	private Avaliacao avaliacao;
		
	private String comentario;
	
	@Inject
	private Turma turma;
	
	@Inject
	private EntityManager em;
	
	@Inject
	private RegistrarAvaliacao registra;

	private HtmlOutputLabel outPutLabelAluno = new HtmlOutputLabel();
	// métodos bs9op84o
	
	public void alunosAvaliados() {
		if(alunos.size() == 0)
			this.outPutLabelAluno.setValue("SEM ALUNOS PARA AVALIAR.");
	}
	
	public void buscarAlunos() throws NegocioException {
		alunos = registra.buscarAlunosSemAvaliacao(turma.getCodigoTurma());
		if(alunos.size() > 0) {
			aluno = alunos.get(0);
		}
		
	}
	
	public String avaliar() throws NegocioException {
		FacesContext context = FacesContext.getCurrentInstance();
		EntityTransaction et = em.getTransaction();
			
		for (int i = 0; i < alunos.size()  ; i++) {
			try {
				
				List<String> comments = new ArrayList<String>();
				comments.add(comentario);
				avaliacao.setComentarios(comments);
				
				avaliacao.setAluno(aluno);
				JOptionPane.showMessageDialog(null, avaliacao.getEscutando());
							
				et.begin();
				
				registra.salvarAvaliacao(avaliacao);
				
				et.commit();
				
				alunos.remove(i);
				if(alunos.size() > 0)
					aluno = alunos.get(i);
				else
					break;
				
				context.addMessage(null, new FacesMessage("Aluno avaliado com sucesso!!" ));
				
			} catch (PersistenceException | NullPointerException e) {
				et.rollback();
				e.printStackTrace();
				aluno = alunos.get(i);
				i--;
				FacesMessage msg = new FacesMessage(e.getMessage());
				e.printStackTrace();
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				context.addMessage(null, msg);
			}finally {
				setComentario("");
				avaliacao = new Avaliacao();
			}
			
			
		}//fim for
		return "/APP/listaTurmas?faces-redirect=true";
		
	}
	
	/**
	 * método que pego os valores dos enums
	 * @return
	 */
	public ConceitoEnum[] getConceitos() {
		return ConceitoEnum.values();
	}
	
	
	
	//getters and setters
	
	public Aluno getAluno() {
		return aluno;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}
	
	
	
	
	

}
