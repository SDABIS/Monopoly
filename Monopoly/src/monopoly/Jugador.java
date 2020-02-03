/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author miguel
 */
public class Jugador {
    
    private Avatar avatar;
    private HashMap<String, Casilla> propiedades;
    private Tablero tablero;
    private String nombre;
    private double dineroActual;
    private double dineroGastado;
    private boolean banca;
    private int turnosCarcel;
    private boolean estaEnCarcel;
    private int vueltas;
    private int orden;
    private boolean puedeTirar;
    
    public Jugador(Tablero tab, String nom, String ficha){        //Constructor para los jugadores en sí, sin contar a la Banca
        if(nom.length() < 2){
            System.out.println("El nombre debe de tener un minimo de 2 caracteres");
            System.exit(0);
        }
           
        Collection<Jugador> jugadores = tab.getJugadores().values();
        
        int maxOrden = 0;
        
        orden = 1;      //Atributo que sirve para decidir el orden de los jugadores a la hora de lanzar dados
        
        for (Jugador jug : jugadores) {
            if (jug.nombre.equals(nom)) {
                System.out.println("Ya existe un jugador con ese nombre.");
                return;
            }
            if (jug.orden > maxOrden) {
                maxOrden = jug.orden;
            }
        }
        orden = maxOrden + 1;          //Según se vayan creando más jugadores, su orden subirá de 1 a 1, empezando por el 1
        
        nombre = nom;
        avatar = new Avatar(this, ficha, tab);
        dineroActual = Constantes.CANTIDAD_INICIAL;
        dineroGastado = 0;
        propiedades = new HashMap<>();
        tablero = tab;
        turnosCarcel = 0;
        estaEnCarcel = false;
        banca = false;
        vueltas = 0;
        
        puedeTirar = true;
        
        tablero.anadirJugador(this);
        
    }
    
    public Jugador(Tablero tab, String nom){      //Constructor específico para la Banca

        nombre = nom;
        banca = true;
        tablero = tab;
        propiedades = new HashMap<>();
        dineroActual = Double.POSITIVE_INFINITY;      //Dinero infinito
        dineroGastado = 0;
        turnosCarcel = 0;
        estaEnCarcel = false;
        puedeTirar = false;
        orden = 0;
        vueltas = 0;
      
        tablero.anadirJugador(this);
    }
    
    
    
    //GETTERS Y SETTERS
    
    
    
    public Avatar getAvatar(){
        return avatar;
    }
    
    public double getDineroActual(){
        return dineroActual;
    }
    
    public double getDineroGastado(){
        return dineroGastado;
    }
    
    public int getVueltas() {
        return vueltas;
    }
    
    public int getTurnosCarcel() {
        return turnosCarcel;
    }
    
    public int getOrden() {
        return orden;
    }
    
    public boolean getPuedeTirar() {
        return puedeTirar;
    }
    
    public boolean getEstaEnCarcel() {
        return estaEnCarcel;
    }
    
