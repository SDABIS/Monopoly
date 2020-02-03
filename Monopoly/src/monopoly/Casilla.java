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
public class Casilla {
    
    private double valorInicial;
    private Jugador propietario;
    private String nombre;
    private HashMap <String, Avatar> jugadores;
    private Tablero tablero;
    private String tipoCasilla;
    private Grupo grupo;
    
    public Casilla(Tablero tab, String nom, Grupo grup) {          //Dos constructores, este para los solares
        tablero = tab;
        nombre = nom;
        tipoCasilla = "solar";
        jugadores = new HashMap<>();
        propietario = tab.getJugadores().get("Banca");
        grupo = grup;
        valorInicial = grupo.getValor();
        grupo.anadirCasilla(nombre, this);
        propietario.anadirPropiedad(this);
    }
    
    public Casilla(Tablero tab, String nom, String tipo) {        //Este para el resto de casillas, con información básica para especiales (salida, caja, carcel etc), y más imformación para las comprables e impuestos
        tablero = tab;
        nombre = nom;
        tipoCasilla = tipo;
        jugadores = new HashMap<>();
        
        if(tipo.equals("transporte")) {
            propietario = tab.getJugadores().get("Banca");
            valorInicial = Constantes.PRECIO_TRANSPORTES;
            propietario.anadirPropiedad(this);
        }
        if(tipo.equals("servicio")) {
            propietario = tab.getJugadores().get("Banca");
            valorInicial = Constantes.PRECIO_SERVICIOS;
            propietario.anadirPropiedad(this);
        }
        if(tipo.equals("impuesto1")) {
            valorInicial = Constantes.PRECIO_IMPUESTO1;
        }
        if(tipo.equals("impuesto2")) {
            valorInicial = Constantes.PRECIO_IMPUESTO2;
        }
        
        
        
    }
    
    
    //GETTERS
    
    
    public double getPrecio(){
        return valorInicial;             
    }
    
    public Jugador getPropietario(){
        return propietario;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public HashMap<String, Avatar> getJugadores(){
        return jugadores;
    }
    
    public String getTipoCasilla(){
        return tipoCasilla;
    }
    
    public Grupo getGrupo() {
        return grupo;
    }
    
    public void setPropietario(Jugador jugador) {
        propietario = jugador;
    }
    
    //Los demás atributos son constantes, así que no necesitan setters
    
    
    @Override
    public String toString(){
        String str1 = "\n{\n\tnombre: " + nombre + "\n\ttipo: " + tipoCasilla + "\n\tjugadores: [";
        Iterator<Avatar> avs = jugadores.values().iterator();
        
        while(avs.hasNext()) {
            Avatar av = avs.next();
            str1 = str1 + av.getJugador().getNombre();
            //Si el jugador está en la carcel, imprimir cuantos turnos les quedan
            if (tipoCasilla.equals("carcel")) {
                str1 = str1 + " (" + av.getJugador().getTurnosCarcel() + ")";
            }
            if(avs.hasNext()) {
                str1 = str1 + ", ";
            }
            
        }
        
        if (jugadores.isEmpty()) {
            str1 = str1 + "---]";
        } else {
            str1 = str1 + "]";
        }
        if(tipoCasilla.equals("solar")) {
            double precio = valorInicial + (valorInicial * (tablero.getMinimoVueltas()/4) * 0.05);
            str1 = str1 + "\n\tgrupo: " + grupo.getNombreColor() 
                        + "\n\tpropietario: " + propietario.getNombre() 
                        + "\n\tvalor: " + precio  
                        + "\n\talquiler: " + valorInicial * 0.1;
        }
        
        if(tipoCasilla.equals("transporte") || tipoCasilla.equals("servicio")) {
            str1 = str1 + "\n\tpropietario: " + propietario.getNombre() 
                        + "\n\tvalor: " + valorInicial;
        }
        
        str1 = str1 + "\n}\n";
        return str1;
    }
}