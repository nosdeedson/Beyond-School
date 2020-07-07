package br.com.edson.manageBean;

import java.awt.color.CMMException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.inject.New;
import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlOutputLabel;
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
import br.com.edson.Model.ConceitoEnum;
import br.com.edson.Model.Turma;
import br.com.edson.Model.Usuario;
import br.com.edson.repository.AlunosBD;
import br.com.edson.repository.AvaliacoesBD;
import br.com.edson.repository.ComentariosBD;
import br.com.edson.service.NegocioException;
import br.com.edson.service.RegistrarAvaliacao;


@Named
@javax.faces.view.ViewScoped
public class TelaAvaliacaoAlunoMBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Aluno aluno = null;
	
	private Aluno alunoEditar = null;
	
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
	
	@Inject
	private AvaliacoesBD avaliacoesBD;
	
	private boolean flag = true;
	
	private boolean flag1= false;
	
	private boolean flagAvaliar = true;
	
	private boolean flagBuscar = false;
	
	private boolean flagTemAvaliacao = true;
	
	@Inject
	private Comentario objComentario;
	
	@Inject
	private ComentariosBD comentariosBD;
	
	@Inject
	private Usuario usuario;
	
	HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	
	// métodos
	
	public void buscarAlunos() throws Exception {
			usuario = (Usuario) session.getAttribute("usuario");
			if( aluno == null)
				alunos = registra.buscarAlunosSemAvaliacao(turma.getCodigoTurma());
			if(alunos.size() > 0) {
				aluno = alunos.get(0);
				flagAvaliar = true;
				flagBuscar = false;
			}
			else  {
				flag = false;
				flag1 = true;
				flagAvaliar = false;
				flagBuscar = true;
			}
	
	}

	
	public void buscarAvaliacao() throws NegocioException {
		if( alunos.size() == 0 &&  aluno != null) {
			flag = true;
			flag1 = false;
			flagAvaliar = true;
			flagBuscar = false;
			
			FacesContext context = FacesContext.getCurrentInstance();
			try {
				avaliacao = avaliacoesBD.buscaPorIdAluno(aluno.getIdPessoa());
				if( avaliacao == null) {
					avaliacao = new Avaliacao();
					flagTemAvaliacao = false;
				}
				if(avaliacao != null)
					comentario = avaliacao.getComentarios().get(0).getComentario();
			} catch ( PersistenceException e) {
				FacesMessage msg = new FacesMessage(e.getMessage());
				e.printStackTrace();
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				context.addMessage(null, msg );
			}

		}
	}
	
	
	//avalia alunos
	public void avaliar() throws NegocioException {
		FacesContext context = FacesContext.getCurrentInstance();
		EntityTransaction et = em.getTransaction();
		
		// usado quando edita avaliação
		if(alunos.size() == 0  && aluno != null) {
			try {
					objComentario = avaliacao.getComentarios().get(0);
					//objComentario.setComentario(comentario);
					//avaliacao.getComentarios().get(0).setComentario(comentario);
					et.begin();
					
					objComentario.setComentario(comentario);
					registra.salvarAvaliacao(avaliacao);		
					
					comentariosBD.salvarComentario(objComentario);
					
					et.commit();
					comentario = "";
					avaliacao = new Avaliacao();
					aluno = new Aluno();
					flagAvaliar = false;
					flagBuscar = true;
					context.addMessage(null, new FacesMessage( "Avaliação editada.") );
				
			} catch ( PersistenceException e) {
				et.rollback();
				System.out.println("catch");
				e.printStackTrace();
			}
		}else {
			int cont = 0;
			
			try {
									
				objComentario.setComentario(comentario);
				objComentario.setDataComentario(new Date());
				objComentario.setIdPessoaQueFez(usuario.getPessoa().getIdPessoa());
				
				avaliacao.setAluno(aluno);
								
				et.begin();
	
				Long idAvaliacao = registra.salvarAvaliacao(avaliacao);
				avaliacao.setIdAvaliacao(idAvaliacao);
				
				objComentario.setAvaliacao(avaliacao);
				comentariosBD.salvarComentario(objComentario);		
					
				et.commit();
				alunos.remove(cont);
					
				comentario = "";
				avaliacao = new Avaliacao();
				aluno = null;
				flagAvaliar = false;
				flagBuscar = true;
				context.addMessage(null, new FacesMessage( "Aluno avaliado. Clique em buscar aluno.") );
	
				} catch (Exception e) {
					et.rollback();
					e.printStackTrace();
					FacesMessage msg = new FacesMessage("Falha ao avaliar o aluno.");
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					context.addMessage(null, msg);
				}
		}

		
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
	
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
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

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public boolean isFlag1() {
		return flag1;
	}

	public void setFlag1(boolean flag1) {
		this.flag1 = flag1;
	}

	public Aluno getAlunoEditar() {
		return alunoEditar;
	}

	public void setAlunoEditar(Aluno alunoEditar) {
		this.alunoEditar = alunoEditar;
	}

	public boolean isFlagAvaliar() {
		return flagAvaliar;
	}

	public void setFlagAvaliar(boolean flagAvaliar) {
		this.flagAvaliar = flagAvaliar;
	}

	public boolean isFlagBuscar() {
		return flagBuscar;
	}

	public void setFlagBuscar(boolean flagBuscar) {
		this.flagBuscar = flagBuscar;
	}


	public boolean isFlagTemAvaliacao() {
		return flagTemAvaliacao;
	}


	public void setFlagTemAvaliacao(boolean flagTemAvaliacao) {
		this.flagTemAvaliacao = flagTemAvaliacao;
	}
	
	
	
	
	
	

}
