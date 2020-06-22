package br.com.edson.service;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;

import br.com.edson.Model.Funcionario;
import br.com.edson.Model.PapelEnum;
import br.com.edson.repository.FuncionariosBD;
import br.com.edson.repository.TurmasBD;

public class CadastraProfessor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FuncionariosBD funcBD;

	@Inject
	private TurmasBD turmasBD;

	@Inject
	private Funcionario funcionario;

	public CadastraProfessor() {
	}

	public Funcionario salvarProfessor(String codigo, String nomeCompleto, String nascimento) throws ParseException, NegocioException {
		
		
		Long idProf = turmasBD.buscaIdProfessor(codigo);
		
		if( idProf == null ) {
			throw new NegocioException("Código inexistente");
		}
		
		funcionario.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse(nascimento));

		funcionario.setIdPessoa(idProf);
		funcionario.setNomeCompleto(nomeCompleto);

		funcionario.setTipoAcesso(PapelEnum.PROFESSOR);

		funcBD.salvarFuncionarioCadastro(funcionario);
		
		
		return funcionario;
	}

}
