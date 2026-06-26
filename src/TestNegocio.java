// Tests del negocio. Se corren con main() e imprimen OK o FALLA.
public class TestNegocio {
    private static int pasaron  = 0;
    private static int fallaron = 0;

    public static void main(String[] args) {
        testNivel();
        testJugador();
        testAvion();
        testEscuadron();
        testMisil();
        testDron();
        testExplosionYDanio();
        testJuego();

        System.out.println("\n==============================");
        System.out.println("Resultado: " + pasaron + " OK, " + fallaron + " FALLA");
        System.out.println("==============================");
    }

    // --- Reglas de la consigna ----------------------------------------------

    // Cada nivel incrementa las velocidades y la frecuencia en un 15%
    private static void testNivel() {
        titulo("Nivel");
        Nivel n = new Nivel();
        check("Arranca en nivel 1", n.getNumero() == 1);

        float velDrones  = n.getVelocidadDrones();
        float velMisiles = n.getVelocidadMisiles();
        float frecuencia = n.getFrecuenciaDisparo();

        n.avanzar();
        check("Sube al nivel 2", n.getNumero() == 2);
        checkCasi("Velocidad de drones +15%",   velDrones  * 1.15f, n.getVelocidadDrones());
        checkCasi("Velocidad de misiles +15%",  velMisiles * 1.15f, n.getVelocidadMisiles());
        checkCasi("Frecuencia de disparo +15%", frecuencia * 1.15f, n.getFrecuenciaDisparo());
    }

    // Puntos, vidas, energia y vida extra cada 1000 puntos
    private static void testJugador() {
        titulo("Jugador");
        Jugador j = new Jugador();
        check("Arranca con 3 vidas",    j.getVidas() == 3);
        check("Arranca con 100 energia", j.getEnergia() == 100f);
        check("Arranca vivo",            j.estaVivo());

        j.sumarPuntos(40);
        check("Suma puntos sin dar vida extra", j.getPuntos() == 40 && j.getVidas() == 3);

        j.sumarPuntos(960); // llega a 1000
        check("Vida extra al alcanzar 1000 puntos", j.getVidas() == 4);

        Jugador j2 = new Jugador();
        j2.perderEnergia(20);
        checkCasi("Pierde 20% de energia", 80f, j2.getEnergia());
        j2.perderEnergia(100); // la energia baja de 0: se resetea y se pierde una vida
        check("Energia agotada resetea y quita una vida", j2.getVidas() == 2 && j2.getEnergia() == 100f);

        Jugador j3 = new Jugador();
        j3.perderVida();
        j3.perderVida();
        j3.perderVida();
        check("Sin vidas deja de estar vivo", !j3.estaVivo());
    }

    // El avion se mueve y varia altitud entre 1000 y 5000, sin salirse de la pantalla
    private static void testAvion() {
        titulo("Avion");
        Avion a = new Avion();
        check("Arranca centrado",            a.getPosicion() == 500f);
        checkCasi("Arranca a media altitud", 3000f, a.getAltitud());

        a.cambiarAltitud(6000f);
        checkCasi("La altitud no supera 5000", 5000f, a.getAltitud());
        a.cambiarAltitud(500f);
        checkCasi("La altitud no baja de 1000", 1000f, a.getAltitud());

        a.mover(Direccion.DERECHA);
        checkCasi("Se mueve a la derecha", 525f, a.getPosicion());

        Avion a2 = new Avion();
        for (int i = 0; i < 100; i++) {
            a2.mover(Direccion.IZQUIERDA);
        }
        check("No se sale por la izquierda", a2.getPosicion() == 0f);
    }

    // Escuadron de 10 drones, nunca mas de 4 activos a la vez
    private static void testEscuadron() {
        titulo("Escuadron");
        Escuadron e = new Escuadron();
        check("Puede lanzar al inicio", e.puedeLanzarSiguienteDron());

        for (int i = 0; i < 4; i++) {
            e.lanzarDron(nuevoDron());
        }
        check("Llega a 4 drones activos",        e.getDronesActivos().size() == 4);
        check("Con 4 activos no puede lanzar mas", !e.puedeLanzarSiguienteDron());

        for (Dron d : e.getDronesActivos()) {
            d.desactivar();
        }
        e.removerDronesInactivos();
        check("Saca de la lista los drones inactivos", e.getDronesActivos().isEmpty());

        for (int i = 0; i < 6; i++) {   // completa los 10 del escuadron
            e.lanzarDron(nuevoDron());
        }
        for (Dron d : e.getDronesActivos()) {
            d.desactivar();
        }
        e.removerDronesInactivos();
        check("Escuadron terminado tras enviar los 10 drones", e.escuadronTerminado());
    }

