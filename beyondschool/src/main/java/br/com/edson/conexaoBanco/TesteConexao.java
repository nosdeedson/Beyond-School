package br.com.edson.conexaoBanco;

import javax.persistence.Persistence;
import javax.swing.JOptionPane;

public class TesteConexao {

	public static void main(String[] args) {
		
		// senha surfando user nosde
		try {
			Persistence.createEntityManagerFactory("beyondschoolPU");
			JOptionPane.showMessageDialog(null, " ok");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, " nao deu"+ e.getMessage());
		}

	}

}
