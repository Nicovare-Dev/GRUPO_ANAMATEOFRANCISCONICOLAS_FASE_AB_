public class Explosion {
    // El daño se mide en pixeles (lo que se ve). La altitud va en metros:
    // 4000 m se dibujan en 460 px, de ahi este factor metro a pixel.
    private static final float PX_POR_METRO_VERTICAL = 460f / 4000f;
    // Frames que dura visible la explosion
    private static final int FRAMES_VISIBLE = 14;

    private float posicion;
    private float altitud;
    private int vida;

    public Explosion(float posicion, float altitud) {
        this.posicion = posicion;
        this.altitud  = altitud;
        this.vida     = FRAMES_VISIBLE;
    }

    public float calcularDistancia(Avion avion) {
        // Distancia en pixeles entre la explosion y el avion
        float dx = posicion - avion.getPosicion();                          // horizontal
        float dy = (altitud - avion.getAltitud()) * PX_POR_METRO_VERTICAL;  // altitud a px
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    // --- Ciclo de vida para animar la explosion ---
    public void envejecer()      { vida--; }
    public boolean estaVisible() { return vida > 0; }
    public int getVida()         { return vida; }
    public int getVidaInicial()  { return FRAMES_VISIBLE; }

    public float getPosicion() { return posicion; }
    public float getAltitud()  { return altitud; }
}
