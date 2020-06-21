package br.com.edson.conexaoBanco;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import br.com.edson.Model.Aluno;
import br.com.edson.Model.AlunoResponsavel;
import br.com.edson.Model.Avaliacao;
import br.com.edson.Model.Bimestre;
import br.com.edson.Model.ConceitoEnum;
import br.com.edson.Model.Funcionario;
import br.com.edson.Model.PapelEnum;
import br.com.edson.Model.Responsavel;
import br.com.edson.Model.Turma;
import br.com.edson.Model.Usuario;

public class InserirDados {
	
	public static void inserir( EntityManager em) throws ParseException{
		
		Turma turma = new Turma();
		turma.setCodigoTurma("bs9op84o");
		turma.setNomeLivro("livro");
		turma.setNomeTurma("turma 1");
		turma.setHorario(new SimpleDateFormat("hh:mm").parse("10:00"));
		turma.setHorario2(new SimpleDateFormat("hh:mm").parse("10:00"));
		turma.setPrimeiroDiaSemana("Segunda feira");
		turma.setSegundoDiaSemana("Quinta Feira");
		
		Funcionario professor = new Funcionario();
		professor.setNomeCompleto("maria da silva");
		professor.setTipoAcesso(PapelEnum.PROFESSOR);
		
		turma.setProfessor(professor);
		
		Usuario uteacher = new Usuario();
		uteacher.setNomeUsuario("maria.silva prof");
		uteacher.setPessoa(professor);
		uteacher.setSenha("123");
		
		
		Aluno aluno = new Aluno();
		aluno.setMatricula("111111");
		aluno.setNomeCompleto("Edson jose de souza aluno");
		aluno.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse("30/06/1980"));
		aluno.setTurma(turma);
		
		Usuario ualuno = new Usuario();
		ualuno.setNomeUsuario("edson.souza aluno");
		ualuno.setPessoa(aluno);
		ualuno.setSenha("123");
		
		em.persist(professor);
		em.persist(uteacher);
		
		em.persist(turma);
		
		em.persist(aluno);
		em.persist(ualuno);
		
		/// acima ok
				
				
		Funcionario f = new Funcionario();
		f.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse("11/11/2020"));
		f.setNomeCompleto("jose de souza admin");
		f.setTipoAcesso(PapelEnum.ADMIN);
		
		Usuario uadmin = new Usuario();
		uadmin.setNomeUsuario("jose.souza");
		uadmin.setPessoa(f);
		uadmin.setSenha("123");
		
		em.persist(f);
		em.persist(uadmin);
		
		Responsavel r = new Responsavel();
		r.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse("11/11/2020"));
		r.setNomeCompleto("joao da silva resp");
		
		Usuario uresp = new Usuario();
		uresp.setNomeUsuario("joao.silva");
		uresp.setPessoa(professor);
		uresp.setSenha("123");
		
		em.persist(r);
		em.persist(uresp);
	
		
		AlunoResponsavel ar = new AlunoResponsavel();
		ar.setAluno(aluno);
		ar.setResponsavel(r);
		
		em.persist(ar);
		
		Bimestre b = new Bimestre();
		b.setDataFim( new SimpleDateFormat("dd/MM/yyyy").parse("15/07/2020"));
		b.setDataInicio(new SimpleDateFormat("dd/MM/yyyy").parse("01/02/2020"));
		b.setAtual(true);
	
		List<String> coments = new ArrayList<String>();
		coments.add(new String("teste"));
		Avaliacao a = new Avaliacao();
		
		a.setComentarios(coments);
		
		a.setEscrevendo(ConceitoEnum.BAd);
		a.setEscutando(ConceitoEnum.EXCELLENT);
		a.setFalando(ConceitoEnum.GOOD);
		a.setGramatica(ConceitoEnum.VERYGOOD);
		a.setHomeWork(ConceitoEnum.BAd);
		a.setLeitura(ConceitoEnum.BAd);
		a.setVocabulario(ConceitoEnum.BAd);
		
		a.setAluno(aluno);
		a.setBimestre(b);
		
		em.persist(b);
		em.persist(a);
				
		
	}

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("beyondschoolPU");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			inserir(em);
			et.commit();
		} catch (Exception e) {
			et.rollback();
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}finally {
			em.close();
			emf.close();
		}

	}

}
