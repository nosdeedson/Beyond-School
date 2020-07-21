package br.com.edson.service;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;

import br.com.edson.Model.Funcionario;
import br.com.edson.Model.PapelEnum;
import br.com.edson.Model.Usuario;
import br.com.edson.repository.FuncionariosBD;
import br.com.edson.repository.TurmasBD;
import br.com.edson.repository.UsuariosBD;

public class CadastraProfessor implements Serializable {

	private final long serialVersionUID = 1L;
	
	
	@Inject
	private  FuncionariosBD funcBD;

	@Inject
	private  TurmasBD turmasBD;

	@Inject
	private  Funcionario funcionario;
	
	
	
	public CadastraProfessor() {
	}

	public Funcionario salvarProfessor(String codigo, String nomeCompleto, String nascimento) throws ParseException, NegocioException {
		
		
		Long codigoProf= turmasBD.buscaIdProfessor(codigo);
		
		if( codigoProf == null ) {
			throw new NegocioException("Código inexistente");
		}
		Funcionario prof = funcBD.porId(codigoProf);
		prof.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse(nascimento));

		prof.setNomeCompleto(nomeCompleto);

		prof.setTipoAcesso(PapelEnum.PROFESSOR);

		funcBD.atualizaFuncionario(prof);
		
		return prof;
	}

}
