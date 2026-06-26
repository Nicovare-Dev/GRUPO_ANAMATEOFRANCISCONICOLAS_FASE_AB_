import java.util.ArrayList;
import java.util.List;

public class Escuadron {
    private static final int MAXIMOS_ACTIVOS = 4;
    private static final int TOTAL_DRONES = 10;

    private List<Dron> dronesActivos = new ArrayList<>();
    private int dronesLanzados = 0;

    public boolean puedeLanzarSiguienteDron() {
        // Max 4 a la vez y mientras queden por enviar
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
        // Enviados los 10 y ninguno en vuelo
        return dronesLanzados >= TOTAL_DRONES && dronesActivos.isEmpty();
    }
}
