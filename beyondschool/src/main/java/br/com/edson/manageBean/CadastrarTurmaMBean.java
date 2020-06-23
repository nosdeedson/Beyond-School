package br.com.edson.manageBean;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.enterprise.inject.InjectionException;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.swing.JOptionPane;

import br.com.edson.Model.Funcionario;
import br.com.edson.Model.Turma;
import br.com.edson.repository.FuncionariosBD;
import br.com.edson.repository.TurmasBD;
import br.com.edson.service.GeradorCodigo;
import br.com.edson.service.NegocioException;

@Named
@javax.faces.view.ViewScoped
public class CadastrarTurmaMBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Turma turma;
	
	@Inject
	private TurmasBD turmasBD;

	@Inject
	private Funcionario professor;
	
	@Inject
	private GeradorCodigo gerardorCodigo;
	
	@Inject
	private FuncionariosBD funcionariosBD;
	
	private String dia1;
	
	private String dia2;
	
	private String hora;
	
	private String hora2;
	
	@Inject
	private EntityManager em;
	
	private String codigo;
	
	private boolean flag = true;
	
	//métodos
	
	public void salvar() throws ParseException {
		
		FacesContext context = FacesContext.getCurrentInstance();
		EntityTransaction et = em.getTransaction();
		
		try {
			
			codigo = gerardorCodigo.gerarCodigoTurma();
			
			
			
			et.begin();
			
			Funcionario existe = funcionariosBD.buscaFuncionarioPeloNome(professor.getNomeCompleto());		
			
			if(existe != null) {
				professor = existe;
				flag = false;
			}
			
			if(flag) {
				Long idPessoa = funcionariosBD.salvarFuncionario(professor);
				professor.setIdPessoa(idPessoa);
			}
			
			
			
			turma.setCodigoTurma(codigo);
			turma.setHorario(new SimpleDateFormat("hh:mm").parse(hora));
			turma.setHorario2(new SimpleDateFormat("hh:mm").parse(hora2));
			turma.setPrimeiroDiaSemana(dia1);
			turma.setSegundoDiaSemana(dia2);
			turma.setProfessor(professor);
			
			turmasBD.salvarTurma(turma);
			
			et.commit();
			context.addMessage(null, new FacesMessage("Turma criada com sucesso!!\n Codigo da turma: " + codigo));
		} catch ( PersistenceException | ParseException | NullPointerException | FacesException | InjectionException e) {
			et.rollback();
			FacesMessage msg = new FacesMessage(e.getMessage());
			e.printStackTrace();
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, msg);
		}finally {
			turma = new Turma();
			professor = new Funcionario();
			setDia1("");
			setDia2("");
			setHora("");
			setHora2("");
		}
	}
	
	
	// getters and setters
	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Funcionario getProfessor() {
		return professor;
	}

	public void setProfessor(Funcionario professor) {
		this.professor = professor;
	}


	public String getDia1() {
		return dia1;
	}


	public void setDia1(String dia1) {
		this.dia1 = dia1;
	}


	public String getDia2() {
		return dia2;
	}


	public void setDia2(String dia2) {
		this.dia2 = dia2;
	}


	public String getHora() {
		return hora;
	}
	
	public void setHora(String hora) {
		this.hora = hora;
	}


	public String getHora2() {
		return hora2;
	}


	public void setHora2(String hora2) {
		this.hora2 = hora2;
	}
	
	
	
}
