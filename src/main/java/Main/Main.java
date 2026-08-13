package Main;

import DAO.AlunoDAO;
import Model.Aluno;
import util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EntityManager em = new JPAUtil().getEntityManager();
        AlunoDAO dao = new AlunoDAO(em);

        int opcao = 0;

        while (opcao != 6) {
            System.out.println("\n** CADASTRO DE ALUNOS **\n");
            System.out.println("1 - Cadastrar Aluno");
            System.out.println("2 - Excluir Aluno");
            System.out.println("3 - Alterar Aluno");
            System.out.println("4 - Buscar Aluno Pelo Nome");
            System.out.println("5 - Listar Alunos Aprovados");
            System.out.println("6 - FIM");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpeza de buffer

            if(opcao == 1) {
                System.out.print("Nome: ");
                String nome = scanner.nextLine();
                System.out.print("RA: ");
                String ra = scanner.nextLine();
                System.out.print("Email: ");
                String email = scanner.nextLine();

                System.out.print("Nota 1: ");
                BigDecimal n1 = scanner.nextBigDecimal();
                System.out.print("Nota 2: ");
                BigDecimal n2 = scanner.nextBigDecimal();
                System.out.print("Nota 3: ");
                BigDecimal n3 = scanner.nextBigDecimal();
                scanner.nextLine(); // Limpeza de buffer

                Aluno aluno = new Aluno(nome, ra, email, n1, n2, n3);

                em.getTransaction().begin();
                dao.cadastrar(aluno);
                em.getTransaction().commit();

                System.out.println("Aluno cadastrado com sucesso!");

            } else if(opcao == 2) {
                System.out.print("Digite o nome do aluno que deseja excluir: ");
                String nomeBusca = scanner.nextLine();

                try {
                    // 1. Busca o aluno no banco
                    Aluno alunoParaExcluir = dao.procurarPorNome(nomeBusca);

                    // 2. Abre a transação e exclui
                    em.getTransaction().begin();
                    dao.excluir(alunoParaExcluir);
                    em.getTransaction().commit();

                    System.out.println("Aluno excluído com sucesso!");
                } catch (NoResultException e) {
                    System.out.println("Erro: Aluno não encontrado no sistema.");
                }

            } else if(opcao == 3) {
                System.out.print("Digite o nome do aluno que deseja alterar: ");
                String nomeBusca = scanner.nextLine();

                try {
                    // 1. Busca o aluno existente
                    Aluno alunoParaAlterar = dao.procurarPorNome(nomeBusca);
                    System.out.println("Aluno encontrado! Digite os novos dados:");

                    // 2. Coleta os novos dados
                    System.out.print("Novo Nome: ");
                    String novoNome = scanner.nextLine();
                    System.out.print("Novo RA: ");
                    String novoRa = scanner.nextLine();
                    System.out.print("Novo Email: ");
                    String novoEmail = scanner.nextLine();

                    System.out.print("Nova Nota 1: ");
                    BigDecimal novaN1 = scanner.nextBigDecimal();
                    System.out.print("Nova Nota 2: ");
                    BigDecimal novaN2 = scanner.nextBigDecimal();
                    System.out.print("Nova Nota 3: ");
                    BigDecimal novaN3 = scanner.nextBigDecimal();
                    scanner.nextLine(); // Limpeza de buffer

                    // 3. Atualiza o objeto usando os Setters
                    alunoParaAlterar.setNome(novoNome);
                    alunoParaAlterar.setRa(novoRa);
                    alunoParaAlterar.setEmail(novoEmail);
                    alunoParaAlterar.setNota1(novaN1);
                    alunoParaAlterar.setNota2(novaN2);
                    alunoParaAlterar.setNota3(novaN3);

                    // 4. Salva a alteração no banco
                    em.getTransaction().begin();
                    dao.alterar(alunoParaAlterar);
                    em.getTransaction().commit();

                    System.out.println("Dados do aluno alterados com sucesso!");

                } catch (NoResultException e) {
                    System.out.println("Erro: Aluno não encontrado no sistema.");
                }

            } else if(opcao == 4) {
                System.out.print("Digite o nome do aluno: ");
                String nomeBusca = scanner.nextLine();
                try {
                    Aluno aluno = dao.procurarPorNome(nomeBusca);
                    System.out.println("\n--- DADOS DO ALUNO ---");
                    System.out.println("Nome: " + aluno.getNome());
                    System.out.println("RA: " + aluno.getRa());
                    System.out.println("Email: " + aluno.getEmail());
                    System.out.println("Notas: " + aluno.getNota1() + " | " + aluno.getNota2() + " | " + aluno.getNota3());
                } catch (NoResultException e) {
                    System.out.println("Erro: Aluno não encontrado no sistema.");
                }

            } else if(opcao == 5) {
                List<Aluno> alunos = dao.listarAlunos();

                if (alunos.isEmpty()) {
                    System.out.println("Nenhum aluno cadastrado no momento.");
                } else {
                    System.out.println("\n--- LISTA DE ALUNOS ---");
                    for (Aluno a : alunos) {
                        BigDecimal soma = a.getNota1().add(a.getNota2()).add(a.getNota3());
                        BigDecimal media = soma.divide(new BigDecimal("3"), 2, RoundingMode.HALF_UP);

                        String status = media.compareTo(new BigDecimal("6.0")) >= 0 ? "Aprovado" : "Reprovado";

                        System.out.println("Nome: " + a.getNome() + " | Média: " + media + " | Status: " + status);
                    }
                }

            } else if(opcao == 6) {
                System.out.println("Encerrando programa...");
                em.close();
            } else {
                System.out.println("Opção inválida! Tente novamente.");
            }
        }
        scanner.close();
    }
}
