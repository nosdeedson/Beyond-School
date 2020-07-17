package br.com.edson.service;

import java.io.Serializable;

import org.simplejavamail.api.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;


public class SendEmail  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public static void enviarEmail(String nomeCompleto, String email, String novaSenha ) throws NegocioException {
		try {
			// caso para de funcionar fazer a classe de autenticação projeto topico especial 
			Email mailAddress = EmailBuilder.startingBlank().from("Edson", "edsonjs_80@hotmail.com").to(nomeCompleto, email)
					.withSubject("Sua nova senha")
					.withPlainText("Sua nova senha " + novaSenha +  " redefina-a quando entrar no site novamente.").buildEmail();

			MailerBuilder.withSMTPServer("smtp.office365.com", 587, "edsonjs_80@hotmail.com", "revoltadosDandis!&")
					.buildMailer().sendMail(mailAddress);
			
		} catch (Exception e) {
			throw new NegocioException(e.getMessage());
		}
		
	
	}	
	

}
