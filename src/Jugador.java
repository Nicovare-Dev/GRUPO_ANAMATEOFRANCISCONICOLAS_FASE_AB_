public class Jugador {
    private int vidas;
    private float energia;
    private int puntos;

    public void perderEnergia(float porcentaje) {
        this.energia -= energia * (porcentaje / 100f);
    }

    public void perderVida() {
        this.vidas--;
    }

    public void sumarPuntos(int pts) {
        this.puntos += pts;
    }

    public void ganarVidaExtra() {
        this.vidas++;
    }
}
