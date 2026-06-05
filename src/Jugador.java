public class Jugador {
    private static final int   VIDAS_INICIALES  = 3;
    private static final float ENERGIA_INICIAL  = 100f;
    private static final int   PUNTOS_VIDA_EXTRA = 1000;

    private int vidas;
    private float energia;
    private int puntos;
    private int puntosUltimaVidaExtra;

    public Jugador() {
        this.vidas = VIDAS_INICIALES;
        this.energia = ENERGIA_INICIAL;
        this.puntos = 0;
        this.puntosUltimaVidaExtra = 0;
    }

    public void perderEnergia(float porcentaje) {
        this.energia -= porcentaje;
        if (this.energia <= 0) {
            this.energia = ENERGIA_INICIAL;
            perderVida();
        }
    }

    public void perderVida() {
        this.vidas--;
    }

    public void sumarPuntos(int pts) {
        this.puntos += pts;
        if (this.puntos - puntosUltimaVidaExtra >= PUNTOS_VIDA_EXTRA) {
            ganarVidaExtra();
            puntosUltimaVidaExtra += PUNTOS_VIDA_EXTRA;
        }
    }

    public void ganarVidaExtra() {
        this.vidas++;
    }

    public boolean estaVivo()   { return vidas > 0; }
    public int getVidas()       { return vidas; }
    public float getEnergia()   { return energia; }
    public int getPuntos()      { return puntos; }
}
