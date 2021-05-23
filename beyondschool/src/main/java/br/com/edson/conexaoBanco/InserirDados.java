package br.com.edson.conexaoBanco;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import br.com.edson.Model.Aluno;
import br.com.edson.Model.AlunoResponsavel;
import br.com.edson.Model.Avaliacao;
import br.com.edson.Model.Bimestre;
import br.com.edson.Model.Comentario;
import br.com.edson.Model.ConceitoEnum;
import br.com.edson.Model.Funcionario;
import br.com.edson.Model.PapelEnum;
import br.com.edson.Model.Responsavel;
import br.com.edson.Model.Turma;
import br.com.edson.Model.Usuario;
import br.com.edson.service.GeradorHashSenha;

public class InserirDados {
	
	public static void inserir( EntityManager em) throws ParseException{
		
		Turma turma = new Turma();
		turma.setCodigoTurma("bs9op84o");
		turma.setNomeLivro("livro");
		turma.setNomeTurma("turma 1");
		turma.setHorario(new SimpleDateFormat("hh:mm").parse("10:00"));
		turma.setHorario2(new SimpleDateFormat("hh:mm").parse("10:00"));
		turma.setPrimeiroDiaSemana("Segunda");
		turma.setSegundoDiaSemana("Quinta");
		
		Funcionario professor = new Funcionario();
		professor.setNomeCompleto("maria da silva");
		professor.setTipoAcesso(PapelEnum.PROFESSOR);
		professor.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse("25/06/1985"));
		
		turma.setProfessor(professor);
		
