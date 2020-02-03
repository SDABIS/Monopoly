/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author miguel
 */
public class Tablero {
        
    private ArrayList<ArrayList<Casilla>> casillas;
    private HashMap<String, Jugador> jugadores;
    private Dado dados;
    private HashMap<String, Avatar> avatares;
    private double parking;
    private HashMap<String, Grupo> grupos;
    private int minimoVueltas;
    
    
    
    
    
    
    public Tablero(){                         //Constructor del tablero, crea las 40 casillas y las propiedades las pone a nombre de la banca, y los grupos
        casillas = new ArrayList<>();
        grupos = new HashMap<>();
        jugadores = new HashMap<>();
        dados = new Dado();
        avatares = new HashMap<>();
        parking = 0.0;
        minimoVueltas = 0;
        
        for (int i = 0; i<4; i++) {
            ArrayList<Casilla> fila = new ArrayList<>();                 
            casillas.add(fila);
        }
        
        jugadores.put("Banca", new Jugador(this, "Banca"));
        
        
        grupos.put("negro", new Grupo("negro", this));
        grupos.put("rojo", new Grupo("rojo", this));
        grupos.put("verde", new Grupo("verde", this));
        grupos.put("amarillo", new Grupo("amarillo", this));
        grupos.put("azul", new Grupo("azul", this));
        grupos.put("morado", new Grupo("morado", this));
        grupos.put("cyan", new Grupo("cyan", this));
        grupos.put("blanco", new Grupo("blanco", this));
        
        casillas.get(0).add(new Casilla(this, "SALIDA", "salida"));
        casillas.get(0).add(new Casilla(this, "Budapest", grupos.get("negro")));
        casillas.get(0).add(new Casilla(this, "Caja1", "caja"));
        casillas.get(0).add(new Casilla(this, "Bagdad", grupos.get("negro")));
        casillas.get(0).add(new Casilla(this, "21%_IVA", "impuesto"));
        casillas.get(0).add(new Casilla(this, "Renfe", "transporte"));
        casillas.get(0).add(new Casilla(this, "Madrid", grupos.get("rojo")));                         
        casillas.get(0).add(new Casilla(this, "Suerte1", "suerte"));         
        casillas.get(0).add(new Casilla(this, "Minsk", grupos.get("rojo")));
        casillas.get(0).add(new Casilla(this, "Manila", grupos.get("rojo")));
        casillas.get(1).add(new Casilla(this, "CARCEL", "carcel"));
        casillas.get(1).add(new Casilla(this, "Estocolmo", grupos.get("verde")));
        casillas.get(1).add(new Casilla(this, "Eolica", "servicio"));
        casillas.get(1).add(new Casilla(this, "El_Cairo", grupos.get("verde")));
        casillas.get(1).add(new Casilla(this, "Erevan", grupos.get("verde")));
        casillas.get(1).add(new Casilla(this, "Uber", "transporte"));
        casillas.get(1).add(new Casilla(this, "Amsterdam", grupos.get("amarillo")));
        casillas.get(1).add(new Casilla(this, "Caja2", "caja"));
        casillas.get(1).add(new Casilla(this, "Atenas", grupos.get("amarillo")));
        casillas.get(1).add(new Casilla(this, "Ankara", grupos.get("amarillo")));
        casillas.get(2).add(new Casilla(this, "PARKING", "parking"));
        casillas.get(2).add(new Casilla(this, "Damasco", grupos.get("azul")));
        casillas.get(2).add(new Casilla(this, "Suerte2", "suerte"));
        casillas.get(2).add(new Casilla(this, "Dublin", grupos.get("azul")));
        casillas.get(2).add(new Casilla(this, "Dakar", grupos.get("azul")));
        casillas.get(2).add(new Casilla(this, "Monbus", "transporte"));
        casillas.get(2).add(new Casilla(this, "Londres", grupos.get("morado")));
        casillas.get(2).add(new Casilla(this, "Lima", grupos.get("morado")));
        casillas.get(2).add(new Casilla(this, "Biomasa", "servicio"));
        casillas.get(2).add(new Casilla(this, "Lisboa", grupos.get("morado")));
        casillas.get(3).add(new Casilla(this, "IR_CARCEL", "ircarcel"));
        casillas.get(3).add(new Casilla(this, "Tokio", grupos.get("cyan")));
        casillas.get(3).add(new Casilla(this, "Tallin", grupos.get("cyan")));
        casillas.get(3).add(new Casilla(this, "Caja3", "caja"));
        casillas.get(3).add(new Casilla(this, "Teheran", grupos.get("cyan")));
        casillas.get(3).add(new Casilla(this, "Ryanair", "transporte"));
        casillas.get(3).add(new Casilla(this, "Suerte", "suerte"));
        casillas.get(3).add(new Casilla(this, "Pekin", grupos.get("blanco")));
        casillas.get(3).add(new Casilla(this, "IRPF", "impuesto"));
        casillas.get(3).add(new Casilla(this, "Paris", grupos.get("blanco")));
        
       
        
    }
    
  
    //GETTERS
    
    
    public ArrayList<ArrayList<Casilla>> getCasillas(){         
        
        ArrayList<ArrayList<Casilla>> listaCasillas = new ArrayList<>();
        for(int i=0; i<4; i++){
            ArrayList<Casilla> fila = new ArrayList<>();
            listaCasillas.add(fila);
            for(int j=0; j<10; j++){
                fila.add(casillas.get(i).get(j));
            }
        }
        
        return listaCasillas;
        
    }
    
