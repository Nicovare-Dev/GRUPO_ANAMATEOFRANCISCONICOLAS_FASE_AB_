import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Juego {
    private static final int   BONUS_NIVEL        = 300;
    private static final float ALTITUD_DRONES     = 5000f;
    private static final int   FRAMES_ENTRE_DRONES = 40; // cada cuantos tics aparece un dron nuevo (ajustable)

    private Nivel nivel;
    private Jugador jugador;
    private Avion avion;
    private Escuadron escuadron;
    private List<Misil> misiles;
    private float anchoPantalla;
    private int contadorSpawn;

    public Juego(float anchoPantalla) {
        this.anchoPantalla = anchoPantalla;
        this.nivel         = new Nivel();
        this.jugador       = new Jugador();
        this.avion         = new Avion();
        this.misiles       = new ArrayList<>();
        this.escuadron     = new Escuadron();
        this.contadorSpawn = 0;
    }

    // Avanza un tic de la simulacion: lo llama el game loop de la ventana en cada frame
    public void update() {
        if (finDelJuego()) {
            return;
        }

        // Spawn de drones: cada FRAMES_ENTRE_DRONES tics, si hay lugar, entra uno nuevo
        contadorSpawn++;
        if (contadorSpawn >= FRAMES_ENTRE_DRONES && escuadron.puedeLanzarSiguienteDron()) {
            escuadron.lanzarDron(crearDron());
            contadorSpawn = 0;
        }

        // Drones: se mueven, los que terminaron el recorrido se desactivan, el resto puede disparar
        for (Dron dron : escuadron.getDronesActivos()) {
            dron.mover();
            if (dron.recorridoCompleto()) {
                dron.desactivar();
            } else if (dron.debeDispararMisil()) {
                misiles.add(dron.dispararMisil());
            }
        }

        // Misiles: caen y, al alcanzar su altitud de detonacion, explotan y se quitan
        Iterator<Misil> it = misiles.iterator();
        while (it.hasNext()) {
            Misil misil = it.next();
            misil.mover();
            if (misil.debeDetonar()) {
                misil.procesarExplosion(avion, jugador);
                it.remove();
            }
        }

        //  Sacar de la lista los drones ya desactivados
        escuadron.removerDronesInactivos();
        //
        //
        //  Si el escuadron termino (10 drones enviados y ninguno en vuelo), sube de nivel
        if (escuadron.escuadronTerminado()) {
            avanzarNivel();
            escuadron = new Escuadron();
        }
    }

    // Crea un dron con los parametros del nivel actual
    private Dron crearDron() {
        return new Dron(
            nivel.getVelocidadDrones(),
            nivel.getFrecuenciaDisparo(),
            nivel.getVelocidadMisiles(),
            anchoPantalla,
            ALTITUD_DRONES);
    }

    public void avanzarNivel() {
        nivel.avanzar();                  // numero++ y velocidades x1. por cada vez q se pasa de nvl
        jugador.sumarPuntos(BONUS_NIVEL); // 300 puntos por superar el nvl
    }

    public Nivel getNivel()       { return nivel; }
    public Jugador getJugador()   { return jugador; }
    public Avion getAvion()       { return avion; }
    public float getAnchoPantalla() { return anchoPantalla; }

    public int dronesActivos() {
        return escuadron.getDronesActivos().size();
    }

    public boolean finDelJuego() {
        return !jugador.estaVivo();
    }
}
