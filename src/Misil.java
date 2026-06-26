public class Misil extends EntidadVoladora {
    private float altitudDetonacion;

    public Misil(float altitudInicial, float velocidadCaida, float posicion) {
        super(posicion, velocidadCaida, altitudInicial); // cae recto desde la posicion del dron
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
        // Explota en su posicion y a la altitud de detonacion
        return new Explosion(posicion, altitudDetonacion);
    }

    // Aplica daño segun la distancia (en pixeles) y devuelve la explosion para dibujarla.
    // Tramos: >150 seguro, 80-150 leve, 20-80 fuerte, <20 pierde una vida.
    public Explosion procesarExplosion(Avion avion, Jugador jugador) {
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
        return explosion;
    }
}
