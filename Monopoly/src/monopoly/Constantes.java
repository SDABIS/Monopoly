/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

/**
 *
 * @author miguel
 */


public class Constantes {
    
    
    //Colores de los grupos
    public static final String COLOR_MORADO = "\u001B[35m";        //Colores para imprimir
    public static final String COLOR_CYAN = "\u001B[36m";
    public static final String COLOR_VERDE= "\u001B[32m";
    public static final String COLOR_AZUL = "\u001B[34m";
    public static final String COLOR_ROJO = "\u001B[31m";
    public static final String COLOR_BLANCO = "\u001B[37m";
    public static final String COLOR_AMARILLO = "\u001B[33m";
    public static final String COLOR_NEGRO = "\u001B[30m";
    public static final String COLOR_RESET = "\u001B[0m";
    
    //Precios y cantidades

    
    public static final double PRECIO_GRUPO1 = 600000.0;                     //Precios de las casillas y grupos
    public static final double PRECIO_CASILLA_GRUPO1 = PRECIO_GRUPO1/2;
    public static final double PRECIO_GRUPO2 = PRECIO_GRUPO1*1.3;
    public static final double PRECIO_CASILLA_GRUPO2 = PRECIO_GRUPO2/3;
    public static final double PRECIO_GRUPO3 = PRECIO_GRUPO2*1.3;
    public static final double PRECIO_CASILLA_GRUPO3 = PRECIO_GRUPO3/3;
    public static final double PRECIO_GRUPO4 = PRECIO_GRUPO3*1.3;
    public static final double PRECIO_CASILLA_GRUPO4 = PRECIO_GRUPO4/3;
    public static final double PRECIO_GRUPO5 = PRECIO_GRUPO4*1.3;
    public static final double PRECIO_CASILLA_GRUPO5 = PRECIO_GRUPO5/3;
    public static final double PRECIO_GRUPO6 = PRECIO_GRUPO5*1.3;
    public static final double PRECIO_CASILLA_GRUPO6 = PRECIO_GRUPO6/3;
    public static final double PRECIO_GRUPO7 = PRECIO_GRUPO6*1.3;
    public static final double PRECIO_CASILLA_GRUPO7 = PRECIO_GRUPO7/3;
    public static final double PRECIO_GRUPO8 = PRECIO_GRUPO7*1.3;
    public static final double PRECIO_CASILLA_GRUPO8 = PRECIO_GRUPO8/2;
    
    public static final double PRECIO_TOTAL_SOLARES = PRECIO_GRUPO1 + PRECIO_GRUPO2 + PRECIO_GRUPO3 + PRECIO_GRUPO4 + PRECIO_GRUPO5 + PRECIO_GRUPO6 + PRECIO_GRUPO7 + PRECIO_GRUPO8;
    
    public static final double CANTIDAD_INICIAL = (PRECIO_TOTAL_SOLARES)/3 ;
    
    public static final double CANTIDAD_SALIDA = (PRECIO_TOTAL_SOLARES)/22;          //Otros valores y cantidades
    public static final double PRECIO_TRANSPORTES = CANTIDAD_SALIDA;
    public static final double PRECIO_SERVICIOS = CANTIDAD_SALIDA * (3.0/4.0);
    public static final double FACTOR_SERVICIO = CANTIDAD_SALIDA/200;
    public static final double PRECIO_SALIR_CARCEL = CANTIDAD_SALIDA/4;
    public static final double PRECIO_IMPUESTO1 = CANTIDAD_SALIDA;
    public static final double PRECIO_IMPUESTO2 = CANTIDAD_SALIDA/2;
    
}