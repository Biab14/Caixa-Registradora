package model;

//Model de Vendas com interface Swing
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
// import NovaVenda.model.ItemVenda; // Removido import incorreto

public class Venda {
    private List<ItemVenda> itens;
    private LocalDateTime dataHora;
    private String idVenda;

    public Venda() {
        this.itens = new ArrayList<>();
        this.dataHora = LocalDateTime.now();
        this.idVenda = "VENDA-" + System.currentTimeMillis();
    }

    public void adicionarItem(ItemVenda item) {
        this.itens.add(item);
    }

    public void limpar() {
        this.itens.clear();
        this.dataHora = LocalDateTime.now();
    }

    public double calcularTotal() {
        double soma = 0.0;
        for (ItemVenda item : itens) {
            soma += item.getSubtotal();
        }
        return soma;
    }

    public boolean isVazia() {
        return this.itens.isEmpty();
    }

    public List<ItemVenda> getItens() {
        return itens;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public String getIdVenda() { return idVenda; }
}