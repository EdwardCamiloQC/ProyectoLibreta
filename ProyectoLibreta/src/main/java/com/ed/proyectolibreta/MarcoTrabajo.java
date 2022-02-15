package com.ed.proyectolibreta;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

public class MarcoTrabajo extends JFrame{
    Toolkit toolkit;
    Dimension tamañoPantalla;
    short alto, ancho;
    String valorGrosorTrazo;
    static boolean flagEscribir;
    static short tamañoTrazo;
    static Color colorApunte;
    static short indicePagina;
    static ArrayList <EspacioDibujo> paginas;
    PanelControl panelRegresar;
    PanelControl panelOpciones;
    PanelControl panelColor;
    PanelControl panelGuardado;
    JTabbedPane hojas;
    
    JButton botonInicio;
    JLabel titulo;
    JLabel numeroGrosor;
    JLabel numeroPagina;
    
    JButton botonHojaAnterior;
    JButton botonHojaSiguiente;
    JButton botonEliminarHoja;
    JButton botonCrearHoja;
    JButton botonColorNegro;
    JButton botonColorRojo;
    JButton botonEscribir;
    JButton botonBorrar;
    JButton botonAumentarTrazo;
    JButton botonDisminuirTrazo;
    
    JButton botonRenombrarApunte;
    JButton botonGuardarApunte;
    
    public MarcoTrabajo(){
        init();
    }
    
