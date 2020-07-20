package br.com.edson.manageBean;

import java.io.Serializable;
import java.nio.channels.SeekableByteChannel;
import java.util.ArrayList;
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
import javax.transaction.SystemException;
import javax.transaction.Transaction;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import br.com.edson.Model.Aluno;
import br.com.edson.Model.Avaliacao;
import br.com.edson.Model.Comentario;
import br.com.edson.Model.Turma;
import br.com.edson.Model.Usuario;
import br.com.edson.repository.AlunosBD;
import br.com.edson.repository.AvaliacoesBD;
import br.com.edson.repository.ComentariosBD;
import br.com.edson.repository.TurmasBD;
import br.com.edson.service.AtualizaBimestre;
import br.com.edson.service.NegocioException;

@Named
@javax.faces.view.ViewScoped
public class verBoletimMBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Aluno aluno;
		
	@Inject
	private Avaliacao avaliacao;
	
	private List<Avaliacao> avaliacoes = new ArrayList<Avaliacao>();
	
	@Inject
	private AvaliacoesBD avaliacoesBD;

	@Inject
	private Turma turma;
	
	@Inject
	private Usuario user;
	
	private List<Comentario> comentarios = new ArrayList<Comentario>();
	
	private String[] comentario = new String[2];
	
	private boolean flagCommentAluno = false;
	
	private boolean flagCommentPai = false;
	
	private boolean flagTemAvaliacao = true;
	
	@Inject
	private AtualizaBimestre atualizaBimestre;
	
	@Inject
	private EntityManager em;
	
	private boolean nextAva = false;
	
	@Inject
	private ComentariosBD commentsBD;
	
	private List<Comentario> comments = new ArrayList<Comentario>();
	
	HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	
	//métodos
	public void atualizaBimestre() throws NegocioException {
		EntityTransaction et = this.em.getTransaction();

		try {
			et.begin();
			atualizaBimestre.atualizaBimestre();
			et.commit();
		} catch (PersistenceException |NegocioException e) {
			et.rollback();
			throw new NegocioException("Falha ao atualizar o bimestre");
		}
		
	}
	public void buscarAvaliacao() {
		user = (Usuario) session.getAttribute("usuario");
		
		avaliacao = avaliacoesBD.buscaPorIdAluno(aluno.getIdPessoa());
		if(avaliacao == null)
			flagTemAvaliacao = false;
	}
	
	public void buscarComentarios() {
		if(  flagTemAvaliacao ) {
			
			if( avaliacao.getComentarios().size() > 1 && avaliacao.getComentarios().get(1).getIdPessoaQueFez().equals(aluno.getIdPessoa())  )
			{	
				flagCommentAluno = true;
				comentario[0] = avaliacao.getComentarios().get(1).getComentario();
			}
			else if ( avaliacao.getComentarios().size() > 1  ){
				
				flagCommentPai = true;
				comentario[1] = avaliacao.getComentarios().get(1).getComentario();
			}
		}
		if( flagTemAvaliacao && avaliacao.getComentarios().size() > 2   ) {
			
			if( avaliacao.getComentarios().get(1).getIdPessoaQueFez().equals(aluno.getIdPessoa()) )
			{	
				flagCommentPai = true;
				flagCommentAluno = true;
				comentario[0] = avaliacao.getComentarios().get(1).getComentario();
				comentario[1] = avaliacao.getComentarios().get(2).getComentario();
			}
			else { 
				flagCommentPai = true;
				flagCommentAluno = true;
				comentario[0] = avaliacao.getComentarios().get(2).getComentario();
				comentario[1] = avaliacao.getComentarios().get(1).getComentario();
			}
		}
		
	}
	
	public void todasAvaliacoes() throws NegocioException {
		
		avaliacoes = avaliacoesBD.todasAvaliacoesAluno(aluno.getIdPessoa());
		
		if (avaliacoes == null) {
			throw new NegocioException("Falha ao buscar avaliações.");
		}
		if( avaliacoes.size() == 1) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Esta é a única avaliação"));
		}
		else {
			nextAva = true;
			avaliacao = avaliacoes.get(0);
		}
	}
	
	/**
	 * faz o reload do form para exibir a próxima avaliação até a mais recente
	 */
	public void next()  {
		avaliacoes.remove(0);
		
		if(avaliacoes.size() == 1 ) {
			nextAva = false;
			flagTemAvaliacao = true;
			comments = commentsBD.porIdAvaliacao(avaliacoes.get(0).getIdAvaliacao());
			avaliacoes.get(0).setComentarios(comments);
			buscarAvaliacao();
			avaliacao = avaliacoes.get(0);
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Esta é a última avaliação"));
		}
		else if( avaliacoes.size() > 1) {
			nextAva = false;
			flagTemAvaliacao = true;
			comments = commentsBD.porIdAvaliacao(avaliacoes.get(0).getIdAvaliacao());
			avaliacoes.get(0).setComentarios(comments);
			buscarAvaliacao();
			avaliacao = avaliacoes.get(0);
		}
					
	}
	
	//getters and setters
	public Aluno getAluno() {
		return aluno;
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

	public String[] getComentario() {
		return comentario;
	}

	public void setComentario(String[] comentario) {
		this.comentario = comentario;
	}

	public boolean isFlagTemAvaliacao() {
		return flagTemAvaliacao;
	}
	public void setFlagTemAvaliacao(boolean flagTemAvaliacao) {
		this.flagTemAvaliacao = flagTemAvaliacao;
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


	public Usuario getUser() {
		return user;
	}


	public void setUser(Usuario user) {
		this.user = user;
	}
	public boolean isNextAva() {
		return nextAva;
	}
	public void setNextAva(boolean nextAva) {
		this.nextAva = nextAva;
	}

	
	
	
	
}
