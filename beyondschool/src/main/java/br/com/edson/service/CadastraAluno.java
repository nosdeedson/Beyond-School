package br.com.edson.service;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;

import br.com.edson.Model.Aluno;
import br.com.edson.Model.Funcionario;
import br.com.edson.Model.PapelEnum;
import br.com.edson.Model.Turma;
import br.com.edson.repository.AlunosBD;
import br.com.edson.repository.TurmasBD;

public class CadastraAluno implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;
	
	@Inject
	private TurmasBD turmasBD;
		
	@Inject
	private AlunosBD alunosBD;
	
	@Inject
	private VerificaExisteAluno verificaAluno;
	
	@Inject
	private Turma turma;
	
	@Inject
	public CadastraAluno() { }
	
	public Aluno salvarAluno(String codigo, String nomeCompleto, String nascimento) throws ParseException {
		return null;
	}

}
