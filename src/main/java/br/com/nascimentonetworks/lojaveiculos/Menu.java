package br.com.nascimentonetworks.lojaveiculos;

import java.util.Scanner;

public class Menu {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("=== Menu ===");
            System.out.println("1. Consultar veículo");
            System.out.println("2. Atualizar veículo");
            System.out.println("3. Excluir veículo");
            System.out.println("4. Sair do programa");
            System.out.println("============");
            System.out.println("Escolha uma opção: ");
            int opcao = sc.nextInt();
/*Quando usamos null como argumento em um método estático, 
 * estamos apenas indicando que não estamos passando nenhum 
 * argumento adicional para o método. 
 */
            switch (opcao) {
                case 1:
                    Consulta.main(null);
                    break;
                case 2:
                    Atualizacao.main(null);
                    break;
                case 3:
                    Exclusao.main(null);
                    break;
                case 4:
                    System.out.println("Saindo do programa...");
                    return;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
            }
        }
    }
}
