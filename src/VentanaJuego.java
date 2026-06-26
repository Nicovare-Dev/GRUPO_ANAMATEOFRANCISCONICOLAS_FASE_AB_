import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.CardLayout;

// Ventana principal. Alterna entre menu y juego con un CardLayout.
public class VentanaJuego extends JFrame {
    private static final String MENU  = "MENU";
    private static final String JUEGO = "JUEGO";
    private static final int    DELAY_MS = 33; // ~30 fps

    private CardLayout cardLayout;
    private JPanel contenedor;
    private PanelJuego panelJuego;
    private Controlador controlador;
    private Timer gameLoop;

    public VentanaJuego() {
        setTitle("SkyDefense");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        controlador = new Controlador();

        cardLayout = new CardLayout();
        contenedor = new JPanel(cardLayout); // muestra una pantalla por vez

        PanelMenu panelMenu = new PanelMenu(this::comenzarJuego);
        panelJuego = new PanelJuego(controlador);

        contenedor.add(panelMenu, MENU);
        contenedor.add(panelJuego, JUEGO);

        add(contenedor);
        pack();                          // ajusta la ventana al tamaño de los paneles
        setLocationRelativeTo(null);     // la centra

        // Game loop: cada frame avanza el juego y redibuja
        gameLoop = new Timer(DELAY_MS, e -> {
            controlador.update();
            panelJuego.repaint();
        });
    }

    // Del menu al juego (lo llama el boton "Comenzar")
    private void comenzarJuego() {
        cardLayout.show(contenedor, JUEGO);
        panelJuego.requestFocusInWindow(); // para recibir el teclado
        gameLoop.start();                  // arranca el loop
    }
}
