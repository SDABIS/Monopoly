/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import java.util.Collection;
import java.util.Random;

/**
 *
 * @author miguel
 */
public class Avatar {
    
    private Jugador jugador;
    private String tipo;
    private Casilla posicion;
    private String id;                  //Ponemos String en vez de char para poder hacer HashMap de avatares
    private Tablero tablero;
    
    
    public Avatar(Jugador jug, String ficha, Tablero tab){
        int repetido = 0;
        jugador = jug;
        
        Random rn = new Random();
        //Asignar un valor para id, y comprobar que no esta repetido
        //El valor de id sera una letra mayuscula del abecedario (ascii 65-90)
        do {
            char idd;
            idd = (char)(rn.nextInt(26) + 65);
            id = "" + idd;
            Collection<Avatar> avatares = tab.getAvatares().values();   
            repetido = 0;
            
            for (Avatar avat : avatares) {
                if (avat.id.equals(this.id)) {                           
                    repetido = 1;
                }
            }
        } while(repetido == 1);
        posicion = tab.getCasillas().get(0).get(0);
        tablero = tab;
        tipo = ficha;
        
        tablero.anadirAvatar(this);
        posicion.getJugadores().put(id, this);
    }
    
    public Avatar(){
        jugador = null;
        tipo = "coche";
        posicion = null;
        id = "-";
        tablero = null;
    }
    
    public Jugador getJugador() {
        return jugador;
    }
    
    public String getTipo() {
        return tipo;
    }
    
    public Casilla getPosicion() {
        return posicion;
    }
    
    public String getId() {
        return id;
    }
    
    public void setPosicion(Casilla cas){
        posicion = cas;
    }
    
    //Los demás atributos son constantes, así que no necesitan setters
    
    @Override 
    public String toString(){
        return "\n{\n\tid: " + getId() + "\n\ttipo: " + getTipo() + "\n\tcasilla: " + getPosicion().getNombre() + "\n\tjugador: " + getJugador().getNombre() + "\n}";
    }
    
}