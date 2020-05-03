package br.com.edson.conexaoBanco;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

public class GeradorTabelasApp {

	private static EntityManagerFactory emf;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			emf = Persistence.createEntityManagerFactory("bPU");
			JOptionPane.showMessageDialog(null, "ok");
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "não deu " + e.getMessage() + e.getStackTrace());
		}finally {
			emf.close();
		}

	}

}
