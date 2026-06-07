# Pendiente - Fase C

## Clases completadas
- [x] Nivel
- [x] Misil
- [x] Avion
- [x] Dron
- [x] Escuadron
- [x] Jugador
- [x] Explosion (distancia euclídea + reglas de daño/puntaje)

## En progreso
- [~] Juego - lógica central del juego
  - [x] constructor (inicializa nivel, jugador, avion)
  - [x] avanzarNivel (sube nivel +15% velocidades y suma 300 puntos)
  - [x] getters (nivel, jugador, avion)
  - [ ] dronesActivos - contar drones activos de los escuadrones
  - [ ] finDelJuego - jugador sin vidas
  - [ ] control del avion (moverAvion / cambiarAltitudAvion)
  - [ ] actualizar() - el "tick": lanzar drones, mover, disparar, explotar, avanzar nivel

## Falta implementar
- [ ] Controlador - intermediario entre el usuario/GUI y el juego
- [ ] Tests - JUnit para verificar la lógica del negocio

## Pendiente de documentación (actualizar diagrama de clases UML)
- [ ] Misil: nuevo parámetro `posicion` en el constructor + método `explotar(): Explosion`
- [ ] Explosion: constructor `Explosion(posicion, altitud)` + getters
- [ ] Asociación Misil -> Explosion
- [ ] Escuadron: reescrito a lanzamiento bajo demanda (`lanzarDron`, `removerDronesInactivos`, `puedeLanzarSiguienteDron`); reemplaza al viejo `agregarDron`/`lanzarSiguiente`
- [ ] Dron: nuevos métodos `recorridoCompleto()` y `debeDispararMisil()`

## Fase Final (26/06)
- [ ] Interfaz gráfica
