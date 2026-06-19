public class Misil extends EntidadVoladora {
    private float altitudDetonacion;

    public Misil(float altitudInicial, float velocidadCaida, float posicion) {
        super(posicion, velocidadCaida, altitudInicial); // posicion horizontal del dron que lo lanzo (cae en linea recta)
        this.altitudDetonacion = 1200f + (float)(Math.random() * (4500f - 1200f));
    }

    @Override
    public void mover() {
        this.altitud -= this.velocidad;
    }


    public boolean debeDetonar() {
        return altitud <= altitudDetonacion;
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
}
