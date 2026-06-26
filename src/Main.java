import javax.swing.SwingUtilities;

// Punto de entrada: abre la ventana del juego.
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaJuego().setVisible(true));
    }
}
