/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import java.util.Random;

/**
 *
 * @author miguel
 */
public class Dado{
    
    private int valor1;
    private int valor2;
    
    public Dado() {
        valor1 = 0;
        valor2 = 0;
    }
    
    public void tirarDados(){   //Funcion que tira dados, a cada dado se le asigna un random del 1-6
        Random rn = new Random();
        int n = rn.nextInt(6) + 1;
        valor1 = n;
        n = rn.nextInt(6) + 1;
        valor2 = n;
    }
    
    public int getValor1(){
        return valor1;
    }
    
    public int getValor2(){
        return valor2;
    }
    
    //No hacen falta setters, pues el único momento en el que Valor1 y Valor2 cambiar es con tirarDados().
    
    public boolean sonIguales(){
        return valor1 == valor2;
    }
    
}