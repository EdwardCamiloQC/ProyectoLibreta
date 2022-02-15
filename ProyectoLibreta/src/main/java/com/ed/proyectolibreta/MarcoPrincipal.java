package com.ed.proyectolibreta;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class MarcoPrincipal extends JFrame {

    Toolkit toolkit;
    Dimension tamañoPantalla;
    int alto;
    int ancho;
    static String nombre;
    PanelEntrada panelEntrada;
    PanelEntrada panelData;
    JButton botonNuevoApunte;
    JButton botonAbrirApunte;
    JButton botonSalir;
    //JList <MarcoTrabajo.paginas> materia;

    public MarcoPrincipal() {
        init();
    }

    private void init() {
        this.setUndecorated(true);
        this.setTitle("Libreta");
        this.setResizable(false);
        this.getContentPane().setLayout(null);
        toolkit = Toolkit.getDefaultToolkit();

        tamañoPantalla = toolkit.getScreenSize();                               //Se obtiene la resolución de la pantalla
        alto = (int) tamañoPantalla.getHeight();
        ancho = (int) tamañoPantalla.getWidth();
        nombre = " ";
        
        this.setBounds(0, 0, ancho, alto);
        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        
        panelEntrada = new PanelEntrada(0, 0, ancho/8, alto, Color.LIGHT_GRAY);
        panelData = new PanelEntrada(ancho/8, 0, ancho*7/8, alto, Color.GRAY);
        
        panelEntrada.setLayout(new GridLayout(3, 1));
        panelData.setLayout(new FlowLayout());
        
        botonNuevoApunte = new JButton("Trazar");
        botonAbrirApunte = new JButton("Abrir");
        botonSalir = new JButton("Salir");
        
        botonNuevoApunte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                nombre = JOptionPane.showInputDialog("Ingresa el título");
                btnNuevoApunteActionPerformed(evento);
            }
        });
        
        botonAbrirApunte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                
            }
        });
        
        botonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                System.exit(0);
            }
        });
            
        this.add(panelEntrada);
        this.add(panelData);
        panelEntrada.add(botonNuevoApunte);
        panelEntrada.add(botonAbrirApunte);
        panelEntrada.add(botonSalir);
    }

    private JFrame getFrame() {
        return this;
    }

    private void btnNuevoApunteActionPerformed(ActionEvent e) {
        MarcoTrabajo marcoTrabajo = new MarcoTrabajo() {
            @Override
            public void dispose(){
                super.dispose();
                getFrame().setVisible(true);
            }
        };
        
        marcoTrabajo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(false);
        marcoTrabajo.setVisible(true);
        
    }
}
