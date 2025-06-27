package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

//View do Cadastro de produtos com interface Swing!
public class ProdutoView extends JFrame {
    private JTextField codigoField = new JTextField(10);
    private JTextField nomeField = new JTextField(10);
    private JTextField precoField = new JTextField(10);
    private JTextField quantidadeField = new JTextField(10);
    private JButton cadastrarButton = new JButton("Cadastrar");
    private JButton listarButton = new JButton("Listar Produtos");
    private JTextArea areaTexto = new JTextArea(10, 30);

    public ProdutoView() {
        super("Gerenciar Produtos");
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.add(new JLabel("Código:"));
        inputPanel.add(codigoField);
        inputPanel.add(new JLabel("Nome:"));
        inputPanel.add(nomeField);
        inputPanel.add(new JLabel("Preço (ex: 10.50):"));
        inputPanel.add(precoField);
        inputPanel.add(new JLabel("Quantidade:"));
        inputPanel.add(quantidadeField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(cadastrarButton);
        buttonPanel.add(listarButton);

        areaTexto.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaTexto);
        setLayout(new BorderLayout(0, 10));
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scroll, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    public int getCodigo() { return Integer.parseInt(codigoField.getText()); }
    public String getNome() { return nomeField.getText(); }
    public double getPreco() { return Double.parseDouble(precoField.getText().replace(",", ".")); }
    public int getQuantidade() { return Integer.parseInt(quantidadeField.getText()); }

    public void limparCampos() {
        codigoField.setText("");
        nomeField.setText("");
        precoField.setText("");
        quantidadeField.setText("");
    }

    public void addCadastrarListener(ActionListener listener) { cadastrarButton.addActionListener(listener); }
    public void addListarListener(ActionListener listener) { listarButton.addActionListener(listener); }
    public void exibirMensagem(String mensagem) { JOptionPane.showMessageDialog(this, mensagem); }
    public void mostrarLista(String texto) { areaTexto.setText(texto); }
}