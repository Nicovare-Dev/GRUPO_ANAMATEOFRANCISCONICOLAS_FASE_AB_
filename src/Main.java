public class Main {
    public static void main(String[] args) {
        // Crear el jugador y darle puntos / quitarle una vida
        Jugador jugador = new Jugador();
        jugador.sumarPuntos(100);
        jugador.perderVida();
        jugador.ganarVidaExtra();

        // Crear el avión del jugador y moverlo
        Avion avion = new Avion();
        avion.mover(Direccion.DERECHA);
        avion.cambiarAltitud(250f);

        // Crear un escuadrón y lanzarle un par de drones (máx. 4 en vuelo)
        Escuadron escuadron = new Escuadron();
        escuadron.lanzarDron(new Dron(100f, 2f, 150f, 1000f, 5000f));
        escuadron.lanzarDron(new Dron(100f, 2f, 150f, 1000f, 5000f));

        // Crear un misil y comprobar si debe detonar
        Misil misil = new Misil(5000f, 50f, 0f);
        misil.mover();
        boolean detona = misil.debeDetonar();

        // Armar el juego y avanzar de nivel
        Juego juego = new Juego();
        juego.avanzarNivel();

        System.out.println("Drones activos: " + juego.dronesActivos());
        System.out.println("El misil debe detonar: " + detona);
        System.out.println("Fin del juego: " + juego.finDelJuego());
    }
}
