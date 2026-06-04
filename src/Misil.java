public class Misil extends EntidadVoladora {
    private float altitudActual;
    private float altitudDetonacion;
    private float velocidadCaida;

    @Override
    public void mover() {
        // Movimiento del misil
    }

    public void descender() {
        // Hacer descender el misil
    }

    public boolean debeDetonar() {
        return altitudActual <= altitudDetonacion;
    }

    public void procesarExplosion() {
        // Procesar la explosión del misil
    }
}
