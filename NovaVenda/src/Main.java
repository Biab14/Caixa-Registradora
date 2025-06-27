import controller.CaixaController;
import view.CaixaView;
import javax.swing.SwingUtilities;


//Interface integrada das duas pastas
public class Main {
    public static void main(String[] args) {
        // Garante que a interface gráfica seja criada na thread correta
        SwingUtilities.invokeLater(() -> {
            // 1. Cria a View principal
            CaixaView view = new CaixaView();
            // 2. Cria o Controller principal, passando a View para ele
            CaixaController controller = new CaixaController(view);
            // 3. Inicia a aplicação
            controller.iniciar();
        });
    }
}