public class Controlador {
    private Juego juego;

    public Controlador() {
        juego = new Juego(1000f);
    }

    public void moverAvion(Direccion dir)   { juego.getAvion().mover(dir); }
    public void cambiarAltitud(float alt)   { juego.getAvion().cambiarAltitud(alt); }
    public void avanzarNivel()              { juego.avanzarNivel(); }

    public int getPuntos()        { return juego.getJugador().getPuntos(); }
    public int getVidas()         { return juego.getJugador().getVidas(); }
    public float getEnergia()     { return juego.getJugador().getEnergia(); }
    public int getNivelActual()   { return juego.getNivel().getNumero(); }
    public int getDronesActivos() { return juego.dronesActivos(); }
    public boolean finDelJuego()  { return juego.finDelJuego(); }
}
