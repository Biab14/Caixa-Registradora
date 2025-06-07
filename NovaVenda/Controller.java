package VendasController;

import VendasModel.;
import VendasView.View;

import java.util.*;

public class CaixaController {
    private final Map<Integer, Produto> catalogo = new HashMap<>();
    private final Venda venda = new Venda();
    private final CaixaView view = new CaixaView();
    private double totalCaixa = 0.0;

    public void iniciar() {
        int opcao;
        do {
            view.exibirMenu();
            opcao = view.lerOpcao();
            switch (opcao) {
                case 1 -> cadastrarProduto();
                case 2 -> iniciarVenda();
                case 3 -> exibirTotalCaixa();
                case 4 -> fecharCaixa();
                case 0 -> view.mostrarMensagem("Encerrando...");
                default -> view.mostrarMensagem("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void cadastrarProduto() {
        int id = view.lerInt("ID do produto: ");
        String nome = view.lerString("Nome do produto: ");
        double preco = view.lerDouble("Preço do produto: ");
        catalogo.put(id, new Produto(id, nome, preco));
        view.mostrarMensagem("Produto cadastrado com sucesso!");
    }

    private void iniciarVenda() {
        venda.limpar();
        view.mostrarMensagem("\n--- NOVA VENDA ---");

        while (true) {
            int id = view.lerInt("Digite o ID do produto (ou 0 para encerrar): ");
            if (id == 0) break;

            Produto p = catalogo.get(id);
            if (p == null) {
                view.mostrarMensagem("Produto não encontrado!");
                continue;
            }

            int quantidade = view.lerInt("Quantidade: ");
            venda.adicionarItem(new ItemVenda(p, quantidade));
            view.mostrarMensagem("Item adicionado!");
        }

        finalizarVenda();
    }

    private void finalizarVenda() {
        if (venda.isVazia()) {
            view.mostrarMensagem("Nenhum item na venda.");
            return;
        }

        double total = venda.calcularTotal();
        view.mostrarMensagem("\n--- RESUMO DA VENDA ---");
        for (ItemVenda item : venda.getItens()) {
            Produto p = item.getProduto();
            System.out.printf("%s x%d - Subtotal: R$ %.2f\n", p.getNome(), item.getQuantidade(), item.getSubtotal());
        }

        System.out.printf("TOTAL: R$ %.2f\n", total);
        double recebido = view.lerDouble("Valor recebido: R$ ");
        if (recebido < total) {
            view.mostrarMensagem("Valor insuficiente. Venda cancelada.");
            venda.limpar();
            return;
        }

        double troco = recebido - total;
        System.out.printf("TROCO: R$ %.2f\n", troco);
        totalCaixa += total;
        venda.limpar();
        view.mostrarMensagem("Venda finalizada com sucesso!");
    }

    private void exibirTotalCaixa() {
        System.out.printf("Total no caixa: R$ %.2f\n", totalCaixa);
    }

    private void fecharCaixa() {
        System.out.printf("\n--- CAIXA FECHADO ---\nTotal: R$ %.2f\n", totalCaixa);
        totalCaixa = 0;
    }
}

