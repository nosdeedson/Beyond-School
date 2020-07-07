package br.com.edson.manageBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import br.com.edson.Model.Aluno;
import br.com.edson.Model.Avaliacao;
import br.com.edson.Model.Comentario;
import br.com.edson.Model.Turma;
import br.com.edson.Model.Usuario;
import br.com.edson.repository.AlunosBD;
import br.com.edson.repository.AvaliacoesBD;
import br.com.edson.repository.ComentariosBD;
import br.com.edson.repository.TurmasBD;
import br.com.edson.repository.UsuariosBD;
import br.com.edson.service.NegocioException;

@Named
@javax.faces.view.ViewScoped
public class TelaAlunoMBean implements Serializable {

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
	
	@Inject
	private AlunosBD alunosBD;
	
	private List<String> comentarios = new ArrayList<String>();
	
	private String[] comentario = new String[2];
	
	private String comentarioAluno;
	
	private boolean flagCommentAluno = false;
	
	private boolean flagCommentResp = false;
	
	private boolean flagTemAvaliacao = true;
	
	@Inject
	private Comentario objComentario;
	
	@Inject
	private ComentariosBD comentariosBD;
	
	@Inject
	private EntityManager em;
	
	HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	
	//métodos


	public void buscarAluno() {
		user = (Usuario) session.getAttribute("usuario");
		aluno = alunosBD.porId(user.getPessoa().getIdPessoa());
	}
	
	public void buscaTurma() {
		turma = turmasBD.buscaTurma(aluno.getIdPessoa());
	}
	
	public void buscaAvaliacao() {
		avaliacao = avaliacoesBD.buscaPorIdAluno(aluno.getIdPessoa());
		if(avaliacao == null)
			flagTemAvaliacao = false;
	}
	
	public void buscarComentarios() {
	if( flagTemAvaliacao ) {
			
			
			if( avaliacao.getComentarios().size() > 1 && avaliacao.getComentarios().get(1).getIdPessoaQueFez().equals(aluno.getIdPessoa())  )
			{	JOptionPane.showMessageDialog(null, "1");
				flagCommentAluno = true;
				comentario[0] = avaliacao.getComentarios().get(1).getComentario();
			}
			else if ( avaliacao.getComentarios().size() > 1  ){
				JOptionPane.showMessageDialog(null, "2");
				flagCommentResp = true;
				comentario[1] = avaliacao.getComentarios().get(1).getComentario();
			}
		}
		if( avaliacao.getComentarios().size() > 2  && flagTemAvaliacao  ) {
			if( avaliacao.getComentarios().get(1).getIdPessoaQueFez().equals(aluno.getIdPessoa()) )
			{	JOptionPane.showMessageDialog(null, "3");
				flagCommentResp = true;
				flagCommentAluno = true;
				comentario[0] = avaliacao.getComentarios().get(1).getComentario();
				comentario[1] = avaliacao.getComentarios().get(2).getComentario();
			}
			else { JOptionPane.showMessageDialog(null, "4");
				flagCommentResp = true;
				flagCommentAluno = true;
				comentario[0] = avaliacao.getComentarios().get(2).getComentario();
				comentario[1] = avaliacao.getComentarios().get(1).getComentario();
			}
		}
		
	}
	
	public void salvarComentarioAluno() throws NegocioException {
		FacesContext context = FacesContext.getCurrentInstance();
		EntityTransaction et = this.em.getTransaction();
		
		try {
			
			objComentario.setComentario(comentarioAluno);
			objComentario.setDataComentario(new Date());
			objComentario.setIdPessoaQueFez(aluno.getIdPessoa());
			objComentario.setAvaliacao(avaliacao);
			et.begin();
			
			
			comentariosBD.salvarComentario(objComentario);
			
			et.commit();
			flagCommentAluno = true;
			context.addMessage(null, new FacesMessage("Comentário salvo."));
			buscaAvaliacao();
			buscarComentarios();
		} catch (PersistenceException |NegocioException e) {
			et.rollback();
			e.printStackTrace();
			FacesMessage msg = new FacesMessage("Falha ao salvar comentário");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, msg);
		}
	}
	
	// getters and setters
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

	public boolean isFlagCommentResp() {
		return flagCommentResp;
	}

	public void setFlagCommentResp(boolean flagCommentResp) {
		this.flagCommentResp = flagCommentResp;
	}

	public String getComentarioAluno() {
		return comentarioAluno;
	}

	public void setComentarioAluno(String comentarioAluno) {
		this.comentarioAluno = comentarioAluno;
	}

	public boolean isFlagTemAvaliacao() {
		return flagTemAvaliacao;
	}

	public void setFlagTemAvaliacao(boolean flagTemAvaliacao) {
		this.flagTemAvaliacao = flagTemAvaliacao;
	}
	
	
	
}
