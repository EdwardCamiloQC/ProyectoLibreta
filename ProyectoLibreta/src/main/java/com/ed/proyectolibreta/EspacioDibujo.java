package com.ed.proyectolibreta;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JPanel;

class EspacioDibujo extends JPanel implements MouseListener, MouseMotionListener{
    
    private String nombreApunte;
    private int ancho, alto, coordenadaX1, coordenadaY1, coordenadaX2, coordenadaY2, indice;
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
        addMouseMotionListener(this);
        addMouseListener(this);
    }
    
    @Override
    public void paintComponent(Graphics g){
        Graphics2D graficos = (Graphics2D) g;
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        for(int i = 0; i <= ancho; i = i + 15){
            g.drawLine(i, 0, i, alto);
        }
        for(int i = 0; i <= alto; i = i + 15){
            g.drawLine(0, i, ancho, i);
        }
        
        for(int i = 0; i < lineas.size(); i++){
            graficos.setColor(lineas.get(i).getColorTrazo());
            graficos.setStroke(new BasicStroke(lineas.get(i).getGrosor()));
            graficos.drawLine(lineas.get(i).getX1(), lineas.get(i).getY1(), lineas.get(i).getX2(), lineas.get(i).getY2());
        }
        
        graficos.setStroke(new BasicStroke(1));
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
        
        if(MarcoTrabajo.flagEscribir){
            lineas.add(new Trazos(coordenadaX1, coordenadaY1,coordenadaX2, coordenadaY2, MarcoTrabajo.colorApunte, MarcoTrabajo.tamañoTrazo));
        }else{
            lineas.add(new Trazos(coordenadaX1, coordenadaY1,coordenadaX2, coordenadaY2, Color.WHITE, MarcoTrabajo.tamañoTrazo));
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
      
}