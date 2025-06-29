package controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.List;
import model.Venda;
import model.ItemVenda;
import model.Produto;

/**
 * Classe utilitária para gerar a nota fiscal de uma venda em um arquivo de texto.
 */
public class NotaFiscalGenerator {

    /**
     * Gera a nota fiscal para uma venda específica e a salva em um arquivo .txt.
     *
     * @param venda A instância da venda para a qual a nota fiscal será gerada.
     * @param caminhoArquivo O caminho completo do arquivo onde a nota fiscal será salva.
     * @return true se a nota fiscal foi gerada com sucesso, false caso contrário.
     */
    public static boolean gerarNotaFiscal(Venda venda, String caminhoArquivo) {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(caminhoArquivo)))) {

            // Formata a data/hora da venda para o cabeçalho da nota fiscal
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            String dataHoraFormatada = venda.getDataHora().format(formatter);

            // Cabeçalho da Nota Fiscal
            writer.println("----------------------------------------");
            writer.println("          NOTA FISCAL - CAIXA          ");
            writer.println("----------------------------------------");
            writer.println("Data/Hora: " + dataHoraFormatada);
            writer.println("ID da Venda: " + venda.getIdVenda()); // Usa o getIdVenda() da sua Venda
            writer.println("----------------------------------------");
            writer.println("PRODUTO           QTD      VL.UN    SUBTOTAL");
            writer.println("----------------------------------------");

            // Itens da Venda
            double totalGeral = 0;
            List<ItemVenda> itens = venda.getItens();

            if (itens != null && !itens.isEmpty()) {
                for (ItemVenda item : itens) {
                    Produto produto = item.getProduto();
                    // Formata os nomes para que ocupem um espaço fixo, melhorando a visualização no TXT
                    String nomeProdutoFormatado = String.format("%-18.18s", produto.getNome());
                    String quantidadeFormatada = String.format("%-8d", item.getQuantidade());
                    String valorUnitarioFormatado = String.format("%-9.2f", produto.getPreco());
                    String subtotalFormatado = String.format("%-9.2f", item.getSubtotal()); // Usando getSubtotal()

                    writer.println(nomeProdutoFormatado + quantidadeFormatada + valorUnitarioFormatado + subtotalFormatado);
                    totalGeral += item.getSubtotal();
                }
            } else {
                writer.println("Nenhum item nesta venda.");
            }

            writer.println("----------------------------------------");
            writer.println(String.format("TOTAL DA VENDA: R$ %.2f", totalGeral));
            writer.println("----------------------------------------");
            writer.println("         Obrigado(a) e volte sempre!    ");
            writer.println("----------------------------------------");

            System.out.println("Nota fiscal gerada com sucesso em: " + caminhoArquivo);
            return true;

        } catch (IOException e) {
            System.err.println("Erro ao gerar a nota fiscal: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}