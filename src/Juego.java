import java.util.ArrayList;
import java.util.List;

public class Juego {
    private Nivel nivel;
    private Jugador jugador;
    private Avion avion;
    private List<Escuadron> escuadrones = new ArrayList<>();
    private List<Misil> misiles = new ArrayList<>();

    public void avanzarNivel() {
        // Avanzar al siguiente nivel del juego
    }

    public int dronesActivos() {
        // Contar la cantidad de drones activos en el juego
        return 0;
    }

    public boolean finDelJuego() {
        // Determinar si el juego ha terminado
        return false;
    }
}
