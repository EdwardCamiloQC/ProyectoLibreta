package com.ed.proyectolibreta;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JPanel;

class EspacioDibujo extends JPanel implements MouseListener, MouseMotionListener{
    
    private String nombreApunte;
    private int ancho, alto, coordenadaX1, coordenadaY1, coordenadaX2, coordenadaY2, indice;
    private int diferenciaX, diferenciaY;
    private float pendiente;
    private int aumentoX, aumentoY;
    private char n;
    public final boolean horizontal = false;
    public final boolean vertical = true;
    ArrayList <Trazos> lineas;
    
    public EspacioDibujo(int xInicial, int yInicial, int ancho, int alto, int ind, String nom) {
        init(xInicial, yInicial, ancho, alto, ind, nom);
    }
    
    private void init(int xInicial, int yInicial, int ancho, int alto, int ind, String nom){
        this.nombreApunte = nom;
        this.setBounds(xInicial, yInicial, ancho, alto);
        this.setVisible(true);
        this.setBackground(Color.WHITE);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.ancho = ancho;
        this.alto = alto;
        this.indice = ind;
        lineas = new ArrayList<>();
        coordenadaX1 = 0;
        coordenadaY1 = 0;
        coordenadaX2 = 0;
        coordenadaY2 = 0;
        n = 1;
        addMouseMotionListener(this);
        addMouseListener(this);
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        for(int i = 0; i <= ancho; i = i + 15){
            g.drawLine(i, 0, i, alto);
        }
        for(int i = 0; i <= alto; i = i + 15){
            g.drawLine(0, i, ancho, i);
        }
        
        for(int i = 0; i < lineas.size(); i++){
            g.setColor(lineas.get(i).getColorTrazo());
            g.drawLine(lineas.get(i).getX1(), lineas.get(i).getY1(), lineas.get(i).getX2(), lineas.get(i).getY2());
        }
        
        g.setColor(Color.BLACK);
        for(int i = 0; i <= ancho; i = i + 15){
            g.drawLine(i, 0, i, alto);
        }
        for(int i = 0; i <= alto; i = i + 15){
            g.drawLine(0, i, ancho, i);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        coordenadaX2 = e.getX();
        coordenadaY2 = e.getY();
        
        diferenciaX = Math.abs(coordenadaX1 - coordenadaX2);
        diferenciaY = Math.abs(coordenadaY1 - coordenadaY2);
        try{
            diferenciaX /= maximoComunDivisor(diferenciaY, diferenciaX);
            diferenciaY /= maximoComunDivisor(diferenciaY, diferenciaX);
        }catch(Exception ex){
            diferenciaX = 0;
            diferenciaY = 1;
        }
        
        try{
            pendiente = coordenadaY2 - coordenadaY1 / coordenadaX2 - coordenadaX1;
        }catch(Exception ex){}
        
        if(MarcoTrabajo.flagEscribir){
            lineas.add(new Trazos(coordenadaX1, coordenadaY1,coordenadaX2, coordenadaY2, MarcoTrabajo.colorApunte));
            if(pendiente >= 0){
                while(n <= MarcoTrabajo.tamañoTrazo){
                    for(int i = 0; i <= diferenciaY; i++){
                        coordenadaX1 -= i;
                        coordenadaX2 -= i;
                        for(int j = 0; j <= diferenciaX; j++){
                            coordenadaY1 += j;
                            coordenadaY2 += j;
                            if(n <= MarcoTrabajo.tamañoTrazo){
                                lineas.add(new Trazos(coordenadaX1, coordenadaY1, coordenadaX2, coordenadaY2, MarcoTrabajo.colorApunte));
                                n++;
                            }else{
                                j = diferenciaX + 1;
                                i = diferenciaY + 1;
                            }
                        }
                    }
                }
                n = 1;
            }else if(pendiente < 0){
                while(n <= MarcoTrabajo.tamañoTrazo){
                    for(int i = 0; i <= diferenciaY; i++){
                        coordenadaX1 += i;
                        coordenadaX2 += i;
                        for(int j = 0; j <= diferenciaX; j++){
                            coordenadaY1 += j;
                            coordenadaY2 += j;
                            if(n <= MarcoTrabajo.tamañoTrazo){
                                lineas.add(new Trazos(coordenadaX1, coordenadaY1,coordenadaX2, coordenadaY2, MarcoTrabajo.colorApunte));
                                n++;
                            }else{
                                j = diferenciaX + 1;
                                i = diferenciaY + 1;
                            }
                        }
                    }
                }
                n = 1;
            }
        }else{
            if(pendiente >= 0){
                while(n <= MarcoTrabajo.tamañoTrazo){
                    for(int i = 0; i <= diferenciaY; i++){
                        coordenadaX1 -= i;
                        coordenadaX2 -= i;
                        for(int j = 0; j <= diferenciaX; j++){
                            coordenadaY1 += j;
                            coordenadaY2 += j;
                            if(n <= MarcoTrabajo.tamañoTrazo){
                                lineas.add(new Trazos(coordenadaX1, coordenadaY1,coordenadaX2, coordenadaY2, Color.WHITE));
                                n++;
                            }else{
                                j = diferenciaX + 1;
                                i = diferenciaY + 1;
                            }
                        }
                    }
                }
                n = 1;
            }else if(pendiente < 0){
                while(n <= MarcoTrabajo.tamañoTrazo){
                    for(int i = 0; i <= diferenciaY; i++){
                        coordenadaX1 += i;
                        coordenadaX2 += i;
                        for(int j = 0; j <= diferenciaX; j++){
                            coordenadaY1 += j;
                            coordenadaY2 += j;
                            if(n <= MarcoTrabajo.tamañoTrazo){
                                lineas.add(new Trazos(coordenadaX1, coordenadaY1,coordenadaX2, coordenadaY2, Color.WHITE));
                                n++;
                            }else{
                                j = diferenciaX + 1;
                                i = diferenciaY + 1;
                            }
                        }
                    }
                }
                n = 1;
            }
        }
        coordenadaX1 = e.getX();
        coordenadaY1 = e.getY();
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        coordenadaX1 = e.getX();
        coordenadaY1 = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    
    public void setNombre(String nom){
        nombreApunte = nom;
    }
    
    public String getNombre(){
        return nombreApunte;
    }
    
    public int getIndice(){
        return indice;
    }
    
    private int maximoComunDivisor(int numerador, int denominador){
        int residuo;
        if(0 != numerador % 2){
            numerador += 1;
        }
        if(0 != denominador % 2){
            denominador += 1;
        }
        do{
            try{
                residuo = numerador % denominador;
            }catch(Exception ex){
                residuo = 0;            //esta línea no cumple una función más que dar un valor igual a cero a residuo
            }
            if(residuo != 0){
                numerador = denominador;
                denominador = residuo;
            }
        }while(residuo != 0);
        return denominador;
    }
}