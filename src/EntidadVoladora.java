public abstract class EntidadVoladora {
    protected float posicion;
    protected float velocidad;
    protected float altitud;

    protected EntidadVoladora(float posicion, float velocidad, float altitud) {
        this.posicion  = posicion;
        this.velocidad = velocidad;
        this.altitud   = altitud;
    }

    public abstract void mover();

    public float getAltitud()  { return altitud; }
    public float getPosicion() { return posicion; }
}
