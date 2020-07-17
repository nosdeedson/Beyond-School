package br.com.edson.service;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.swing.JOptionPane;

import br.com.edson.Model.Usuario;
import br.com.edson.repository.UsuariosBD;

public class ResetarSenha implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private UsuariosBD usersBD;
	
	@Inject
	public ResetarSenha() {	}



	public void resetPassWord( String email, String nomeCompleto, Date niver, String tipoAcesso  ) throws NegocioException {
		
		Usuario existe = usersBD.validaUserResetPassWord(email, nomeCompleto, niver, tipoAcesso);
		if( existe == null )
			throw new NegocioException("Usuário inexistente. Verifique os dados.");
		
		String novaSenha = getPassword();
		String novaPassHash = GeradorHashSenha.geradorHashPassWord(novaSenha);
		
		existe.setSenha(novaPassHash);
		
		try {
			usersBD.salvarUser(existe);
		} catch (PersistenceException e) {
			throw new NegocioException("Falha ao gerar nova senha.");
		}
		
		SendEmail.enviarEmail(nomeCompleto, email, novaSenha);
		
	}
	
	private static String getPassword()
    {
        String temp = Long.toHexString(Double.doubleToLongBits(Math.random()));
        return temp;
    }

}
