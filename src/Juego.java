import java.util.ArrayList;
import java.util.List;

public class Juego {
    private static final int   BONUS_NIVEL    = 300;
    private static final float ALTITUD_DRONES = 5000f;

    private Nivel nivel;
    private Jugador jugador;
    private Avion avion;
    private Escuadron escuadron;
    private List<Misil> misiles;
    private float anchoPantalla;

    public Juego(float anchoPantalla) {
        this.anchoPantalla = anchoPantalla;
        this.nivel         = new Nivel();
        this.jugador       = new Jugador();
        this.avion         = new Avion();
        this.misiles       = new ArrayList<>();
        this.escuadron     = new Escuadron();
    }

    public void avanzarNivel() {
        nivel.avanzar();                  // numero++ y velocidades x1. por cada vez q se pasa de nvl
        jugador.sumarPuntos(BONUS_NIVEL); // 300 puntos por superar el nvl
    }

    public Nivel getNivel()     { return nivel; }
    public Jugador getJugador() { return jugador; }
    public Avion getAvion()     { return avion; }

    public int dronesActivos() {
        return escuadron.getDronesActivos().size();
    }

    public boolean finDelJuego() {
        return !jugador.estaVivo();
    }
}
