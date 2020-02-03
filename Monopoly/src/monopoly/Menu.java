/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author miguel
 */

public class Menu {

    private Tablero tablero;
    private HashMap<String, Jugador> jugadores;
    private int orden;
    private boolean cambiarTurno;
    private Jugador Banca;
    private int numeroDobles;

    public Menu() {
        System.out.println("\t\t\t\tMMMMMMMM               MMMMMMMM     OOOOOOOOO     NNNNNNNN        NNNNNNNN     OOOOOOOOO     PPPPPPPPPPPPPPPPP        OOOOOOOOO     \n"
                + "\t\t\t\tM:::::::M             M:::::::M   OO:::::::::OO   N:::::::N       N::::::N   OO:::::::::OO   P::::::::::::::::P     OO:::::::::OO   \n"
                + "\t\t\t\tM::::::::M           M::::::::M OO:::::::::::::OO N::::::::N      N::::::N OO:::::::::::::OO P::::::PPPPPP:::::P  OO:::::::::::::OO \n"
                + "\t\t\t\tM:::::::::M         M:::::::::MO:::::::OOO:::::::ON:::::::::N     N::::::NO:::::::OOO:::::::OPP:::::P     P:::::PO:::::::OOO:::::::O\n"
                + "\t\t\t\tM::::::::::M       M::::::::::MO::::::O   O::::::ON::::::::::N    N::::::NO::::::O   O::::::O  P::::P     P:::::PO::::::O   O::::::O\n"
                + "\t\t\t\tM:::::::::::M     M:::::::::::MO:::::O     O:::::ON:::::::::::N   N::::::NO:::::O     O:::::O  P::::P     P:::::PO:::::O     O:::::O\n"
                + "\t\t\t\tM:::::::M::::M   M::::M:::::::MO:::::O     O:::::ON:::::::N::::N  N::::::NO:::::O     O:::::O  P::::PPPPPP:::::P O:::::O     O:::::O\n"
                + "\t\t\t\tM::::::M M::::M M::::M M::::::MO:::::O     O:::::ON::::::N N::::N N::::::NO:::::O     O:::::O  P:::::::::::::PP  O:::::O     O:::::O\n"
                + "\t\t\t\tM::::::M  M::::M::::M  M::::::MO:::::O     O:::::ON::::::N  N::::N:::::::NO:::::O     O:::::O  P::::PPPPPPPPP    O:::::O     O:::::O\n"
                + "\t\t\t\tM::::::M   M:::::::M   M::::::MO:::::O     O:::::ON::::::N   N:::::::::::NO:::::O     O:::::O  P::::P            O:::::O     O:::::O\n"
                + "\t\t\t\tM::::::M    M:::::M    M::::::MO:::::O     O:::::ON::::::N    N::::::::::NO:::::O     O:::::O  P::::P            O:::::O     O:::::O\n"
                + "\t\t\t\tM::::::M     MMMMM     M::::::MO::::::O   O::::::ON::::::N     N:::::::::NO::::::O   O::::::O  P::::P            O::::::O   O::::::O\n"
                + "\t\t\t\tM::::::M               M::::::MO:::::::OOO:::::::ON::::::N      N::::::::NO:::::::OOO:::::::OPP::::::PP          O:::::::OOO:::::::O\n"
                + "\t\t\t\tM::::::M               M::::::M OO:::::::::::::OO N::::::N       N:::::::N OO:::::::::::::OO P::::::::P           OO:::::::::::::OO \n"
                + "\t\t\t\tM::::::M               M::::::M   OO:::::::::OO   N::::::N        N::::::N   OO:::::::::OO   P::::::::P             OO:::::::::OO   \n"
                + "\t\t\t\tMMMMMMMM               MMMMMMMM     OOOOOOOOO     NNNNNNNN         NNNNNNN     OOOOOOOOO     PPPPPPPPPP               OOOOOOOOO     \n\n\n");
        tablero = new Tablero();
        jugadores = tablero.getJugadores();

        Banca = jugadores.get("Banca");
        
        Scanner scanner = new Scanner(System.in);            //Creamos los jugadores al principio de la partida, ya que luego no se podrá

        String opcion = "si";                                //Mientras opcion sea si, se siguen creando jugadores
        int cont = 0;
        
        System.out.println("/-/-/-/-/-/-/-/-/-/-/     Bienvenidos a la versión suprema del Monopoly!!!!!!!!!!!     /-/-/-/-/-/-/-/-/-/-/");
        System.out.println("-->Para comenzar la partida, deben crear los jugadores. Una vez creados, ningún jugador nuevo podrá unirse a la partida<--");
        System.out.println("-->Para crear los jugadores, escriban el comando -->[crear + jugador + [Nombre de jugador] + [tipo de avatar]]<--\n\n");
        do {
            System.out.print("$> ");
            String nombre = scanner.nextLine();
            String[] partes = nombre.split(" ");
            if (partes.length == 4) {
                if (partes[1].equals("jugador")) {
                    if (partes[3].equals("coche") || partes[3].equals("pelota") || partes[3].equals("sombrero") || partes[3].equals("esfinge")) {
                        Jugador jug1 = new Jugador(tablero, partes[2], partes[3]);
                        jugadores.put(partes[2], jug1);
                        cont++;
                        System.out.println(jug1);
                        tablero.imprimirTablero();
                        if(cont >= 2 && cont < 6){
                            System.out.println("-->Quiere escribir otro jugador?");
                            opcion = scanner.nextLine();
                        }
                        if (cont == 6) {
                            opcion = "no";
                        }
                    } else {
                        System.out.println("Introduce un avatar valido.");
                    }   
                } else {
                    System.out.println("Solo se pueden crear jugadores.");
                }
            } else {
                System.out.println("Escribe: crear + jugador + [Nombre de jugador] + [tipo de avatar]");
            }
            
        
        } while (opcion.equals("si"));

        System.out.println("Todos los jugadores han sido creados!!!! Ahora empieza la partida.");   //Oficialmente comienza la partida

        orden = 0;
        boolean salir = false;
        while (!salir) {

            orden = (orden + 1) % jugadores.size();       //Calculamos el orden global de la partida, el jugador activo será aquel que su orden coincida con el del menú
            if (orden == 0) {
                orden++;
            }

            Jugador activo = null;

            Iterator<Jugador> jugs = jugadores.values().iterator();
            while (jugs.hasNext()) {
                Jugador jug1 = jugs.next();
                if (jug1.getOrden() == orden) {
                    activo = jug1;
                }
            }

            System.out.println("El jugador actual es: " + activo.getNombre());
            cambiarTurno = false;
            numeroDobles = 0;
            activo.setPuedeTirar(true);
            while (!cambiarTurno) {                //Mientras no elija cambiar turno...
                System.out.print("$> ");
                String funcion = scanner.nextLine();
                String[] partes = funcion.split(" ");
                String comando = partes[0];        //Recogemos su comando, la primera palabra irá al switch, ahí valoraremos las opciones posibles
                
                switch (comando) {
                    
                    case "crear":                  //Para mayor claridad, repetimos la regla de no poder crear jugadores una vez empezada la partida
                        System.out.println("No hay más cosas para crear una vez empezada la partida\n");  
                        System.out.println("Comandos disponibles: "
                                + "\n\t--> listar + avatares/jugadores/enventa"
                                + "\n\t--> describir + [nombre de casilla]/jugador/avatar + [nombre de jugador/ID de avatar]"
                                + "\n\t--> salir carcel (si se está en la cárcel)"
                                + "\n\t--> acabar turno/partida"
                                + "\n\t--> ver tablero"
                                + "\n\t--> comprar [nombre de casilla]"
                                + "\n\t--> lanzar dados\n\n");
                    break;

                    case "listar":                     //Se pueden listar avatares, jugadores o casillas en venta
                        if (partes.length == 1) {
                            System.out.println("Escriba listar + avatares/jugadores, por favor");
                        } else if (partes[1].equals("jugadores")) {
                            listarJugadores();
                        } else if (partes[1].equals("avatares")) {
                            listarAvatares();
                        } else if (partes[1].equals("enventa")) {
                            for (int i = 0; i < 4; i++) {
                                for (int j = 0; j < 10; j++) {
                                    Casilla cas = tablero.getCasillas().get(i).get(j);

                                    if (cas.getTipoCasilla().equals("solar") || cas.getTipoCasilla().equals("transporte") || cas.getTipoCasilla().equals("servicio")) {
                                        if (cas.getPropietario().getNombre().equals("Banca")) {
                                            System.out.println(tablero.getCasillas().get(i).get(j));
                                        }
                                    }
                                }
                            }
                        } else {
                            System.out.println("Escriba listar + avatares/jugadores/enventa, por favor");
                        }
                    break;
                    
                    case "jugador":
                        if (partes.length == 1) {
                            System.out.println(activo);
                        }
                        else {
                            System.out.println("Para obtener la información del jugador activo en este turno, basta con escribir \"jugador\".");
                        }
                    break;
                    
                    case "describir":              //Se pueden describir casillas, jugadores o avatares, imprimiendo información útil para el estado actual del juego
                        if (partes.length == 2) {
                            int encontradoCas = 0;
                            for (int i = 0; i < 4; i++) {
                                for (int j = 0; j < 10; j++) {
                                    if (tablero.getCasillas().get(i).get(j).getNombre().equals(partes[1])) {
                                        System.out.println(tablero.getCasillas().get(i).get(j));
                                        encontradoCas = 1;
                                    }
                                }
                            }
                            if (encontradoCas == 0) {
                                System.out.println("Escribe: describir + [nombre de casilla]");
                            }
                        } else if (partes.length == 3) {
                            if (partes[1].equals("jugador")) {
                                int encontradoJug = 0;
                                Collection<Jugador> jugs1 = jugadores.values();

                                for (Jugador jug1 : jugs1) {
                                    if (jug1.getNombre().equals(partes[2])) {
                                        jug1.imprimirJugador();
                                        encontradoJug = 1;
                                    }
                                }
                                if (encontradoJug == 0) {
                                    System.out.println("Escribe: describir + jugador + [nombre de jugador]");
                                }
                            } else if (partes[1].equals("avatar")) {
                                int encontradoAv = 0;
                                Collection<Jugador> jugs1 = jugadores.values();

                                for (Jugador jug1 : jugs1) {
                                    if (!jug1.getNombre().equals("Banca")) {
                                        if (jug1.getAvatar().getId().equals(partes[2])) {
                                            System.out.println(jug1.getAvatar());
                                            encontradoAv = 1;
                                        }
                                    }
                                }
                                if (encontradoAv == 0) {
                                    System.out.println("Escribe: describir + avatar + [ID de avatar]");
                                }
                            }
                        } else {
                            System.out.println("Escribe: describir + [nombre de casilla]/jugador/avatar + [nombre de jugador/ID de avatar]");
                        }
                    break;

                    case "ver":                             //Imprimir el tablero
                        if (partes[1].equals("tablero")) {
                            tablero.imprimirTablero();
                        } else {
                            System.out.println("Escribe: ver + tablero");
                        }
                        break;

                    case "acabar":                          //Puedes acabar turno, si no puedes tirar los dados
                        if (partes[1].equals("turno")) {
                            if (activo.getPuedeTirar() == false) {
                                cambiarTurno = true;
                                System.out.println("Turno terminado. ");
                            } else {
                                System.out.println("Es necesario tirar los dados antes de acabar el turno.");
                            }
                        } else if(partes[1].equals("partida")) {              //Hemos incluido la opción de acabar partida directamente, terminando con la ejecución del programa. Esta opción puede usarse como escape rápido del programa.
                            salir = true;
                            cambiarTurno = true;
                        } else {
                            System.out.println("Escribe: acabar turno/partida");
                        }
                    break;

                    case "salir":                               //Salir de la cárcel, sólo si se está en ella y tiene el dinero para pagarlo
                        if (partes[1].equals("carcel")) {
                            if (activo.getEstaEnCarcel() == true) {
                                if(!activo.getPuedeTirar()){
                                    System.out.println("No se puede pagar para salir de la cárcel si ya se han tirado los dados");
                                }
                                else{
                                    if (activo.getDineroActual() >= Constantes.PRECIO_SALIR_CARCEL) {
                                        activo.quitarDinero(Constantes.PRECIO_SALIR_CARCEL);
                                        System.out.println(activo.getNombre() + " paga " + Constantes.PRECIO_SALIR_CARCEL + " y sale de la cárcel. Puede lanzar los dados.");
                                        activo.setEstaEnCarcel(false);
                                        activo.setTurnosCarcel(0);
                                    } else {
                                        if (activo.getTurnosCarcel() == 3) {
                                            System.out.println(activo.getNombre() + " no tiene dinero para salir de la carcel. El jugador se declara en bancarrota.");
                                            activo.bancarrota(Banca);
                                        } else {
                                            System.out.println(activo.getNombre() + " no tiene dinero para salir de la carcel.");
                                        }
                                    }
                                }
                            } else {
                                System.out.println(activo.getNombre() + " no está en la carcel.");
                            }
                        } else {
                            System.out.println("Escribe: salir carcel");
                        }
                    break;

                    case "comprar":         //Comprar casilla
                        String tipo = activo.getAvatar().getPosicion().getTipoCasilla();
                        if(tipo.equals("salida") || tipo.equals("impuesto") || tipo.equals("suerte") || tipo.equals("caja") || tipo.equals("carcel") || tipo.equals("ircarcel") || tipo.equals("parking")){
                            System.out.println("No es una casilla comprable");
                        }
                        else{
                            if (activo.getAvatar().getPosicion().getNombre().equals(partes[1])) {
                                activo.comprarCasilla();
                                System.out.println(activo.getNombre() + " ha comprado " + activo.getAvatar().getPosicion().getNombre());
                                listarJugadores();
                            } 
                            else{
                                int encontrado = 0;
                                for (int i = 0; i < 4; i++) {
                                    for (int j = 0; j < 10; j++) {
                                        if (tablero.getCasillas().get(i).get(j).getNombre().equals(partes[1])) {
                                            encontrado = 1;
                                        }
                                    }
                                }
                                if(encontrado == 0){
                                    System.out.println("No existe la casilla " + partes[1]);
                                }
                                else{
                                    System.out.println("El jugador no está en la casilla " + partes[1]);
                                }
                            }
                        }
                    break;

                    case "lanzar":         //Lanzar dados, llamando a la función mover en tablero.java
                        if (partes[1].equals("dados")) {
                            if(activo.getPuedeTirar() == true) {
                                if(tablero.mover(activo, numeroDobles) == 1) {
                                    numeroDobles++;
                                }
                                System.out.println("Valor dado 1: " + tablero.getDados().getValor1() + "    Valor dado 2: " + tablero.getDados().getValor2());
                                activo.imprimirJugador();
                                
                            }
                            else {
                                System.out.println("El jugador no puede tirar.");
                            }
                        } else {
                            System.out.println("Escribe: lanzar dados");
                        }
                        
                        
                    break;
                    
                    
                    default:          //Si no se escribe ningún comando cuya primera palabra esté disponible, se abre un menú de ayuda con todos los comandos del juego
                        System.out.println("Comandos disponibles: "
                                + "\n\t--> listar + avatares/jugadores/enventa"
                                + "\n\t--> describir + [nombre de casilla]/jugador/avatar + [nombre de jugador/ID de avatar]"
                                + "\n\t--> salir carcel"
                                + "\n\t--> acabar turno/partida"
                                + "\n\t--> ver tablero"
                                + "\n\t--> comprar [nombre de casilla]"
                                + "\n\t--> jugador (describe al jugador del turno actual)"
                                + "\n\t--> lanzar dados\n\n");
                }
                
            }
            if(activo.getEstaEnCarcel() == true) {           //Si acabas el turno y estás en la carcel, se suma un turno en la cárcel. Si llevas 3 turnos, pagas automáticamente para salir. Si no tienes dinero, entras en bancarrota   
                activo.setTurnosCarcel(activo.getTurnosCarcel() + 1);
                if(activo.getTurnosCarcel() == 3) {

                    if (activo.getDineroActual() >= Constantes.PRECIO_SALIR_CARCEL) {
                        activo.quitarDinero(Constantes.PRECIO_SALIR_CARCEL);

                        System.out.println(activo.getNombre() + " paga " + Constantes.PRECIO_SALIR_CARCEL + " y sale de la cárcel. No puede lanzar los dados.");
                        activo.setEstaEnCarcel(false);
                        activo.setTurnosCarcel(0);
                    } else {
                        System.out.println(activo.getNombre() + " no tiene dinero para salir de la carcel. El jugador se declara en bancarrota");
                        activo.bancarrota(Banca);
                    }
                }
            }

            if (jugadores.size() == 2) {
                salir = true;
            }                                      //Partida acabada
        }                                            
        System.out.println("\n\n /$$   /$$  /$$$$$$   /$$$$$$         /$$$$$$   /$$$$$$  /$$   /$$  /$$$$$$  /$$$$$$$   /$$$$$$  /$$ /$$\n"
                + "| $$  | $$ /$$__  $$ /$$__  $$       /$$__  $$ /$$__  $$| $$$ | $$ /$$__  $$| $$__  $$ /$$__  $$| $$| $$\n"
                + "| $$  | $$| $$  \\ $$| $$  \\__/      | $$  \\__/| $$  \\ $$| $$$$| $$| $$  \\ $$| $$  \\ $$| $$  \\ $$| $$| $$\n"
                + "| $$$$$$$$| $$$$$$$$|  $$$$$$       | $$ /$$$$| $$$$$$$$| $$ $$ $$| $$$$$$$$| $$  | $$| $$  | $$| $$| $$\n"
                + "| $$__  $$| $$__  $$ \\____  $$      | $$|_  $$| $$__  $$| $$  $$$$| $$__  $$| $$  | $$| $$  | $$|__/|__/\n"
                + "| $$  | $$| $$  | $$ /$$  \\ $$      | $$  \\ $$| $$  | $$| $$\\  $$$| $$  | $$| $$  | $$| $$  | $$        \n"
                + "| $$  | $$| $$  | $$|  $$$$$$/      |  $$$$$$/| $$  | $$| $$ \\  $$| $$  | $$| $$$$$$$/|  $$$$$$/ /$$ /$$\n"
                + "|__/  |__/|__/  |__/ \\______/        \\______/ |__/  |__/|__/  \\__/|__/  |__/|_______/  \\______/ |__/|__/\n\n\n");
    }

