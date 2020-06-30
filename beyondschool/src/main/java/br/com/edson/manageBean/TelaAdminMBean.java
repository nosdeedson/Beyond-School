package br.com.edson.manageBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.inject.Inject;
import javax.inject.Named;
import javax.swing.JOptionPane;

import br.com.edson.Model.Turma;
import br.com.edson.repository.TurmasBD;


@Named
@javax.faces.view.ViewScoped
public class TelaAdminMBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private TurmasBD turmasBD;
	
	
	@Inject
	private Turma turma;

	private Turma turma2 = null;
	
	private List<Turma> turmas = new ArrayList<Turma>();
	

	//métodos
	public void buscarTurmas() {
		turmas = turmasBD.todasTurmas();
	}
	
	public void editarTurma() {
		
			JOptionPane.showMessageDialog(null, "turma dois");
		
	}
	//getters and setters
	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Turma getTurma2() {
		return turma2;
	}

	public void setTurma2(Turma turma2) {
		this.turma2 = turma2;
	}
	
	

}
