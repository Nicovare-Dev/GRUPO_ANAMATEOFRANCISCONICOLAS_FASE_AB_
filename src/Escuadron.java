import java.util.ArrayList;
import java.util.List;

public class Escuadron {
    private static final int MAXIMOS_ACTIVOS = 4;
    private static final int TOTAL_DRONES = 10;

    private List<Dron> dronesActivos = new ArrayList<>();
    private int dronesLanzados = 0;

    public boolean puedeLanzarSiguienteDron() {
        // No mas de 4 simultaneos y mientras queden drones del escuadron por enviar
        return dronesActivos.size() < MAXIMOS_ACTIVOS && dronesLanzados < TOTAL_DRONES;
    }

    public void lanzarDron(Dron dron) {
        dronesActivos.add(dron);
        dronesLanzados++;
    }

    public List<Dron> getDronesActivos() {
        return dronesActivos;
    }

    public void removerDronesInactivos() {
        dronesActivos.removeIf(dron -> !dron.estaActivo());
    }

    public boolean escuadronTerminado() {
        // Se enviaron los 10 drones y ninguno sigue en vuelo
        return dronesLanzados >= TOTAL_DRONES && dronesActivos.isEmpty();
    }
}
