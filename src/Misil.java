public class Misil extends EntidadVoladora {
    private float altitudActual;
    private float altitudDetonacion;
    private float velocidadCaida;

    public Misil(float altitudInicial, float velocidadCaida, float posicion) {
        this.altitudActual     = altitudInicial;
        this.velocidadCaida    = velocidadCaida;
        this.posicion          = posicion; // posicion horizontal del dron que lo lanzo (cae en linea recta)
        this.altitudDetonacion = 1200f + (float)(Math.random() * (4500f - 1200f));
    }

    @Override
    public void mover() {
        this.altitudActual -= this.velocidadCaida;
    }


    public boolean debeDetonar() {
        return altitudActual <= altitudDetonacion;
    }

    public Explosion explotar() {
        // El misil explota en su posicion horizontal y a la altitud de detonacion
        return new Explosion(posicion, altitudDetonacion);
    }

    public void procesarExplosion(Avion avion, Jugador jugador) {
        Explosion explosion = explotar();
        float distancia = explosion.calcularDistancia(avion);

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
