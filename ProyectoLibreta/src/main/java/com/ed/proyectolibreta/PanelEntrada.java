package com.ed.proyectolibreta;

import java.awt.Color;
import javax.swing.JPanel;

public class PanelEntrada extends JPanel{
    
    public PanelEntrada(int xInicial, int yInicial, int ancho, int alto, Color colores){
        init(xInicial, yInicial, ancho, alto, colores);
    }
    
    private void init(int xInicial, int yInicial, int ancho, int alto, Color colores){
        this.setBounds(xInicial, yInicial, ancho, alto);
        this.setBackground(colores);
        this.setVisible(true);
    }
}
