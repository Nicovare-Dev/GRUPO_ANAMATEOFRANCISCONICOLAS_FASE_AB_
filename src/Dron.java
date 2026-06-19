public class Dron extends EntidadVoladora {
    private static final float UMBRAL_DISPARO = 10f; // cuanta "frecuencia" acumulada hace falta para disparar

    private float frecuenciaDisparo;
    private float velocidadMisiles;
    private boolean activo;
    private Direccion direccion;
    private float anchoPantalla;
    private float acumuladorDisparo;

    public Dron(float velocidad, float frecuenciaDisparo, float velocidadMisiles, float anchoPantalla, float altitud) {
        super(0f, velocidad, altitud);
        this.frecuenciaDisparo = frecuenciaDisparo;
        this.velocidadMisiles  = velocidadMisiles;
        this.activo            = true;
        this.anchoPantalla     = anchoPantalla;
        this.acumuladorDisparo = 0f;
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

    public boolean recorridoCompleto() {
        // El dron termino su recorrido cuando llego al extremo opuesto al que arranco
        if (direccion == Direccion.DERECHA) {
            return posicion >= anchoPantalla;
        } else {
            return posicion <= 0f;
        }
    }

    public boolean debeDispararMisil() {
        // A mayor frecuencia (sube 15% por nivel), antes se alcanza el umbral y mas seguido dispara
        acumuladorDisparo += frecuenciaDisparo;
        if (acumuladorDisparo >= UMBRAL_DISPARO) {
            acumuladorDisparo -= UMBRAL_DISPARO;
            return true;
        }
        return false;
    }

    public Misil dispararMisil() {
        return new Misil(altitud, velocidadMisiles, posicion);
    }

    public void desactivar()           { activo = false; }
    public boolean estaActivo()        { return activo; }
    public float getFrecuenciaDisparo(){ return frecuenciaDisparo; }
}