                                            //Lista a los jugadores en el tablero
    public void listarJugadores() {
        Iterator<Jugador> jugs = jugadores.values().iterator();
        String str1 = new String();

        while (jugs.hasNext()) {
            Jugador jug1 = jugs.next();
            if (!jug1.getNombre().equals("Banca")) {
                str1 = str1 + "\n{\n\tnombre: " + jug1.getNombre() + "\n\tavatar: " + jug1.getAvatar().getId() + "\n\tfortuna: " + jug1.getDineroActual() + "\n\tpropiedades: [";
                Iterator<Casilla> props = jug1.getPropiedades().values().iterator();

                while (props.hasNext()) {
                    str1 = str1 + props.next().getNombre();
                    if (props.hasNext()) {
                        str1 = str1 + ", ";
                    }
                }
                str1 = str1 + "]\n}";
                if (jugs.hasNext()) {
                    str1 = str1 + ",";
                }
            }
        }
        System.out.println(str1);
    }
                                             //Lista a los avatares de los jugadores
    public void listarAvatares() {
        Iterator<Jugador> jugs = jugadores.values().iterator();
        String str1 = new String();

        while (jugs.hasNext()) {
            Jugador jug1 = jugs.next();
            if (!jug1.getNombre().equals("Banca")) {
                Avatar av = jug1.getAvatar();
                str1 = str1 + av.toString();

                if (jugs.hasNext()) {
                    str1 = str1 + ",";
                }
            }
        }
        System.out.println(str1);
    }
}