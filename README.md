# INTRODUCCIÓN

Esta aplicación es una implementación del Monopoly en Java. El objetivo fue aprender este lenguaje de programación, así como conocer las bases del paradigma orientado a objetos.
La forma de interactuar es por línea de comandos. El juego tiene una lista de funciones que se pueden realizar, e imprimirá el tablero (y los cambios producidos) después de cada una de ellas.

# LA PARTIDA

Para empezar, cada jugador indica su nombre y su avatar, habiendo un mínimo de dos.
Por turnos, realizarán las acciones que consideren oportunas: podrán moverse, comprar casillas, edificar, hipotecar... Las opciones disponibles se mostrarán escribiendo "ayuda". para terminar, deberán escribir "acabar turno". Solo podrán moverse una única vez, y deberán hacerlo antes de acabar el turno.
Al caer en una casilla propiedad de otro usuario, se realizará automáticamente un pago.
Usando el comando "listar", los usuarios tienen acceso a toda la información sobre el estado de las casillas, jugadores, avatares...
En el caso de que un jugador pierda todo su dinero, podrá hipotecar casillas para recuperarlo o declararse en bancarrota. En este último caso, sus propiedades pasarán al jugador con el que tenía deudas.
La partida termina cuando solo queda un jugador.

# LOS AVATARES

Existen 4 avatares, con sus movimientos especiales.
  - Coche: Puede lanzar dados hasta 3 veces. Si saca menos de un 5, deberá retroceder esa cantidad y finalizar su turno.
  - Pelota: Puede dividir su movimiento en saltos de 2 casillas.
  - Sombrero: se moverá por el tablero de izquierda a derecha, como si estuviese rebotando en los laterales.
  - Esfinge: se moverá por el tablero de arriba abajo, como si estuviese rebotando en la parte superior e inferior.
  
# EL CÓDIGO

Existe una clase para cada elemento físico: Jugador, Avatar, Casilla, Carta, Tablero... Además, hay otras para elementos más abstractos como "Juego", o de excepciones. Se ha empleado frecuentemente la herencia para estructurar las clases.
