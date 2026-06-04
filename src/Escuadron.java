import java.util.ArrayList;
import java.util.List;

public class Escuadron {
    private List<Dron> drones = new ArrayList<>();
    private Direccion direccion;

    public void agregarDron(Dron dron) {
        drones.add(dron);
    }

    public List<Dron> getDronesActivos() {
        // Devolver los drones que están activos
        return new ArrayList<>();
    }
}