    // El misil cae en linea recta y detona a una altitud entre 1200 y 4500
    private static void testMisil() {
        titulo("Misil");
        Misil m = new Misil(5000f, 40f, 500f);
        check("Arranca sin detonar", !m.debeDetonar());

        float altitudPrevia = m.getAltitud();
        m.mover();
        check("Baja de altitud al moverse", m.getAltitud() < altitudPrevia);

        for (int i = 0; i < 200 && !m.debeDetonar(); i++) {
            m.mover();
        }
        check("Termina detonando", m.debeDetonar());
        check("Detona en altitud valida (>= 1200)", m.getAltitud() >= 1160f);
    }

    // El dron arranca en un extremo, cruza la pantalla y dispara al acumular frecuencia
    private static void testDron() {
        titulo("Dron");
        Dron d = nuevoDron();
        check("Arranca activo",              d.estaActivo());
        check("Arranca en un extremo",       d.getPosicion() == 0f || d.getPosicion() == 1000f);
        check("No completo el recorrido aun", !d.recorridoCompleto());

        for (int i = 0; i < 500 && !d.recorridoCompleto(); i++) {
            d.mover();
        }
        check("Completa el recorrido al cruzar", d.recorridoCompleto());

        Dron d2 = nuevoDron();
        boolean disparo = false;
        for (int i = 0; i < 100 && !disparo; i++) {
            disparo = d2.debeDispararMisil();
        }
        check("Dispara tras acumular frecuencia", disparo);
        check("Genera un misil al disparar",      d2.dispararMisil() != null);
    }

    // El daño depende de la distancia entre la explosion y el avion
    private static void testExplosionYDanio() {
        titulo("Explosion y daño");
        Explosion centro = new Explosion(500f, 3000f);
        Avion avion = new Avion(); // posicion 500, altitud 3000
        checkCasi("Distancia 0 cuando coinciden", 0f, centro.calcularDistancia(avion));

        Explosion separada = new Explosion(900f, 3000f);
        check("La distancia crece con la separacion", separada.calcularDistancia(avion) > 150f);

        // Explosion lejana: suma 40 puntos y no hace daño
        Jugador jugador = new Jugador();
        Avion avion2 = new Avion();                  // posicion 500
        Misil lejano = new Misil(3000f, 40f, 900f);  // explota lejos en horizontal
        lejano.procesarExplosion(avion2, jugador);
        check("Explosion lejana suma 40 y no daña",
                jugador.getPuntos() == 40 && jugador.getVidas() == 3 && jugador.getEnergia() == 100f);
    }

    // Al superar un nivel se suman 300 puntos; el juego termina sin vidas
    private static void testJuego() {
        titulo("Juego");
        Juego juego = new Juego(1000f);
        check("Arranca sin drones activos", juego.dronesActivos() == 0);
        check("Arranca sin terminar",       !juego.finDelJuego());

        int puntosPrevios = juego.getJugador().getPuntos();
        juego.avanzarNivel();
        check("Avanzar de nivel suma 300 puntos", juego.getJugador().getPuntos() == puntosPrevios + 300);
        check("Avanzar de nivel sube el numero",  juego.getNivel().getNumero() == 2);

        // Quitar todas las vidas termina el juego
        while (juego.getJugador().estaVivo()) {
            juego.getJugador().perderVida();
        }
        check("Sin vidas el juego termina", juego.finDelJuego());
    }

    // --- Helpers -------------------------------------------------------------

    private static Dron nuevoDron() {
        return new Dron(5f, 0.15f, 40f, 1000f, 5000f);
    }

    private static void titulo(String nombre) {
        System.out.println("\n[" + nombre + "]");
    }

    private static void check(String descripcion, boolean condicion) {
        if (condicion) {
            pasaron++;
            System.out.println("  OK    - " + descripcion);
        } else {
            fallaron++;
            System.out.println("  FALLA - " + descripcion);
        }
    }

    private static void checkCasi(String descripcion, float esperado, float real) {
        check(descripcion, Math.abs(esperado - real) < 0.01f);
    }
}