    public HashMap<String, Casilla> getPropiedades(){
        return propiedades;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    //Igual que en avatar, no hace falta un getTablero, porque solo hay un tablero y es unico para toda la partida
    
    public void anadirDinero(double cash){
        dineroActual += cash;
    }
    
    public void quitarDinero(double cash){
        dineroActual -= cash;
        dineroGastado += cash;
    }
    
    public void setVueltas(int vuelt) {
        vueltas = vuelt;
    }
    
    public void setOrden(int ord) {
        orden = ord;
    }
    
    public void setPuedeTirar(boolean tirar){
        puedeTirar = tirar;
    }
    
    public void setEstaEnCarcel(boolean carcel){
        estaEnCarcel = carcel;
    }
    
    public void setTurnosCarcel(int num){
        turnosCarcel = num;
    }
    
    public void anadirPropiedad(Casilla cas) {
        propiedades.put(cas.getNombre(), cas);
    }
    
    //Los demás atributos son constantes, así que no necesitan setters
    
    
    @Override
    public String toString(){
        return "\n{\n\tnombre: " + nombre + "\n\tavatar: " + avatar.getId() + "\n}\n";
    }
   
    //Las funcionalidades de hipoteca y edificios aun no están implementadas, asi que no se imprimen
    
    public void comprarCasilla(){             //Función que compra la casilla en la que se encuentra al jugador, solo si esta casilla pertenece a la banca 
        
        Casilla casilla = this.getAvatar().getPosicion();
        
        if(!casilla.getPropietario().getNombre().equals("Banca")) {
            System.out.println("La casilla ya pertenece a un jugador. ");
        }
        
        double precio = casilla.getPrecio() + (casilla.getPrecio() * (tablero.getMinimoVueltas()/4) * 0.05);
    
        if(this.dineroActual <= precio){
            System.out.println("El jugador " + nombre + " no dispone de la suma necesaria para comprar la propiedad");
        }
        else if (casilla.getTipoCasilla().equals("solar") || casilla.getTipoCasilla().equals("transporte") || casilla.getTipoCasilla().equals("servicio")) {

            quitarDinero(precio);
            casilla.setPropietario(this);                                  //Si se dispone de lo necesario para comprar, se quita dinero, se añade la propiedad a propiedades, y se le quita a la banca. También se coloca al jugador como dueño de la casilla
            Collection<Jugador> jugs = tablero.getJugadores().values();
            Jugador bank = null;
            for (Jugador jug1 : jugs) {
                if(jug1.getNombre().equals("Banca")) {
                    bank = jug1;
                }
            }
            
            bank.propiedades.remove(casilla.getNombre());
            propiedades.put(casilla.getNombre(), casilla);
        }
        else {
            System.out.println("Esta casilla no se puede comprar.");
        }
    }
    
    
    public void bancarrota(Jugador jug) {
        tablero.getJugadores().remove(nombre);
        Collection<Jugador> jugs = tablero.getJugadores().values();         //Función que elimina al jugador de la partida, por pérdida total de dinero
        
        for(Jugador jug1 : jugs) {                                          //El jugador que se le pasa por parámetro es el que se quedará con sus casillas, puede ser la banca
            if (jug1.getOrden() > orden) {
                jug1.setOrden(jug1.getOrden() - 1);                         //Actualizamos el orden de la partida
            }
        }
        
        tablero.getAvatares().remove(avatar.getId());
        avatar.getPosicion().getJugadores().remove(avatar.getId());         
        
        Collection<Casilla> props = propiedades.values();
        
        for(Casilla cas : props) {                                           //Eliminamos las propiedades de este jugador, y se las añadimos al pasado por parámetro
            jug.anadirPropiedad(cas);
            propiedades.remove(cas.getNombre());
        }
    }
    
    public void bancarrota() { //Igual que la anterior, pero le da las propiedades a la banca
        tablero.getJugadores().remove(nombre);
        Collection<Jugador> jugs = tablero.getJugadores().values();         //Función que elimina al jugador de la partida, por pérdida total de dinero
        
        for(Jugador jug1 : jugs) {                                          //El jugador que se le pasa por parámetro es el que se quedará con sus casillas, puede ser la banca
            if (jug1.getOrden() > orden) {
                jug1.setOrden(jug1.getOrden() - 1);                         //Actualizamos el orden de la partida
            }
        }
        
        tablero.getAvatares().remove(avatar.getId());
        avatar.getPosicion().getJugadores().remove(avatar.getId());         
        
        Jugador Banca = tablero.getJugadores().get("Banca");
        
        Collection<Casilla> props = propiedades.values();
        
        for(Casilla cas : props) {                                           //Eliminamos las propiedades de este jugador, y se las añadimos al pasado por parámetro
            Banca.anadirPropiedad(cas);
            propiedades.remove(cas.getNombre());
        }
    }
    
    public void imprimirJugador() {                                          //Necesario hacer otra función de imprimir, a parte del toString, ya que se usa en ocasiones distintas 
        String str1 = new String();
        str1 = str1 + "\n{\n\tnombre: " + nombre + "\n\tavatar: " + getAvatar().getId() + "\n\tfortuna: " + dineroActual + "\n\tpropiedades: [" ;
        Iterator<Casilla> props = propiedades.values().iterator();

        while(props.hasNext()) {
            str1 = str1 + props.next().getNombre();
            if(props.hasNext()) {
                str1 = str1 + ", ";
            }
        }
        str1 = str1 + "]\n\tdinero gastado: " + dineroGastado + "\n}";
        
        System.out.println(str1);
    }

    public void entrarEnCarcel(){                          //Función que actualiza la información de un jugador para meterlo en la cárcel
        avatar.setPosicion(tablero.getCasillas().get(1).get(0));
        estaEnCarcel = true;
        puedeTirar = false;
        tablero.imprimirTablero();
        System.out.println(nombre + " ha sido enviado a la cárcel. ");
    }
    
    public void pagarAJugador(Jugador jug, double cantidad) {               //Función en la que un jugador le paga a otro 
        if(dineroActual >= cantidad){
            System.out.println(nombre + " paga a " + jug.getNombre() + " el alquiler de la propiedad: " + cantidad);   
            quitarDinero(cantidad);
            jug.anadirDinero(cantidad);
        } else {
            System.out.println(nombre + " no puede pagar el alquiler, por lo que entra en bancarrota. ");
            bancarrota(jug);
        }
    }
}