    public HashMap<String, Jugador> getJugadores(){
        return jugadores;
    }
    
    public Dado getDados() {
        return dados;
    }
    
    public int getMinimoVueltas() {
        return minimoVueltas;
    }
    
    
    public HashMap<String, Avatar> getAvatares(){
        return avatares;
    }
    
    public double getValorParking(){
        return parking;
    }
    
    public HashMap<String, Grupo> getGrupos() {
        return grupos;
    }
    
    
    public void anadirJugador(Jugador jug){           
        jugadores.put(jug.getNombre(), jug);
    }
    
    public void anadirAvatar(Avatar av){
        avatares.put(av.getId(), av);
    }
    
    public void anadirVuelta() {
        minimoVueltas++;
    }
    
    public void setParking(double cantidad){
        if(cantidad > 0){
            parking+=cantidad;
        }
        else{
            System.out.println("Introduzca una cantidad valida");
        }
    }
    
    //Los demás atributos son constantes, así que no necesitan setters
    
    public void eliminarJugador(Jugador jug){
        jugadores.remove(jug.getNombre());
    }
    
    public void eliminarAvatar(Avatar av){
        avatares.remove(av.getId());
    }
    
    private void imprimirCasilla(Casilla cas){
        Collection<Avatar> avatrs = avatares.values();
        
        int espaciosVacios = 0;
        if (cas.getTipoCasilla().equals("solar")) {
            System.out.print("\t|" + cas.getGrupo().getColor() + cas.getNombre() + Constantes.COLOR_RESET);
        } else {
            System.out.print("\t|" + Constantes.COLOR_NEGRO + cas.getNombre() + Constantes.COLOR_RESET);
        }
        for (int i = 0; i<(10 - cas.getNombre().length()); i++) { //Todas las casillas deben ocupar 10 caracteres, asi que se añaden espacios en blanco
            System.out.print(" ");
        }
        for(Avatar avat : avatrs) { //Si hay un avatar, se imprime su ID
            if (avat.getPosicion().getNombre().equals(cas.getNombre())) {
                System.out.print(" &" + avat.getId());                          
            } else {
                espaciosVacios++; //Si no, se guarda registro de que hay que añadir espacios en blanco
            }
        }
        for(int i = 0; i<espaciosVacios; i++) {
            System.out.print("   ");
        }
    }
    
    private void imprimirCasilla(Casilla cas, int numJug){ //Lo mismo que la anterior, para la columna de la derecha
        Collection<Avatar> avatrs = avatares.values();

        if(numJug <= 1) {   //Para cuadrar la fila se añaden tabulaciones según el número de jugadores
            for (int i = 0; i<9; i++) {
                System.out.print("\t\t");
            }
        } else if (numJug <= 4) {
            for (int i = 0; i<9; i++) {
                System.out.print("\t\t\t");
            }
        } else {
            for (int i = 0; i<9; i++) {
                System.out.print("\t\t\t\t");
            }
        }
        int espaciosVacios = 0;
        //A PARTIR DE AQUÍ: IGUAL QUE EN LA SIGUIENTE FUNCIÓN
        if (cas.getTipoCasilla().equals("solar")) {
            System.out.print("\t|" + cas.getGrupo().getColor() + cas.getNombre() + Constantes.COLOR_RESET);
        } else {
            System.out.print("\t|" + Constantes.COLOR_NEGRO + cas.getNombre() + Constantes.COLOR_RESET);
        }
        for (int i = 0; i<(10 - cas.getNombre().length()); i++) {
            System.out.print(" ");
        }
        for(Avatar avat : avatrs) {
            if (avat.getPosicion().getNombre().equals(cas.getNombre())) {
                System.out.print(" &" + avat.getId());                          
            } else {
                espaciosVacios++;                                   
            }
        }
        for(int i = 0; i<espaciosVacios; i++) {
            System.out.print("   ");
        }
    }
    
