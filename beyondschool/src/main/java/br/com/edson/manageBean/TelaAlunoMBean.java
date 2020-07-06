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
	
	private String[] comentario = new String[comentarios.size()];
	
	private String comentarioAluno;
	
	private boolean flag1 = false;
	
	private boolean flag2 = false;
	
	@Inject
	private EntityManager em;
	
	//métodos
	
	public void buscarUser() {
		user = usersBD.ValidaUsuarioLogin("edson.souza2", "123123123");
	}

	public void buscarAluno() {
		aluno = alunosBD.porId(user.getPessoa().getIdPessoa());
	}
	
	public void buscaTurma() {
		turma = turmasBD.buscaTurma(aluno.getIdPessoa());
	}
	
	public void buscaAvaliacao() {
		avaliacao = avaliacoesBD.buscaPorIdAluno(aluno.getIdPessoa());
	}
	
	public void buscarComentarios() {
		
		// testar e  corrigir 
		Avaliacao a  = avaliacoesBD.buscaComentarios(avaliacao.getIdAvaliacao());
		if(comentarios.size() == 0)
			comentarios.add("Sem comentarios");
		for ( int i = 0; i < comentarios.size(); i++) {
			if(avaliacao.getComentarios().get(i) == null)
			//comentario[i] = a.getComentarios().get(i);
			//JOptionPane.showMessageDialog(null, comentario[i]);
			if( i ==  1) {
				flag1 = true;
			}
			if( i ==  2) {
				flag2 = true;
			}
		}
		
	}
	
	public void salvarComentarioAluno() throws NegocioException {
		FacesContext context = FacesContext.getCurrentInstance();
		EntityTransaction et = this.em.getTransaction();
		
		try {
			List<String> comments = new ArrayList<String>();
			comments.add(comentario[0]);
			comments.add(comentarioAluno);
			//avaliacao.setComentarios(comments);
			et.begin();
			avaliacoesBD.salvarAvaliacao(avaliacao);
			et.commit();
			flag1 = true;
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

	public boolean isFlag1() {
		return flag1;
	}

	public void setFlag1(boolean flag1) {
		this.flag1 = flag1;
	}

	public boolean isFlag2() {
		return flag2;
	}

	public void setFlag2(boolean flag2) {
		this.flag2 = flag2;
	}

	public String getComentarioAluno() {
		return comentarioAluno;
	}

	public void setComentarioAluno(String comentarioAluno) {
		this.comentarioAluno = comentarioAluno;
	}
	
	
}
