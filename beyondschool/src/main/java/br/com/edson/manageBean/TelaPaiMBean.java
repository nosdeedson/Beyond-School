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
import br.com.edson.Model.Avaliacao;
import br.com.edson.Model.Turma;
import br.com.edson.Model.Usuario;
import br.com.edson.repository.AlunosBD;
import br.com.edson.repository.AvaliacoesBD;
import br.com.edson.repository.TurmasBD;
import br.com.edson.repository.UsuariosBD;
import br.com.edson.service.BuscaDadosResponsavel;
import br.com.edson.service.NegocioException;

@Named
@javax.faces.view.ViewScoped
public class TelaPaiMBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Turma turma;
	
	@Inject
	private TurmasBD turmasBD;
	
	@Inject
	private Usuario user;
	
	@Inject
	private UsuariosBD usersBD;
	
	@Inject
	private Avaliacao avaliacao;
	
	@Inject
	private AvaliacoesBD avaliacoesBD;
	
	@Inject
	private Aluno aluno;
	
	private List<Aluno> alunos = new ArrayList<Aluno>();
	
	@Inject
	private AlunosBD alunosBD;
	
	private List<String> comentarios = new ArrayList<String>();
	
	private String[] comentario = new String[comentarios.size()];
	
	private String comentarioResponsavel;
	
	private boolean flagCommentAluno = false;
	
	private boolean flagCommentPai = false;
	
	@Inject
	private BuscaDadosResponsavel buscaResp;
	
	@Inject
	private EntityManager em;

	//métodos
	public void buscarUser() {
		user = usersBD.ValidaUsuarioLogin("joao.silva4", "123123123");
	}
	
	public void buscarAlunos() throws NegocioException {
		alunos = buscaResp.buscaAlunosDoResponsavel(user.getPessoa().getIdPessoa());	
	}
	
	public void buscarTurma() {
		turma = turmasBD.buscaTurma(alunos.get(0).getIdPessoa());
		aluno = alunos.get(0);
		avaliacao = avaliacoesBD.buscaPorIdAluno(aluno.getIdPessoa());
	}
	
	public void buscarComentarios() {
		// talvez não precise deste metodo com session tente com session
		JOptionPane.showMessageDialog(null, "comment");
			Avaliacao ava = avaliacoesBD.buscaComentarios(avaliacao.getIdAvaliacao());
			if(comentarios.size() == 0)
				comentarios.add("Sem comentarios");
			for ( int i = 0; i < comentarios.size(); i++) {
				if(ava.getComentarios().get(i) == null)
				comentario[i] = ava.getComentarios().get(i);
				JOptionPane.showMessageDialog(null, comentario[i]);
				if( i ==  1) {
					flagCommentAluno = true;
				}
				if( i ==  2) {
					flagCommentPai = true;
				}
			}
			
		}
	
	public void salvarComentarioAluno() throws NegocioException {
		FacesContext context = FacesContext.getCurrentInstance();
		EntityTransaction et = this.em.getTransaction();
		
		try {
			List<String> comments = new ArrayList<String>();
			comments.add(comentario[0]);
			if(flagCommentAluno)
				comments.add(comentario[1]);
			comments.add(comentarioResponsavel);
			avaliacao.setComentarios(comments);
			et.begin();
			avaliacoesBD.salvarAvaliacao(avaliacao);
			et.commit();
			flagCommentAluno = true;
			context.addMessage(null, new FacesMessage("Comentário salvo."));
			buscarComentarios();
		} catch (PersistenceException |NegocioException e) {
			et.rollback();
			e.printStackTrace();
			FacesMessage msg = new FacesMessage("Falha ao salvar comentário");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, msg);
		}
	}
	
	//getters and setters 
	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public List<String> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<String> comentarios) {
		this.comentarios = comentarios;
	}

	public String[] getComentario() {
		return comentario;
	}

	public void setComentario(String[] comentario) {
		this.comentario = comentario;
	}

	public boolean isFlagCommentAluno() {
		return flagCommentAluno;
	}

	public void setFlagCommentAluno(boolean flagCommentAluno) {
		this.flagCommentAluno = flagCommentAluno;
	}

	public boolean isFlagCommentPai() {
		return flagCommentPai;
	}

	public void setFlagCommentPai(boolean flagCommentPai) {
		this.flagCommentPai = flagCommentPai;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public String getComentarioResponsavel() {
		return comentarioResponsavel;
	}

	public void setComentarioResponsavel(String comentarioResponsavel) {
		this.comentarioResponsavel = comentarioResponsavel;
	}
	
	
	
	
}
