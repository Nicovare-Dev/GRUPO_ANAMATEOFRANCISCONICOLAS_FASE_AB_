public class Dron extends EntidadVoladora {
    private float altitud;
    private float frecuenciaDisparo;
    private float velocidadMisiles;
    private boolean activo;
    private Direccion direccion;

    public Dron(float velocidad, float frecuenciaDisparo, float velocidadMisiles, float anchoPantalla, float altitud) {
        this.velocidad         = velocidad;
        this.frecuenciaDisparo = frecuenciaDisparo;
        this.velocidadMisiles  = velocidadMisiles;
        this.activo            = true;
        this.altitud           = altitud;
        if (Math.random() < 0.5) {
            this.posicion  = 0f;
            this.direccion = Direccion.DERECHA;
        } else {
            this.posicion  = anchoPantalla;
            this.direccion = Direccion.IZQUIERDA;
        }
    }

    @Override
    public void mover() {
        if (direccion == Direccion.DERECHA) {
            posicion += velocidad;
        } else {
            posicion -= velocidad;
        }

    }

    public Misil dispararMisil() {
        return new Misil(altitud, velocidadMisiles);
    }

    public void desactivar()           { activo = false; }
    public boolean estaActivo()        { return activo; }
    public float getPosicion()         { return posicion; }
    public float getFrecuenciaDisparo(){ return frecuenciaDisparo; }
}
