public class Misil extends EntidadVoladora {
    private float altitudActual;
    private float altitudDetonacion;
    private float velocidadCaida;

    public Misil(float altitudInicial, float velocidadCaida) {
        this.altitudActual     = altitudInicial;
        this.velocidadCaida    = velocidadCaida;
        this.altitudDetonacion = 1200f + (float)(Math.random() * (4500f - 1200f));
    }

    @Override
    public void mover() {
        this.altitudActual -= this.velocidadCaida;
    }


    public boolean debeDetonar() {
        return altitudActual <= altitudDetonacion;
    }

    public void procesarExplosion(Avion avion, Jugador jugador) {
        float distancia = Math.abs(altitudActual - avion.getAltitud());

        if (distancia > 150) {
            jugador.sumarPuntos(40);
        } else if (distancia > 80) {
            jugador.sumarPuntos(20);
            jugador.perderEnergia(20);
        } else if (distancia > 20) {
            jugador.perderEnergia(40);
        } else {
            jugador.perderVida();
        }
    }

    public float getAltitudActual() { return altitudActual; }
}
