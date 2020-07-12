package br.com.edson.service;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;
import javax.swing.JOptionPane;

import br.com.edson.repository.UsuariosBD;

public class ResetarSenha implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private UsuariosBD usersBD;
	
	@Inject
	public ResetarSenha() {	}



	public void resetPassWord( String email, String nomeCompleto, Date niver, String tipoAcesso  ) throws NegocioException {
		
		boolean existe = usersBD.validaUserResetPassWord(email, nomeCompleto, niver, tipoAcesso);
		if(!existe)
			throw new NegocioException("Usuário inexistente. Verifique os dados.");
		
	}

}
