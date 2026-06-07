public class Explosion {
    private float posicion;
    private float altitud;

    public Explosion(float posicion, float altitud) {
        this.posicion = posicion;
        this.altitud  = altitud;
    }

    public float calcularDistancia(Avion avion) {
        // Distancia euclidea: el avion esquiva moviendose en horizontal o variando la altitud
        float dx = posicion - avion.getPosicion();
        float dy = altitud   - avion.getAltitud();
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    public float getPosicion() { return posicion; }
    public float getAltitud()  { return altitud; }
}
