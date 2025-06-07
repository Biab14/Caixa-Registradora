package VendasView;


import java.util.Scanner;

public class CaixaView {
    private final Scanner scanner = new Scanner(System.in);

    public void exibirMenu() {
        System.out.println("\n--- MENU ---");
        System.out.println("1. Cadastrar produto");
        System.out.println("2. Iniciar nova venda");
        System.out.println("3. Exibir total do caixa");
        System.out.println("4. Fechar caixa");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    public int lerOpcao() {
        return scanner.nextInt();
    }

    public int lerInt(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextInt();
    }

    public double lerDouble(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextDouble();
    }

    public String lerString(String mensagem) {
        System.out.print(mensagem);
        scanner.nextLine(); // limpar buffer
        return scanner.nextLine();
    }

    public void mostrarMensagem(String msg) {
        System.out.println(msg);
    }
}
