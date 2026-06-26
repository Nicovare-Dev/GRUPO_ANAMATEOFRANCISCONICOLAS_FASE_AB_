import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Juego {
    private static final int   BONUS_NIVEL        = 300;
    private static final float ALTITUD_DRONES     = 5000f;
    private static final int   FRAMES_ENTRE_DRONES = 60; // tics entre cada dron nuevo

    private Nivel nivel;
    private Jugador jugador;
    private Avion avion;
    private Escuadron escuadron;
    private List<Misil> misiles;
    private List<Explosion> explosiones;
    private float anchoPantalla;
    private int contadorSpawn;

    public Juego(float anchoPantalla) {
        this.anchoPantalla = anchoPantalla;
        this.nivel         = new Nivel();
        this.jugador       = new Jugador();
        this.avion         = new Avion();
        this.misiles       = new ArrayList<>();
        this.explosiones   = new ArrayList<>();
        this.escuadron     = new Escuadron();
        this.contadorSpawn = 0;
    }

    // Avanza un tic del juego (lo llama el game loop en cada frame)
    public void update() {
        if (finDelJuego()) {
            return;
        }

        // Spawn: cada cierto tiempo entra un dron nuevo si hay lugar
        contadorSpawn++;
        if (contadorSpawn >= FRAMES_ENTRE_DRONES && escuadron.puedeLanzarSiguienteDron()) {
            escuadron.lanzarDron(crearDron());
            contadorSpawn = 0;
        }

        // Drones: se mueven, se desactivan al cruzar y pueden disparar
        for (Dron dron : escuadron.getDronesActivos()) {
            dron.mover();
            if (dron.recorridoCompleto()) {
                dron.desactivar();
            } else if (dron.debeDispararMisil()) {
                misiles.add(dron.dispararMisil());
            }
        }

        // Misiles: caen y explotan al llegar a su altitud
        Iterator<Misil> it = misiles.iterator();
        while (it.hasNext()) {
            Misil misil = it.next();
            misil.mover();
            if (misil.debeDetonar()) {
                explosiones.add(misil.procesarExplosion(avion, jugador));
                it.remove();
            }
        }

        // Explosiones: se descartan al dejar de verse
        Iterator<Explosion> ie = explosiones.iterator();
        while (ie.hasNext()) {
            Explosion explosion = ie.next();
            explosion.envejecer();
            if (!explosion.estaVisible()) {
                ie.remove();
            }
        }

        escuadron.removerDronesInactivos();

        // Si el escuadron termino, sube de nivel
        if (escuadron.escuadronTerminado()) {
            avanzarNivel();
            escuadron = new Escuadron();
        }
    }

    // Crea un dron con los datos del nivel actual
    private Dron crearDron() {
        return new Dron(
            nivel.getVelocidadDrones(),
            nivel.getFrecuenciaDisparo(),
            nivel.getVelocidadMisiles(),
            anchoPantalla,
            ALTITUD_DRONES);
    }

    public void avanzarNivel() {
        nivel.avanzar();                  // sube nivel y velocidades
        jugador.sumarPuntos(BONUS_NIVEL); // 300 puntos por superarlo
    }

    public Nivel getNivel()       { return nivel; }
    public Jugador getJugador()   { return jugador; }
    public Avion getAvion()       { return avion; }
    public float getAnchoPantalla() { return anchoPantalla; }

    public int dronesActivos() {
        return escuadron.getDronesActivos().size();
    }

    // Para que la vista dibuje las entidades
    public List<Dron> getDrones()           { return escuadron.getDronesActivos(); }
    public List<Misil> getMisiles()         { return misiles; }
    public List<Explosion> getExplosiones() { return explosiones; }

    public boolean finDelJuego() {
        return !jugador.estaVivo();
    }
}
