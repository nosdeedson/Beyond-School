package br.com.edson.service;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.inject.Inject;

public class GeradorHashSenha implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	public GeradorHashSenha() {}
	
	public static String geradorHashPassWord(String senha) {
		
		byte[] passWord = null;
		
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			passWord = digest.digest(senha.getBytes(StandardCharsets.UTF_8));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return senha = passWordToHash(passWord);
	}
	
	private static String passWordToHash(byte[] hash) {
		StringBuffer hexString = new StringBuffer();
	    for (int i = 0; i < hash.length; i++) {
	    String hex = Integer.toHexString(0xff & hash[i]);
	    if(hex.length() == 1) hexString.append('0');
	        hexString.append(hex);
	    }
	    return hexString.toString();
	}

}
