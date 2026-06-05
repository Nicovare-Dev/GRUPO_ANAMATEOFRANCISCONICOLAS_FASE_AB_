public class Nivel {
    private static final float VELOCIDAD_DRONES_BASE    = 100f;
    private static final float VELOCIDAD_MISILES_BASE   = 150f;
    private static final float FRECUENCIA_DISPARO_BASE  = 2f;
    private static final float INCREMENTO               = 1.15f;

    private int numero;
    private float velocidadDrones;
    private float velocidadMisiles;
    private float frecuenciaDisparo;

    public Nivel() {
        this.numero           = 1;
        this.velocidadDrones  = VELOCIDAD_DRONES_BASE;
        this.velocidadMisiles = VELOCIDAD_MISILES_BASE;
        this.frecuenciaDisparo = FRECUENCIA_DISPARO_BASE;
    }

    public void avanzar() {
        numero++;
        velocidadDrones   *= INCREMENTO;
        velocidadMisiles  *= INCREMENTO;
        frecuenciaDisparo *= INCREMENTO;
    }

    public int getNumero()              { return numero; }
    public float getVelocidadDrones()   { return velocidadDrones; }
    public float getVelocidadMisiles()  { return velocidadMisiles; }
    public float getFrecuenciaDisparo() { return frecuenciaDisparo; }
}
