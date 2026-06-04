public class Avion extends EntidadVoladora {
    private float altitud;

    @Override
    public void mover() {
        // Movimiento por defecto del avión
    }

    public void mover(Direccion dir) {
        // Mover el avión en la dirección indicada
    }

    public void cambiarAltitud(float alt) {
        this.altitud = alt;
    }
}
