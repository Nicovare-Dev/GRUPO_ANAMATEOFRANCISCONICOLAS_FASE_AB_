public class Avion extends EntidadVoladora {
    private static final float ALTITUD_MIN  = 1000f;
    private static final float ALTITUD_MAX  = 5000f;
    private static final float VELOCIDAD    = 50f;

    private float altitud;

    public Avion() {
        this.posicion = 0f;
        this.altitud  = ALTITUD_MIN;
    }

    @Override
    public void mover() {}

    public void mover(Direccion dir) {
        if (dir == Direccion.DERECHA) {
            posicion += VELOCIDAD;
        } else {
            posicion -= VELOCIDAD;
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

    public float getAltitud()  { return altitud; }
    public float getPosicion() { return posicion; }
}