    public void imprimirTablero(){
        
        int numeroJugadores = 0;
        Collection<Avatar> avatrs = avatares.values();
        
        for(Avatar avat : avatrs) {
            numeroJugadores++;
        }
        System.out.println("\n\n");
        
        for (int i = 0; i<10; i++) { //Imprimir la primera fiilas 4 y 2, las verticales.la
            this.imprimirCasilla(casillas.get(0).get(i));
        }
        this.imprimirCasilla(casillas.get(1).get(0)); //Imprimir la Carcel
        
        System.out.println();
        
        for(int i = 1; i<10; i++) { //Imprimir las columnas laterales
            this.imprimirCasilla(casillas.get(3).get(10 - i));
            this.imprimirCasilla(casillas.get(1).get(i), numeroJugadores);      //Estas son las casillas que seran imprimidas en blanco, las del centro del tablero
            System.out.println();
        }
        
        this.imprimirCasilla(casillas.get(3).get(0));          //Imprimir ircarcel
        
        for (int i = 1; i<11; i++) {     //Imprimir la fila de abajo
            this.imprimirCasilla(casillas.get(2).get(10 - i));
        }
        
        System.out.println("\n\n\n");
    }
    
    public void desplazarAvatar(Avatar av, int movimiento) {             //Función que desplaza al avatar en el tablero, actualizando toda la información necesaria
        int posicionI=0, posicionJ=0, posFinalI=0, posFinalJ=0;
        int vueltasJug = av.getJugador().getVueltas();
        
        
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j++) {
                if(av.getPosicion().getNombre().equals(casillas.get(i).get(j).getNombre())) {
                    posicionI = i;
                    posicionJ = j;
                }
            }
        }
        
        posFinalJ = (posicionJ + movimiento)%10;                     //Solucionamos el problema de mover el avatar entre diferentes filas o columnas
        posFinalI = (posicionI + (posicionJ + movimiento)/10)%4;
        
        if(posicionI > posFinalI) { //Aumentar numero de vueltas
            av.getJugador().setVueltas(av.getJugador().getVueltas() + 1);
            av.getJugador().anadirDinero(Constantes.CANTIDAD_SALIDA);
            
            Collection<Jugador> jugs = jugadores.values();
        
            for (Jugador jug : jugs) {                              //Añadimos las vueltas de cada jugador, para actualizar la inflacion del 5%
                if(vueltasJug < jug.getVueltas()) {
                    vueltasJug = jug.getVueltas();
                }
            }
            if (vueltasJug > minimoVueltas) {
                anadirVuelta();
            }
        }
        
        av.getPosicion().getJugadores().remove(av.getId());        //Actualizamos posiciones del avatar
        av.setPosicion(this.getCasillas().get(posFinalI).get(posFinalJ));
        av.getPosicion().getJugadores().put(av.getId(), av);
        
    }
    
    public int mover(Jugador jug, int numeroDobles){               //Funcion que llama a la anterior para mover al avatar, y evalúa qué pasa después de eso, dependiendo de la casilla en la que caiga
        int dobles = 0, movimiento, saleCarcel = 0;
        
        dados.tirarDados();
        movimiento = dados.getValor1() + dados.getValor2();
        if (dados.sonIguales()) {
            dobles = 1;
        }
        if(dobles == 1){                         //Evaluación de todo lo relacionado con la cárcel
            
            numeroDobles++;
            if(jug.getEstaEnCarcel()){
                saleCarcel = 1;
            }
            jug.setEstaEnCarcel(false);
            
            if(numeroDobles == 3) {
                jug.entrarEnCarcel();
                return dobles;
            } else {
                desplazarAvatar(jug.getAvatar(), movimiento);
            }
        } 
        else if(!jug.getEstaEnCarcel()) {
            desplazarAvatar(jug.getAvatar(), movimiento);
            jug.setPuedeTirar(false);
        }
        else {
            System.out.println("El jugador no ha sacado dobles, por lo que sigue en la cárcel.");
        }
        
        imprimirTablero();
        if(dobles == 1){
            System.out.println("Dados dobles!!! Puede volver a tirar");
        }
        if(saleCarcel == 1){
            System.out.println(jug.getNombre() + " sale de la carcel");
        }
        
        Casilla posicion = jug.getAvatar().getPosicion();
        
        if(posicion.getTipoCasilla().equals("impuesto")){     //Si cae en un impuesto se paga lo necesario
            if(posicion.getNombre().equals("21%_IVA")){
                if(jug.getDineroActual() >= Constantes.PRECIO_IMPUESTO1){
                    System.out.println(jug.getNombre() + " paga " + Constantes.PRECIO_IMPUESTO1 + " como impuesto, y lo deposita en el Parking");
                    jug.quitarDinero(Constantes.PRECIO_IMPUESTO1);
                    parking += Constantes.PRECIO_IMPUESTO1;
                }
                else{
                    System.out.println(jug.getNombre() + " no puede pagar el impuesto, por lo que entra en bancarrota.");
                    jug.bancarrota(jugadores.get("Banca"));
                }
            }
            if(posicion.getNombre().equals("IRPF")){
                if(jug.getDineroActual() >= Constantes.PRECIO_IMPUESTO2){
                    System.out.println(jug.getNombre() + " paga " + Constantes.PRECIO_IMPUESTO2 + " como impuesto, y lo deposita en el Parking");
                    jug.quitarDinero(Constantes.PRECIO_IMPUESTO1);
                    parking += Constantes.PRECIO_IMPUESTO1;
                }
                else{
                    System.out.println(jug.getNombre() + " no puede pagar el impuesto, por lo que entra en bancarrota.");
                    jug.bancarrota(jugadores.get("Banca"));
                }
            }
        }
        else if(posicion.getTipoCasilla().equals("ircarcel")){        //Envía al avatar a la cárcel
            jug.entrarEnCarcel();
        }
        else if(posicion.getTipoCasilla().equals("parking")) {        //Entrega el dinero del párking al jugador
            if(parking > 0){
                System.out.println(jug.getNombre() + " ha caído en el Parking, y se lleva el bote!!!!!!!   " + parking + "€");
            }
            else{
                System.out.println("Woops, no habia dinero alguno en el parking :(");
            }
            jug.anadirDinero(parking);
            parking = 0.0;
        }
        
        
        if(posicion.getTipoCasilla().equals("solar")) {               //Si pertenece a otro jugador, se le paga (mirar también si ese jugador tiene todas las casillas de ese grupo)
            if (posicion.getPropietario().getNombre().equals(jug.getNombre())) {
                return dobles;
            }
            int monopolioDeGrupo = 1;
            
            if(!posicion.getPropietario().getNombre().equals("Banca")) {    //Si pertenece a la banca, o al mismo jugador, no se hace nada
                Collection<Casilla> grupo = posicion.getGrupo().getCasillasGrupo().values();
                
                for(Casilla cas : grupo){
                    if(!cas.getPropietario().getNombre().equals(posicion.getPropietario().getNombre())){
                        monopolioDeGrupo = 0;
                    }
                }
                
                if(monopolioDeGrupo == 1){
                    jug.pagarAJugador(posicion.getPropietario(), posicion.getPrecio()*0.2);
                }
                else {
                    jug.pagarAJugador(posicion.getPropietario(), posicion.getPrecio()*0.1);
                }
            }
        } else if(posicion.getTipoCasilla().equals("transporte")) {         //Igual que en solar, pero con las condiciones del transporte
            if (posicion.getPropietario().getNombre().equals(jug.getNombre())) {
                return dobles;
            }
            
            if(!posicion.getPropietario().getNombre().equals("Banca")) {
                int cantidadTransportes = 0;
                for(int i = 0; i<4; i++){
                    for(int j = 0; j < 10; j++) {
                        if(casillas.get(i).get(j).getTipoCasilla().equals("transporte")) {

                            if(casillas.get(i).get(j).getPropietario().getNombre().equals(posicion.getPropietario().getNombre())) {
                                cantidadTransportes++;
                            }
                        }
                    }
                }
                jug.pagarAJugador(posicion.getPropietario(), Constantes.PRECIO_TRANSPORTES * (cantidadTransportes/4.0));
            }
        } else if(posicion.getTipoCasilla().equals("servicio")) {          //Igual que en transportes, pero con las condiciones del servicio
            if (posicion.getPropietario().getNombre().equals(jug.getNombre())) {
                return dobles;
            } 
            
            if(!posicion.getPropietario().getNombre().equals("Banca")) {
                int cantidadServicios = 0;
                for(int i = 0; i<4; i++){
                    for(int j = 0; j < 10; j++) {
                        if(casillas.get(i).get(j).getTipoCasilla().equals("servicios")) {

                            if(casillas.get(i).get(j).getPropietario().getNombre().equals(posicion.getPropietario().getNombre())) {
                                cantidadServicios++;
                            }
                        }
                    }
                }
                if(cantidadServicios == 1) {
                    jug.pagarAJugador(posicion.getPropietario(), 4 * movimiento * Constantes.FACTOR_SERVICIO);
                } else if(cantidadServicios == 2) {
                    jug.pagarAJugador(posicion.getPropietario(), 10 * movimiento * Constantes.FACTOR_SERVICIO);
                }
            }
        }
        return dobles;       //Devuelve si el jugador ha lanzado dobles al menú, para poder dar opción de volver a tirar
    }
}