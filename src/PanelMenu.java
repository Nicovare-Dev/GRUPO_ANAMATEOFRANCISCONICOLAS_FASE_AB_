import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

// Pantalla de inicio: titulo y boton para comenzar.
public class PanelMenu extends JPanel {

    private static final Color AMARILLO = new Color(255, 232, 31);

    // Recibe que hacer al apretar "Comenzar"
    public PanelMenu(Runnable alComenzar) {
        setPreferredSize(new Dimension(PanelJuego.ANCHO, PanelJuego.ALTO));
        setBackground(Color.BLACK);
        setLayout(new GridBagLayout()); // centra los componentes

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new Insets(20, 0, 20, 0); // espacio entre titulo y boton

        JLabel titulo = new JLabel("SKYDEFENSE");
        titulo.setForeground(AMARILLO);
        titulo.setFont(new Font("Arial", Font.BOLD, 72));
        gbc.gridy = 0;
        add(titulo, gbc);

        JButton botonComenzar = new JButton("Comenzar juego");
        botonComenzar.setFont(new Font("Arial", Font.PLAIN, 24));
        botonComenzar.setBackground(AMARILLO);
        botonComenzar.setForeground(Color.BLACK);
        botonComenzar.setOpaque(true);          // para que se vea el color de fondo
        botonComenzar.setBorderPainted(false);
        botonComenzar.setFocusPainted(false);
        botonComenzar.addActionListener(e -> alComenzar.run());
        gbc.gridy = 1;
        add(botonComenzar, gbc);
    }
}
