import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

// Vista: dibuja el juego leyendo el estado del Controlador.
public class PanelJuego extends JPanel {
    public static final int ANCHO = 1000;
    public static final int ALTO  = 600;

    private static final Color COLOR_FONDO  = Color.BLACK;
    private static final Color COLOR_AVION  = Color.CYAN;
    private static final Color COLOR_DRON   = new Color(200, 60, 60);
    private static final Color COLOR_MISIL  = new Color(255, 160, 0);
    private static final Font  FUENTE_HUD   = new Font("Arial", Font.BOLD, 20);
    private static final Font  FUENTE_FIN   = new Font("Arial", Font.BOLD, 60);

    // Rango de altitud, para mapear altitud -> pixeles
    private static final float ALTITUD_MIN = 1000f;
    private static final float ALTITUD_MAX = 5000f;
    // Margenes (arriba va el HUD)
    private static final int MARGEN_SUP = 100;
    private static final int MARGEN_INF = 40;
    // Tamaño del triangulo del avion
    private static final int AVION_TAM = 20;
    // Cuanto cambia la altitud por tecla
    private static final float PASO_ALTITUD = 200f;

    private final Controlador controlador;

    public PanelJuego(Controlador controlador) {
        this.controlador = controlador;
        setPreferredSize(new Dimension(ANCHO, ALTO));
        setBackground(COLOR_FONDO);
        setFocusable(true);                    // para recibir el teclado
        addKeyListener(new ControlTeclado());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // limpia el panel
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        dibujarMisiles(g2);
        dibujarDrones(g2);
        dibujarAvion(g2);
        dibujarExplosiones(g2);
        dibujarHud(g2);

        if (controlador.finDelJuego()) {
            dibujarGameOver(g2);
        }
    }

    // --- Dibujo de las entidades ---

    // Avion: un triangulo apuntando hacia arriba
    private void dibujarAvion(Graphics2D g2) {
        int x = aX(controlador.getAvionPosicion());
        int y = aY(controlador.getAvionAltitud());

        Polygon triangulo = new Polygon();
        triangulo.addPoint(x, y - AVION_TAM);
        triangulo.addPoint(x - AVION_TAM, y + AVION_TAM);
        triangulo.addPoint(x + AVION_TAM, y + AVION_TAM);

        g2.setColor(COLOR_AVION);
        g2.fillPolygon(triangulo);
    }

    // Dron: cuerpo ovalado con dos helices
    private void dibujarDrones(Graphics2D g2) {
        g2.setColor(COLOR_DRON);
        for (Dron dron : controlador.getDrones()) {
            int x = aX(dron.getPosicion());
            int y = aY(dron.getAltitud());
            g2.fillOval(x - 18, y - 6, 36, 12);   // cuerpo
            g2.fillOval(x - 26, y - 9, 12, 6);    // helice izquierda
            g2.fillOval(x + 14, y - 9, 12, 6);    // helice derecha
        }
    }

    // Misil: una estela corta que cae
    private void dibujarMisiles(Graphics2D g2) {
        g2.setColor(COLOR_MISIL);
        for (Misil misil : controlador.getMisiles()) {
            int x = aX(misil.getPosicion());
            int y = aY(misil.getAltitud());
            g2.fillRect(x - 2, y, 4, 14);
        }
    }

    // Explosion: circulo naranja que crece y se apaga
    private void dibujarExplosiones(Graphics2D g2) {
        for (Explosion ex : controlador.getExplosiones()) {
            int x = aX(ex.getPosicion());
            int y = aY(ex.getAltitud());
            int edad = ex.getVidaInicial() - ex.getVida();
            int radio = 6 + edad * 3;                  // crece
            int alpha = Math.max(0, 220 - edad * 16);  // se apaga
            g2.setColor(new Color(255, 140, 0, alpha));
            g2.fillOval(x - radio, y - radio, radio * 2, radio * 2);
        }
    }

    // HUD: vidas, energia, nivel y puntos
    private void dibujarHud(Graphics2D g2) {
        g2.setFont(FUENTE_HUD);

        g2.setColor(Color.RED);
        g2.drawString("Vidas: " + controlador.getVidas(), 20, 30);

        g2.setColor(Color.YELLOW);
        g2.drawString("Energia: " + (int) controlador.getEnergia(), 20, 55);

        g2.setColor(Color.WHITE);
        g2.drawString("Nivel: " + controlador.getNivelActual(), 20, 80);

        g2.setColor(Color.GREEN);
        g2.drawString("Puntos: " + controlador.getPuntos(), ANCHO - 180, 30);
    }

    // Pantalla final al quedarse sin vidas
    private void dibujarGameOver(Graphics2D g2) {
        g2.setColor(new Color(0, 0, 0, 180));  // velo oscuro
        g2.fillRect(0, 0, ANCHO, ALTO);

        g2.setColor(Color.RED);
        g2.setFont(FUENTE_FIN);
        dibujarCentrado(g2, "GAME OVER", ALTO / 2 - 30);

        g2.setColor(Color.WHITE);
        g2.setFont(FUENTE_HUD);
        dibujarCentrado(g2, "Puntaje final: " + controlador.getPuntos(), ALTO / 2 + 20);
        dibujarCentrado(g2, "Presiona R para reiniciar", ALTO / 2 + 50);
    }

    private void dibujarCentrado(Graphics2D g2, String texto, int y) {
        int ancho = g2.getFontMetrics().stringWidth(texto);
        g2.drawString(texto, (ANCHO - ancho) / 2, y);
    }

    // --- Mundo -> pixeles ---

    // Posicion horizontal -> pixel x
    private int aX(float posicion) {
        return Math.round(posicion / controlador.getAnchoPantalla() * ANCHO);
    }

    // Altitud -> pixel y (mas altitud = mas arriba)
    private int aY(float altitud) {
        float t = (ALTITUD_MAX - altitud) / (ALTITUD_MAX - ALTITUD_MIN);
        if (t < 0f) t = 0f;
        if (t > 1f) t = 1f;
        return Math.round(MARGEN_SUP + t * (ALTO - MARGEN_SUP - MARGEN_INF));
    }

    // --- Teclado ---

    private class ControlTeclado extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (controlador.finDelJuego()) {
                if (e.getKeyCode() == KeyEvent.VK_R) {
                    controlador.reiniciar();
                    repaint();
                }
                return;
            }

            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    controlador.moverAvion(Direccion.IZQUIERDA);
                    break;
                case KeyEvent.VK_RIGHT:
                    controlador.moverAvion(Direccion.DERECHA);
                    break;
                case KeyEvent.VK_UP:
                    controlador.cambiarAltitud(controlador.getAvionAltitud() + PASO_ALTITUD);
                    break;
                case KeyEvent.VK_DOWN:
                    controlador.cambiarAltitud(controlador.getAvionAltitud() - PASO_ALTITUD);
                    break;
            }
            repaint();
        }
    }
}
