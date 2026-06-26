public class Avion extends EntidadVoladora {
    private static final float ALTITUD_MIN  = 1000f;
    private static final float ALTITUD_MAX  = 5000f;
    private static final float VELOCIDAD    = 25f;
    // Limites horizontales (igual al ancho del Juego)
    private static final float POSICION_MIN = 0f;
    private static final float POSICION_MAX = 1000f;

    public Avion() {
        super(POSICION_MAX / 2f, VELOCIDAD, (ALTITUD_MIN + ALTITUD_MAX) / 2f); // centrado, media altitud
    }

    @Override
    public void mover() {}

    public void mover(Direccion dir) {
        if (dir == Direccion.DERECHA) {
            posicion += VELOCIDAD;
        } else {
            posicion -= VELOCIDAD;
        }
        // No se sale de la pantalla
        if (posicion < POSICION_MIN) {
            posicion = POSICION_MIN;
        } else if (posicion > POSICION_MAX) {
            posicion = POSICION_MAX;
        }
    }

    public void cambiarAltitud(float alt) {
        if (alt < ALTITUD_MIN) {
            this.altitud = ALTITUD_MIN;
        } else if (alt > ALTITUD_MAX) {
            this.altitud = ALTITUD_MAX;
        } else {
            this.altitud = alt;
        }
    }
}
