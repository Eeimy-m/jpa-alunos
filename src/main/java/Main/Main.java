package Main;

import java.util.Scanner;

public class Main {
    static void main() {
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        while (opcao != 6) {
            System.out.println("1 - Cadastrar Aluno");
            System.out.println("2 - Excluir Aluno");
            System.out.println("3 - Alterar Aluno");
            System.out.println("4 - Buscar Aluno Pelo Nome");
            System.out.println("5 - Listar Alunos");
            System.out.println("6 - FIM");

            opcao = scanner.nextInt();

            if(opcao == 1) {

            } else if(opcao == 2) {

            } else if(opcao == 3) {

            } else if(opcao == 4) {

            } else if(opcao == 5) {

            } else if(opcao == 6) {
                System.out.println("Encerrando programa");
            }
        }
    }
}
