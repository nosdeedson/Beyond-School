package br.com.edson.manageBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import br.com.edson.Model.Aluno;
import br.com.edson.Model.Avaliacao;
import br.com.edson.Model.Turma;
import br.com.edson.Model.Usuario;
import br.com.edson.repository.AlunosBD;
import br.com.edson.repository.AvaliacoesBD;
import br.com.edson.repository.TurmasBD;

@Named
@javax.faces.view.ViewScoped
public class verBoletimMBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Aluno aluno;
		
	@Inject
	private Avaliacao avaliacao;
	
	@Inject
	private AvaliacoesBD avaliacoesBD;

	@Inject
	private Turma turma;
	
	@Inject
	private Usuario user;
	
	private List<String> comentarios = new ArrayList<String>();
	
	private String[] comentario = new String[2];
	
	private boolean flagCommentAluno = false;
	
	private boolean flagCommentPai = false;
	
	private boolean flagTemAvaliacao = true;
	
	HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	
	//métodos
	
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
	
	
	
}
