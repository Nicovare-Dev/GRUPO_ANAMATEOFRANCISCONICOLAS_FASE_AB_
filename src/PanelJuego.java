import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

// La Vista: el lienzo donde se dibuja el juego. Lee el estado a traves del Controlador.
public class PanelJuego extends JPanel {
    public static final int ANCHO = 1000;
    public static final int ALTO  = 600;

    private static final Color COLOR_FONDO  = Color.BLACK;
    private static final Color COLOR_AVION  = Color.CYAN;
    private static final Font  FUENTE_HUD   = new Font("Arial", Font.BOLD, 20);

    // Rango de altitud del mundo, para mapear altitud -> pixeles verticales
    private static final float ALTITUD_MIN = 1000f;
    private static final float ALTITUD_MAX = 5000f;
    // Margenes para no dibujar pegado a los bordes (arriba dejo lugar para el HUD)
    private static final int MARGEN_SUP = 100;
    private static final int MARGEN_INF = 40;
    // Medio ancho/alto del triangulo que representa al avion
    private static final int AVION_TAM = 20;

    private final Controlador controlador;

    public PanelJuego(Controlador controlador) {
        this.controlador = controlador;
        setPreferredSize(new Dimension(ANCHO, ALTO));
        setBackground(COLOR_FONDO);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // limpia el panel con el color de fondo
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        dibujarHud(g2);
        // TODO: dibujar avion, drones y misiles (proximas partes)
    }

    // Dibuja la info del jugador en la esquina superior izquierda
    private void dibujarHud(Graphics2D g2) {
        g2.setFont(FUENTE_HUD);

        g2.setColor(Color.RED);
        g2.drawString("Vidas: " + controlador.getVidas(), 20, 30);

        g2.setColor(Color.YELLOW);
        g2.drawString("Energia: " + (int) controlador.getEnergia(), 20, 55);

        g2.setColor(Color.WHITE);
        g2.drawString("Nivel: " + controlador.getNivelActual(), 20, 80);
    }
}
