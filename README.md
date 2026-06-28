Cincuentazo - Mini Proyecto #3 es una aplicación desarrollada en Java con JavaFX que simula un juego de cartas entre un jugador humano y una máquina. 
El objetivo es evitar perder al alcanzar ciertos valores acumulados mediante decisiones estratégicas.

El sistema está construido bajo el patrón MVC (Modelo - Vista - Controlador), separando la lógica del juego, la interfaz gráfica y el manejo de eventos.
La lógica se encuentra en el modelo con clases como Game, Player, Machine y Deck; la interacción se maneja en los controladores como GameController; y la interfaz gráfica se implementa con FXML en la vista.

El proyecto utiliza hilos para simular el turno de la máquina, permitiendo pausas sin congelar la interfaz, mediante el uso de Thread y Platform.runLater. 
Se emplean estructuras de datos como ArrayList para gestionar cartas y jugadores.

Para ejecutarlo, se debe clonar el repositorio, abrirlo en un IDE como IntelliJ o Eclipse, ejecutar la clase Main.java y contar con JavaFX configurado.
Este proyecto fue desarrollado con fines académicos aplicando conceptos de programación orientada a objetos, manejo de eventos y concurrencia.
