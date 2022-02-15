package com.ed.proyectolibreta;

import java.awt.Color;

public class Trazos{
    
    private int x1, x2, y1, y2;
    Color color;
    
    Trazos(int x1, int y1 ,int x2, int y2, Color c){
        init(x1, y1, x2, y2, c);
    }
    
    private void init(int x1, int y1, int x2, int y2, Color c){
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        color = c;
    }
    
    public int getX1(){
        return x1;
    }
    
    public int getY1(){
        return y1;
    }
    
    public int getX2(){
        return x2;
    }
    
    public int getY2(){
        return y2;
    }
    
    public Color getColorTrazo(){
        return color;
    }
}
