/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import java.util.HashMap;

/**
 *
 * @author miguel
 */
public class Grupo {
    private HashMap<String, Casilla> casillas;
    private String color;
    private double valorInicial;
    private Tablero tablero;
    private String nombreColor;
    
    public Grupo(String col, Tablero tab) {           //Clase únicamente si la casilla se trata de un solar, la cual cambia el color y el valor inicial de cada casilla en su constructor
        casillas = new HashMap<>();
        tablero = tab;
        nombreColor = col;
        switch(col) {
            case "negro":
                color = Constantes.COLOR_NEGRO;
                valorInicial = Constantes.PRECIO_CASILLA_GRUPO1;
                break;
            case "rojo":
                color = Constantes.COLOR_ROJO;
                valorInicial = Constantes.PRECIO_CASILLA_GRUPO2;
                break;
            case "verde":
                color = Constantes.COLOR_VERDE;
                valorInicial = Constantes.PRECIO_CASILLA_GRUPO3;
                break;
            case "amarillo":
                color = Constantes.COLOR_AMARILLO;
                valorInicial = Constantes.PRECIO_CASILLA_GRUPO4;
                break;
            case "azul":
                color = Constantes.COLOR_AZUL;
                valorInicial = Constantes.PRECIO_CASILLA_GRUPO5;
                break;
            case "morado":
                color = Constantes.COLOR_MORADO;
                valorInicial = Constantes.PRECIO_CASILLA_GRUPO6;
                break;
            case "cyan":
                color = Constantes.COLOR_CYAN;
                valorInicial = Constantes.PRECIO_CASILLA_GRUPO7;
                break;
            case "blanco":
                color = Constantes.COLOR_BLANCO;
                valorInicial = Constantes.PRECIO_CASILLA_GRUPO8;
                break;
        }
    }
    
    public Grupo() {
        casillas = new HashMap<>();
        tablero = null;
        nombreColor = "blanco";
        color = Constantes.COLOR_BLANCO;
        valorInicial = Double.POSITIVE_INFINITY;
    }
    
    public HashMap<String, Casilla> getCasillasGrupo() {          
        return casillas;
    }
    
    public String getColor() {           //Diferenciar entre get color (la constante que se usa en el tostring para imprimir de otro solor) y el nombre del color en sí
        return color;
    }
    
    public String getNombreColor() {
        return nombreColor;
    }
    
    public double getValor() {
        return valorInicial;
    }
    
    public void anadirCasilla(String nombre, Casilla cas) {      //Funcion que añade casillas a los grupos (setCasilla, pero con el nombre cambiado para más claridad)
        casillas.put(nombre, cas);
    }
    
    //Los atributos son constantes, así que no necesitan setters
}