    private void init(){
        this.setUndecorated(true);
        this.setResizable(false);
        this.setTitle("Libreta");
        this.getContentPane().setLayout(null);
        toolkit = Toolkit.getDefaultToolkit();
        
        tamañoPantalla = toolkit.getScreenSize();                               //Se obtiene la resolución de la pantalla
        alto = (short) tamañoPantalla.getHeight();
        ancho = (short) tamañoPantalla.getWidth();
        
        this.setBounds(0, 0, ancho, alto);
        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        
        flagEscribir = true;
        tamañoTrazo = 2;
        valorGrosorTrazo = String.valueOf(tamañoTrazo);
        colorApunte = Color.BLACK;
        indicePagina = 1;
        
        panelRegresar = new PanelControl(0, 0, ancho / 8, alto * 3/ 16, Color.GRAY);
        panelOpciones = new PanelControl(0, alto / 4, ancho / 8, alto / 4, Color.GRAY);
        panelColor = new PanelControl(0, alto * 9 / 16, ancho / 8, alto / 4, Color.GRAY);
        panelGuardado = new PanelControl(0, alto * 7 / 8, ancho / 8, alto / 8, Color.GRAY);
        hojas = new JTabbedPane(1);
        hojas.setBounds(ancho / 8, 0, ancho - ancho / 8, alto);
        paginas = new ArrayList <>();
        paginas.add(new EspacioDibujo(ancho / 8, 0, ancho - ancho / 8, alto, indicePagina, MarcoPrincipal.nombre));
        hojas.add(paginas.get(indicePagina - 1).getNombre() + "[" + String.valueOf(indicePagina) + "]", paginas.get(0));
        
        panelRegresar.setLayout(new GridLayout(4, 1));
        panelOpciones.setLayout(new GridLayout(5, 2));
        panelColor.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelGuardado.setLayout(new GridLayout(2, 1));
        
        botonInicio = new JButton("Inicio");
        titulo = new JLabel("Materia: " + MarcoPrincipal.nombre);
        numeroGrosor = new JLabel("Grosor: " + valorGrosorTrazo);
        numeroPagina = new JLabel("Página: " + String.valueOf(indicePagina));
        botonHojaAnterior = new JButton("◄");
        botonHojaSiguiente = new JButton("►");
        botonEliminarHoja = new JButton("Eliminar");
        botonCrearHoja = new JButton("Nueva");
        botonAumentarTrazo = new JButton("▬");
        botonDisminuirTrazo = new JButton("–");
        botonColorNegro = new JButton("Negro");
        botonColorNegro.setBackground(Color.BLACK);
        botonColorRojo = new JButton("Rojo");
        botonColorRojo.setBackground(Color.RED);
        botonEscribir = new JButton("Escribir");
        botonBorrar = new JButton("Borrar");
        botonRenombrarApunte = new JButton("Renombrar");
        botonGuardarApunte = new JButton("Guargar");
        
        botonInicio.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evento){
                btnMarcoTrabajoPerformed(evento);
            }
        });
        
        botonHojaAnterior.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evento){
                
            }
        });
        
        botonHojaSiguiente.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evento){
                
            }
        });
        
        botonEliminarHoja.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evento){
                if(indicePagina > 1){
                    hojas.remove(indicePagina - 1);
                    paginas.remove(indicePagina - 1);
                    indicePagina -= 1;
                }
            }
        });
        
        botonCrearHoja.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evento){
                indicePagina += 1;
                paginas.add(new EspacioDibujo(ancho / 8, 0, ancho - ancho / 8, alto, indicePagina, MarcoPrincipal.nombre));
                hojas.add(paginas.get(indicePagina - 1).getNombre() + "[" + String.valueOf(indicePagina) + "]", paginas.get(indicePagina - 1));
            }
        });
        
        botonAumentarTrazo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evento){
                if(tamañoTrazo < 20){
                    tamañoTrazo += 1;
                    valorGrosorTrazo = "Grosor: " + String.valueOf(tamañoTrazo);
                    numeroGrosor.setText(valorGrosorTrazo);
                }
            }
        });
        
        botonDisminuirTrazo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evento){
                if(tamañoTrazo > 1){
                    tamañoTrazo -= 1;
                    valorGrosorTrazo = "Grosor: " + String.valueOf(tamañoTrazo);
                    numeroGrosor.setText(valorGrosorTrazo);
                }
            }
        });
        
        botonColorNegro.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evento){
                colorApunte = Color.BLACK;
            }
        });
        
        botonColorRojo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evento){
                colorApunte = Color.RED;
            }
        });
        
        botonEscribir.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evento){
                flagEscribir = true;
            }
        });
        
        botonBorrar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evento){
                flagEscribir = false;
            }
        });
        
        botonRenombrarApunte.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evento){
                MarcoPrincipal.nombre = JOptionPane.showInputDialog("Renombra el Apunte");
                titulo.setText("Materia: " + MarcoPrincipal.nombre);
                for(short i = 0; i < paginas.size(); i++){
                    paginas.get(i).setNombre(MarcoPrincipal.nombre);
                    hojas.setTitleAt(i, MarcoPrincipal.nombre + "[" + String.valueOf(paginas.get(i).getIndice()) + "]");
                }
            }
        });
        
        botonGuardarApunte.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evento){
                
            }
        });
        
        this.add(panelRegresar);
        this.add(panelOpciones);
        this.add(panelColor);
        this.add(panelGuardado);
        this.add(hojas);
        
        panelRegresar.add(botonInicio);
        panelRegresar.add(titulo);
        panelRegresar.add(numeroGrosor);
        panelRegresar.add(numeroPagina);
        panelOpciones.add(botonHojaAnterior);
        panelOpciones.add(botonHojaSiguiente);
        panelOpciones.add(botonEliminarHoja);
        panelOpciones.add(botonCrearHoja);
        panelOpciones.add(botonAumentarTrazo);
        panelOpciones.add(botonDisminuirTrazo);
        panelOpciones.add(botonColorNegro);
        panelOpciones.add(botonColorRojo);
        panelOpciones.add(botonEscribir);
        panelOpciones.add(botonBorrar);
        panelGuardado.add(botonRenombrarApunte);
        panelGuardado.add(botonGuardarApunte);
    }
    
    public void btnMarcoTrabajoPerformed(ActionEvent e){
        this.dispose();
    }
    
}
