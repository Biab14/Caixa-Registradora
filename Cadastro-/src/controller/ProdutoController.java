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
        this.view.addExcluirListener(e -> excluirProduto());
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
    private void excluirProduto() {
        try {
            // Pega o código do campo de texto da view
            int codigo = view.getCodigo();

            // Verifica se o produto com este código existe no catálogo
            if (catalogo.containsKey(codigo)) {
                // Remove o produto do mapa usando a chave (código)
                catalogo.remove(codigo);
                view.exibirMensagem("Produto excluído com sucesso!");
                view.limparCampos(); // Limpa os campos após a exclusão
                listarProdutos();   // Atualiza a lista na tela
            } else {
                // Se não existir, informa ao usuário
                view.exibirMensagem("Erro: Produto com o código informado não encontrado.");
            }
        } catch (NumberFormatException ex) {
            // Trata o caso do usuário digitar algo que não é um número no campo de código
            view.exibirMensagem("Erro: Por favor, insira um código válido para excluir.");
        }
    }
}