		Usuario uteacher = new Usuario();
		uteacher.setNomeUsuario("maria.silva");
		uteacher.setPessoa(professor);
		uteacher.setSenha(GeradorHashSenha.geradorHashPassWord("111111111"));
		uteacher.setTipoAcesso("professor");
		uteacher.setEmail("josemariazumira@gmail.com");
		
		
		Aluno aluno = new Aluno();
		aluno.setMatricula(100);
		aluno.setNomeCompleto("Edson jose de souza");
		aluno.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse("30/06/1980"));
		aluno.setTurma(turma);
		
		Usuario ualuno = new Usuario();
		ualuno.setNomeUsuario("edson.souza");
		ualuno.setPessoa(aluno);
		ualuno.setSenha(GeradorHashSenha.geradorHashPassWord("111111112"));
		ualuno.setTipoAcesso("aluno");
		ualuno.setEmail("josemariazumira@gmail.com");
		
		em.persist(professor);
		em.persist(uteacher);
		
		em.persist(turma);
		
		em.persist(aluno);
		em.persist(ualuno);
		
		/// acima ok
				
				
		Funcionario f = new Funcionario();
		f.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse("11/11/2020"));
		f.setNomeCompleto("jose de souza");
		f.setTipoAcesso(PapelEnum.ADMIN);
		
		
		Usuario uadmin = new Usuario();
		uadmin.setNomeUsuario("jose.souza");
		uadmin.setPessoa(f);
		uadmin.setSenha(GeradorHashSenha.geradorHashPassWord("111111113"));
		uadmin.setTipoAcesso("admin");
		uadmin.setEmail("josemariazumira@gmail.com");
		
		em.persist(f);
		em.persist(uadmin);
		
		Responsavel r = new Responsavel();
		r.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse("11/11/2020"));
		r.setNomeCompleto("joao da silva");
		em.persist(r);
		
		Usuario uresp = new Usuario();
		uresp.setNomeUsuario("joao.silva");
		uresp.setPessoa(r);
		uresp.setSenha(GeradorHashSenha.geradorHashPassWord("111111114"));
		uresp.setTipoAcesso("responsavel");
		uresp.setEmail("josemariazumira@gmail.com");
		
		em.persist(uresp);
	
		
		AlunoResponsavel ar = new AlunoResponsavel();
		ar.setAluno(aluno);
		ar.setResponsavel(r);
		
		em.persist(ar);
		
		Bimestre b = new Bimestre();
		b.setDataInicio(new SimpleDateFormat("dd/MM/yyyy").parse("01/02/2021"));
		b.setDataFim( new SimpleDateFormat("dd/MM/yyyy").parse("15/04/2021"));
		b.setAtual(false);
		
		Bimestre b2 = new Bimestre();
		b2.setDataInicio(new SimpleDateFormat("dd/MM/yyyy").parse("16/04/2021"));
		b2.setDataFim( new SimpleDateFormat("dd/MM/yyyy").parse("15/07/2021"));
		b2.setAtual(true);		
		
		
		Avaliacao a = new Avaliacao();
		
		a.setEscrevendo(ConceitoEnum.BAD);
		a.setEscutando(ConceitoEnum.EXCELLENT);
		a.setFalando(ConceitoEnum.GOOD);
		a.setGramatica(ConceitoEnum.VERYGOOD);
		a.setHomeWork(ConceitoEnum.BAD);
		a.setLeitura(ConceitoEnum.BAD);
		a.setVocabulario(ConceitoEnum.BAD);
		
		a.setAluno(aluno);
		a.setBimestre(b);
		
		em.persist(b);
		em.persist(b2);
		em.persist(a);
		
		Comentario comentario1 = new Comentario();
		comentario1.setComentario("comentario um prof");
		comentario1.setIdPessoaQueFez(professor.getIdPessoa());
		comentario1.setDataComentario(new Date());
		comentario1.setAvaliacao(a);
		
		em.persist(comentario1);
		
		Comentario comentario2 = new Comentario();
		comentario2.setComentario("comentario aluno");
		comentario2.setIdPessoaQueFez(aluno.getIdPessoa());
		comentario2.setDataComentario(new Date());
		comentario2.setAvaliacao(a);
		
		em.persist(comentario2);
		
		Aluno aluno1 = new Aluno();
		aluno1.setMatricula(101);
		aluno1.setNomeCompleto("Joao pedro souza");
		aluno1.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse("21/01/2000"));
		aluno1.setTurma(turma);
		
		em.persist(aluno1);
		
		Usuario ualuno1 = new Usuario();
		ualuno1.setNomeUsuario("joao.souza");
		ualuno1.setPessoa(aluno1);
		ualuno1.setSenha(GeradorHashSenha.geradorHashPassWord("111111115"));
		ualuno1.setTipoAcesso("aluno");
		em.persist(ualuno1);
		ualuno1.setEmail("josemariazumira@gmail.com");
		
		Responsavel r1 = new Responsavel();
		r1.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse("18/09/1982"));
		r1.setNomeCompleto("silvana souza");
		em.persist(r1);
		
		Usuario uresp1 = new Usuario();
		uresp1.setNomeUsuario("silvana.souza");
		uresp1.setPessoa(r1);
		uresp1.setSenha(GeradorHashSenha.geradorHashPassWord("111111116"));
		uresp1.setTipoAcesso("responsavel");
		uresp1.setEmail("josemariazumira@gmail.com");
		
		AlunoResponsavel ar1 = new AlunoResponsavel();
		ar1.setAluno(aluno1);
		ar1.setResponsavel(r1);
		
		em.persist(r1);
		em.persist(uresp1);
		em.persist(ar1);
		
		Aluno aluno2 = new Aluno();
		aluno2.setMatricula(102);
		aluno2.setNomeCompleto("isabela souza");
		aluno2.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse("02/08/2000"));
		aluno2.setTurma(turma);
		
		em.persist(aluno2);
		
		Usuario ualuno2 = new Usuario();
		ualuno2.setNomeUsuario("isabela.souza");
		ualuno2.setPessoa(aluno2);
		ualuno2.setSenha(GeradorHashSenha.geradorHashPassWord("111111117"));
		ualuno2.setTipoAcesso("aluno");
		em.persist(ualuno2);
		ualuno2.setEmail("josemariazumira@gmail.com");
		
		AlunoResponsavel ar2 = new AlunoResponsavel();
		ar2.setAluno(aluno2);
		ar2.setResponsavel(r1);
		em.persist(ar2);
		
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
