import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.CardLayout;

// La ventana principal. Alterna entre la pantalla de inicio (menu) y el juego con un CardLayout.
public class VentanaJuego extends JFrame {
    private static final String MENU  = "MENU";
    private static final String JUEGO = "JUEGO";

    private CardLayout cardLayout;
    private JPanel contenedor;
    private PanelJuego panelJuego;
    private Controlador controlador;

    public VentanaJuego() {
        setTitle("SkyDefense");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        controlador = new Controlador();

        cardLayout = new CardLayout();
        contenedor = new JPanel(cardLayout); // apila las pantallas y muestra una por vez

        PanelMenu panelMenu = new PanelMenu(this::comenzarJuego);
        panelJuego = new PanelJuego(controlador);

        contenedor.add(panelMenu, MENU);
        contenedor.add(panelJuego, JUEGO);

        add(contenedor);
        pack();                          // ajusta la ventana al tamaño preferido de los paneles
        setLocationRelativeTo(null);     // centra la ventana en la pantalla
    }

    // Pasa de la pantalla de inicio al juego (lo invoca el boton "Comenzar").
    private void comenzarJuego() {
        cardLayout.show(contenedor, JUEGO);
        panelJuego.requestFocusInWindow(); // para que el panel reciba el teclado (proxima parte)
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaJuego().setVisible(true));
    }
}
