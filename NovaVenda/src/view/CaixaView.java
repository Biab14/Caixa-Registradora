package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CaixaView extends JFrame {
    private JButton btnGerenciarProdutos = new JButton("Gerenciar Produtos");
    private JButton btnIniciarVenda = new JButton("Iniciar Nova Venda");
    // Removi os outros botões para focar na funcionalidade principal
    private JButton btnSair = new JButton("Sair");

    // Componentes para a venda
    private JTextField idProdutoField = new JTextField(5);
    private JTextField qtdProdutoField = new JTextField(5);
    private JButton btnAddItem = new JButton("Adicionar Item");
    private JTextArea areaVenda = new JTextArea(10, 30);
    private JButton btnFinalizarVenda = new JButton("Finalizar Venda");
    private JLabel totalLabel = new JLabel("Total: R$ 0.00");

    public CaixaView() {
        setTitle("Caixa Registradora");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // --- Painel de Menu Superior ---
        JPanel menuPanel = new JPanel();
        menuPanel.add(btnGerenciarProdutos);
        menuPanel.add(btnIniciarVenda);
        menuPanel.add(btnSair);

        // --- Painel da Venda Atual ---
        JPanel vendaPanel = new JPanel(new BorderLayout(5, 5));
        vendaPanel.setBorder(BorderFactory.createTitledBorder("Venda Atual"));

        JPanel inputVendaPanel = new JPanel();
        inputVendaPanel.add(new JLabel("ID Prod:"));
        inputVendaPanel.add(idProdutoField);
        inputVendaPanel.add(new JLabel("Qtd:"));
        inputVendaPanel.add(qtdProdutoField);
        inputVendaPanel.add(btnAddItem);

        areaVenda.setEditable(false);
        JScrollPane scrollVenda = new JScrollPane(areaVenda);

        JPanel finalizarPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        finalizarPanel.add(totalLabel);
        finalizarPanel.add(btnFinalizarVenda);

        vendaPanel.add(inputVendaPanel, BorderLayout.NORTH);
        vendaPanel.add(scrollVenda, BorderLayout.CENTER);
        vendaPanel.add(finalizarPanel, BorderLayout.SOUTH);

        // --- Layout Principal ---
        setLayout(new BorderLayout(10, 10));
        add(menuPanel, BorderLayout.NORTH);
        add(vendaPanel, BorderLayout.CENTER);
    }

    // Métodos para o Controller se conectar
    public void addGerenciarProdutoListener(ActionListener l) { btnGerenciarProdutos.addActionListener(l); }
    public void addIniciarVendaListener(ActionListener l) { btnIniciarVenda.addActionListener(l); }
    public void addAdicionarItemListener(ActionListener l) { btnAddItem.addActionListener(l); }
    public void addFinalizarVendaListener(ActionListener l) { btnFinalizarVenda.addActionListener(l); }
    public void addSairListener(ActionListener l) { btnSair.addActionListener(l); }

    // Métodos para o Controller manipular a View
    public int getProdutoIdInput() { return Integer.parseInt(idProdutoField.getText()); }
    public int getProdutoQuantidadeInput() { return Integer.parseInt(qtdProdutoField.getText()); }
    public void setVendaText(String texto) { areaVenda.setText(texto); }
    public void appendVendaText(String texto) { areaVenda.append(texto); }
    public void setTotalLabel(String texto) { totalLabel.setText(texto); }
    public void limparCamposVenda() { idProdutoField.setText(""); qtdProdutoField.setText(""); }
    public void mostrarMensagem(String msg) { JOptionPane.showMessageDialog(this, msg); }
    public void iniciar() { setVisible(true); }
}