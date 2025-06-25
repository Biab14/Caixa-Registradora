package controller;

import model.*;
import view.CaixaView;
import view.ProdutoView;

import javax.swing.JOptionPane;
import java.util.HashMap;
import java.util.Map;

public class CaixaController {
    private final CaixaView view;
    private final Map<Integer, Produto> catalogo = new HashMap<>();
    private Venda vendaAtual;

    public CaixaController(CaixaView view) {
        this.view = view;
        this.vendaAtual = new Venda();
        // Produtos de exemplo
        catalogo.put(1, new Produto(1, "Coca-Cola Lata", 4.50, 100));
        catalogo.put(2, new Produto(2, "Salgado", 7.00, 50));
    }

    public void iniciar() {
        // Conecta os botões da CaixaView às ações do Controller
        view.addGerenciarProdutoListener(e -> abrirProdutoView());
        view.addIniciarVendaListener(e -> iniciarNovaVenda());
        view.addAdicionarItemListener(e -> adicionarItemNaVenda());
        view.addFinalizarVendaListener(e -> finalizarVenda());
        view.addSairListener(e -> System.exit(0));
        view.iniciar();
    }

    private void abrirProdutoView() {
        ProdutoView pView = new ProdutoView();
        new ProdutoController(pView, this.catalogo);
        pView.setVisible(true);
    }

    private void iniciarNovaVenda() {
        vendaAtual.limpar();
        atualizarDisplayVenda();
        view.mostrarMensagem("Nova venda iniciada!");
    }

    private void adicionarItemNaVenda() {
        try {
            int id = view.getProdutoIdInput();
            int qtd = view.getProdutoQuantidadeInput();

            Produto p = catalogo.get(id);
            if (p == null) {
                view.mostrarMensagem("Produto não encontrado!");
                return;
            }
            if (p.getQuantidade() < qtd) {
                view.mostrarMensagem("Estoque insuficiente! Restam: " + p.getQuantidade());
                return;
            }

            vendaAtual.adicionarItem(new ItemVenda(p, qtd));
            atualizarDisplayVenda();
            view.limparCamposVenda();

        } catch (NumberFormatException ex) {
            view.mostrarMensagem("ID e Quantidade devem ser números.");
        }
    }

    private void finalizarVenda() {
        if (vendaAtual.isVazia()) {
            view.mostrarMensagem("Adicione itens antes de finalizar a venda.");
            return;
        }
        double total = vendaAtual.calcularTotal();
        String valRecebidoStr = JOptionPane.showInputDialog(view, String.format("Total: R$ %.2f\nValor Recebido:", total));

        try {
            double valRecebido = Double.parseDouble(valRecebidoStr.replace(",","."));
            if(valRecebido < total) {
                view.mostrarMensagem("Valor insuficiente.");
                return;
            }
            // Atualiza o estoque
            for(ItemVenda item : vendaAtual.getItens()){
                Produto p = item.getProduto();
                p.setQuantidade(p.getQuantidade() - item.getQuantidade());
            }

            double troco = valRecebido - total;
            view.mostrarMensagem(String.format("Venda Finalizada!\nTroco: R$ %.2f", troco));
            iniciarNovaVenda();

        } catch (Exception ex) {
            view.mostrarMensagem("Pagamento cancelado ou valor inválido.");
        }
    }

    private void atualizarDisplayVenda() {
        StringBuilder sb = new StringBuilder("--- Itens da Venda ---\n");
        for(ItemVenda item : vendaAtual.getItens()){
            sb.append(String.format("%s (Qtd: %d) - Subtotal: R$ %.2f\n",
                    item.getProduto().getNome(),
                    item.getQuantidade(),
                    item.getSubtotal()));
        }
        view.setVendaText(sb.toString());
        view.setTotalLabel(String.format("Total: R$ %.2f", vendaAtual. calcularTotal()));
    }
}