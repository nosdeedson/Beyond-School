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
	
	public void buscarProfessor() {
		if(turma.getCodigoTurma() != null) {
			this.hora = turma.getHorario().toString();
			this.hora2 = turma.getHorario2().toString();
			this.dia1 = turma.getPrimeiroDiaSemana();
			this.dia2 = turma.getSegundoDiaSemana();
		}
	}
	
	public void salvar() throws ParseException {
		
		FacesContext context = FacesContext.getCurrentInstance();
		EntityTransaction et = em.getTransaction();
		
		
		
		try {
			
			if(turma.getCodigoTurma() == null )
				codigo = gerardorCodigo.gerarCodigoTurma();
			
			
			et.begin();
			
			Funcionario existe = funcionariosBD.buscaFuncionarioPeloNome(professor.getNomeCompleto());		
			
			if(existe != null) {
				professor = existe;
				flag = false;
			}
			
			if(flag) {
				funcionariosBD.salvarFuncionario(professor);
			}
			///no caso de edição de turma tenho que manter o mesmo codigo e nome do professor
			if( codigo != null)
				turma.setCodigoTurma(codigo);
			
			turma.setHorario(new SimpleDateFormat("hh:mm").parse(hora));
			turma.setHorario2(new SimpleDateFormat("hh:mm").parse(hora2));
			turma.setPrimeiroDiaSemana(dia1);
			turma.setSegundoDiaSemana(dia2);
			turma.setProfessor(professor);
			
			turmasBD.salvarTurma(turma);
			
			et.commit();
			context.addMessage(null, new FacesMessage("Turma criada com sucesso!! Codigo da turma: " + turma.getCodigoTurma()));
				
		} catch ( PersistenceException | NegocioException | NullPointerException | FacesException | InjectionException e) {
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
