import java.util.ArrayList;
import java.util.List;

public class Juego {
    private static final int BONUS_NIVEL = 300;

    private Nivel nivel;
    private Jugador jugador;
    private Avion avion;
    private List<Escuadron> escuadrones = new ArrayList<>();
    private List<Misil> misiles = new ArrayList<>();

    public Juego(){
        this.nivel = new Nivel();
        this.jugador = new Jugador();
        this.avion = new Avion();

    }

    public void avanzarNivel() {
        nivel.avanzar();                  // numero++ y velocidades x1. por cada vez q se pasa de nvl
        jugador.sumarPuntos(BONUS_NIVEL); // 300 puntos por superar el nvl
    }

    public Nivel getNivel()     { return nivel; }
    public Jugador getJugador() { return jugador; }
    public Avion getAvion()     { return avion; }

    public int dronesActivos() {
        // Contar la cant de drones activos en el juego
        return 0;
    }

    public boolean finDelJuego() {
        // Determinar si el juego ha terminado
        return false;
    }
}
