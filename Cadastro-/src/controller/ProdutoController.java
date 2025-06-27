package controller;

import model.Produto;
import view.ProdutoView;
import java.util.Map;

public class ProdutoController {
    private ProdutoView view;
    private Map<Integer, Produto> catalogo;

    public ProdutoController(ProdutoView view, Map<Integer, Produto> catalogo) {
        this.view = view;
        this.catalogo = catalogo;
        this.view.addCadastrarListener(e -> cadastrarProduto());
        this.view.addListarListener(e -> listarProdutos());
        listarProdutos(); // Mostra a lista ao abrir
    }
//Controller do cadastro com Interface Swing
    private void cadastrarProduto() {
        try {
            int codigo = view.getCodigo();
            if (catalogo.containsKey(codigo)) {
                view.exibirMensagem("Erro: Código já existente.");
                return;
            }
            Produto novoProduto = new Produto(codigo, view.getNome(), view.getPreco(), view.getQuantidade());
            catalogo.put(codigo, novoProduto);
            view.exibirMensagem("Produto cadastrado com sucesso!");
            view.limparCampos();
            listarProdutos();
        } catch (NumberFormatException ex) {
            view.exibirMensagem("Erro de formato: Verifique os campos.");
        }
    }

    private void listarProdutos() {
        if (catalogo.isEmpty()) {
            view.mostrarLista("Nenhum produto cadastrado.");
            return;
        }
        StringBuilder lista = new StringBuilder("=== LISTA DE PRODUTOS ===\n");
        for (Produto p : catalogo.values()) {
            lista.append(p.toString()).append("\n");
        }
        view.mostrarLista(lista.toString());
    }
}