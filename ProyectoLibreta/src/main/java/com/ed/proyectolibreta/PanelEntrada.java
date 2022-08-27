package com.ed.proyectolibreta;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

public class PanelEntrada extends JPanel{
    
    private boolean flagFondo;
    private Image fondo;
    
    public PanelEntrada(int xInicial, int yInicial, int ancho, int alto, Color colores){
        init(xInicial, yInicial, ancho, alto, colores);
    }
    
    private void init(int xInicial, int yInicial, int ancho, int alto, Color colores){
        this.setBounds(xInicial, yInicial, ancho, alto);
        this.setBackground(colores);
        this.setVisible(true);
        flagFondo = false;
        fondo = null;
    }
    
    @Override
    public void paintComponent(Graphics g){
        if(flagFondo){
            try{
                g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
            }catch(Exception ex){
                System.out.println("no muestra");
            }
            setOpaque(false);
        }
        super.paintComponent(g);
    }
    
    public void setImagenFondo(Image img){
        if(img != null){
            fondo = img;
            flagFondo = true;
            repaint();
        }
    }
}
