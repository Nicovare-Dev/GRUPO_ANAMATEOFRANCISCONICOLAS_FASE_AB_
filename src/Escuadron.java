import java.util.ArrayList;
import java.util.List;

public class Escuadron {
    private static final int MAXIMOS_ACTIVOS = 4;
    private static final int TOTAL_DRONES = 10;

    private List<Dron> drones = new ArrayList<>();
    private int indiceDron = 0;

    public void agregarDron(Dron dron) {
        drones.add(dron);
    }

    public List<Dron> getDronesActivos() {
        List<Dron> activos = new ArrayList<>();
        for (Dron dron : drones) {
            if (dron.estaActivo()) {
                activos.add(dron);
            }
        }
        return activos;
    }

    public boolean puedeLanzarSiguienteDron() {
        return getDronesActivos().size() < MAXIMOS_ACTIVOS && hayDronesDisponibles();
    }

    public void lanzarSiguiente() {
        if (hayDronesDisponibles()) {
            indiceDron++;
        }
    }

    public boolean hayDronesDisponibles() {
        return indiceDron < drones.size();
    }

    public boolean escuadronTerminado() {
        return indiceDron >= TOTAL_DRONES && getDronesActivos().isEmpty();
    }
}
