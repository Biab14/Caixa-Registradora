package controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime; // Import necessário para LocalDateTime
import java.util.List;
import model.Venda;
import model.ItemVenda;
import model.Produto;

/**
 * Classe utilitária para gerar a nota fiscal de uma venda em um arquivo de texto e exibi-la no terminal.
 */
public class NotaFiscalGenerator {

    /**
     * Gera a nota fiscal para uma venda específica e a salva em um arquivo .txt,
     * além de exibi-la no terminal.
     *
     * @param venda A instância da venda para a qual a nota fiscal será gerada.
     * @param caminhoArquivo O caminho completo do arquivo onde a nota fiscal será salva.
     * @param troco O valor do troco a ser incluído na nota fiscal.
     * @return true se a nota fiscal foi gerada com sucesso, false caso contrário.
     */
    public static boolean gerarNotaFiscal(Venda venda, String caminhoArquivo, double troco) {
        // Usaremos um StringBuilder para construir a nota fiscal para ambos: arquivo e terminal
        StringBuilder notaContent = new StringBuilder();

        // Formata a data/hora da venda para o cabeçalho da nota fiscal
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String dataHoraFormatada = venda.getDataHora().format(formatter);

        // Cabeçalho da Nota Fiscal
        notaContent.append("----------------------------------------\n");
        notaContent.append("          NOTA FISCAL - CAIXA          \n");
        notaContent.append("----------------------------------------\n");
        notaContent.append("Data/Hora: ").append(dataHoraFormatada).append("\n");
        notaContent.append("ID da Venda: ").append(venda.getIdVenda()).append("\n");
        notaContent.append("----------------------------------------\n");
        notaContent.append("PRODUTO           QTD      VL.UN    SUBTOTAL\n");
        notaContent.append("----------------------------------------\n");

        // Itens da Venda
        double totalGeral = 0;
        List<ItemVenda> itens = venda.getItens();

        if (itens != null && !itens.isEmpty()) {
            for (ItemVenda item : itens) {
                Produto produto = item.getProduto();
                // Formata os nomes para que ocupem um espaço fixo, melhorando a visualização
                String nomeProdutoFormatado = String.format("%-18.18s", produto.getNome());
                String quantidadeFormatada = String.format("%-8d", item.getQuantidade());
                String valorUnitarioFormatado = String.format("%-9.2f", produto.getPreco());
                String subtotalFormatado = String.format("%-9.2f", item.getSubtotal());

                notaContent.append(nomeProdutoFormatado)
                        .append(quantidadeFormatada)
                        .append(valorUnitarioFormatado)
                        .append(subtotalFormatado)
                        .append("\n");
                totalGeral += item.getSubtotal();
            }
        } else {
            notaContent.append("Nenhum item nesta venda.\n");
        }

        notaContent.append("----------------------------------------\n");
        notaContent.append(String.format("TOTAL DA VENDA: R$ %.2f\n", totalGeral));
        notaContent.append(String.format("TROCO: R$ %.2f\n", troco)); // Adicionando o troco
        notaContent.append("----------------------------------------\n");
        notaContent.append("         Obrigado(a) e volte sempre!    \n");
        notaContent.append("----------------------------------------\n");

        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(caminhoArquivo)))) {
            // Escreve o conteúdo completo da nota fiscal no arquivo
            writer.print(notaContent.toString());
            System.out.println("Nota fiscal gerada com sucesso em: " + caminhoArquivo);
            System.out.println("\n--- NOTA FISCAL NO TERMINAL ---\n");
            System.out.print(notaContent.toString()); // Exibe o conteúdo completo da nota fiscal no terminal
            System.out.println("--- FIM DA NOTA FISCAL NO TERMINAL ---\n");
            return true;

        } catch (IOException e) {
            System.err.println("Erro ao gerar a nota fiscal: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}