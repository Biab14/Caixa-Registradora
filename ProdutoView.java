package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ProdutoView extends JFrame {
    // Componentes gráficos
    private JTextField codigoField = new JTextField(10);
    private JTextField nomeField = new JTextField(10);
    private JTextField precoField = new JTextField(10);
    private JTextField quantidadeField = new JTextField(10);

    private JButton cadastrarButton = new JButton("Cadastrar");
    private JButton listarButton = new JButton("Listar");
    private JButton atualizarButton = new JButton("Atualizar");
    private JButton excluirButton = new JButton("Excluir");

    private JTextArea areaTexto = new JTextArea(10, 30);

    // Construtor que monta a interface
    public ProdutoView() {
        super("Caixa Registradora - Swing");

        // Painel de entrada de dados
        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Código:"));
        inputPanel.add(codigoField);
        inputPanel.add(new JLabel("Nome:"));
        inputPanel.add(nomeField);
        inputPanel.add(new JLabel("Preço:"));
        inputPanel.add(precoField);
        inputPanel.add(new JLabel("Quantidade:"));
        inputPanel.add(quantidadeField);

        // Painel de botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(cadastrarButton);
        buttonPanel.add(listarButton);
        buttonPanel.add(atualizarButton);
        buttonPanel.add(excluirButton);

        // Área de texto para listar produtos
        JScrollPane scroll = new JScrollPane(areaTexto);

        // Layout principal
        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scroll, BorderLayout.SOUTH);

        areaTexto.setEditable(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    // Métodos para obter dados dos campos
    public int getCodigo() { return Integer.parseInt(codigoField.getText()); }
    public String getNome() { return nomeField.getText(); }
    public double getPreco() { return Double.parseDouble(precoField.getText()); }
    public int getQuantidade() { return Integer.parseInt(quantidadeField.getText()); }

    // Limpa os campos após operações
    public void limparCampos() {
        codigoField.setText("");
        nomeField.setText("");
        precoField.setText("");
        quantidadeField.setText("");
    }

    // Métodos para adicionar "escutadores" aos botões
    public void addCadastrarListener(ActionListener listener) {
        cadastrarButton.addActionListener(listener);
    }

    public void addListarListener(ActionListener listener) {
        listarButton.addActionListener(listener);
    }

    public void addAtualizarListener(ActionListener listener) {
        atualizarButton.addActionListener(listener);
    }

    public void addExcluirListener(ActionListener listener) {
        excluirButton.addActionListener(listener);
    }

    // Exibe mensagens ao usuário
    public void exibirMensagem(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem);
    }

    // Mostra lista de produtos na área de texto
    public void mostrarLista(String texto) {
        areaTexto.setText(texto);
    }
}
