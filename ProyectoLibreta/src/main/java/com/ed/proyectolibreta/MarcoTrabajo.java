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
    InformeDeApunte hacerIntro;
    private static char tipoDeTrazo;
    Toolkit toolkit;
    Dimension tamañoPantalla;
    short alto, ancho;
    String valorGrosorTrazo, modoEscribirBorrar;
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
    JButton botonColor;
    JButton botonEscribirBorrar;
    JButton botonAumentarTrazo;
    JButton botonDisminuirTrazo;
    JButton botonTrazarLinea;
    JButton botonTrazarCuadro;
    JButton botonTrazarCirculo;
    
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
        this.tipoDeTrazo = 'T';                                                 // T:trazo libre, L:trazo linea
        
        panelRegresar = new PanelControl(0, 0, ancho / 8, alto * 3/ 16, MarcoPrincipal.colorFondos);
        panelOpciones = new PanelControl(0, alto / 4, ancho / 8, alto / 4, MarcoPrincipal.colorFondos);
        panelColor = new PanelControl(0, alto * 9 / 16, ancho / 8, alto / 4, MarcoPrincipal.colorFondos);
        panelGuardado = new PanelControl(0, alto * 7 / 8, ancho / 8, alto / 8, MarcoPrincipal.colorFondos);
        hojas = new JTabbedPane(1);
        hojas.setBounds(ancho / 8, 0, ancho - ancho / 8, alto);
        paginas = new ArrayList <>();
        paginas.add(new EspacioDibujo(ancho / 8, 0, ancho - ancho / 8, alto, indicePagina, MarcoPrincipal.nombre));
        hojas.add(paginas.get(indicePagina - 1).getNombre() + "[" + String.valueOf(indicePagina) + "]", paginas.get(0));
        
        panelRegresar.setLayout(new GridLayout(4, 1));
        panelOpciones.setLayout(new GridLayout(6, 2));
        panelColor.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelGuardado.setLayout(new GridLayout(2, 1));
        
        botonInicio = new JButton("Inicio");
        botonInicio.setBackground(MarcoPrincipal.colorBotones);
        botonInicio.setFont(MarcoPrincipal.tamanoLetra);
        titulo = new JLabel("Materia: " + MarcoPrincipal.nombre);
        titulo.setFont(MarcoPrincipal.tamanoLetra);
        numeroGrosor = new JLabel("Grosor: " + valorGrosorTrazo);
        numeroGrosor.setFont(MarcoPrincipal.tamanoLetra);
        numeroPagina = new JLabel("Página: " + String.valueOf(indicePagina));
        numeroPagina.setFont(MarcoPrincipal.tamanoLetra);
        botonHojaAnterior = new JButton("◄");
        botonHojaAnterior.setBackground(MarcoPrincipal.colorBotones);
        botonHojaAnterior.setFont(MarcoPrincipal.tamanoLetra);
        botonHojaSiguiente = new JButton("►");
        botonHojaSiguiente.setBackground(MarcoPrincipal.colorBotones);
        botonHojaSiguiente.setFont(MarcoPrincipal.tamanoLetra);
        botonEliminarHoja = new JButton("Eliminar");
        botonEliminarHoja.setBackground(MarcoPrincipal.colorBotones);
        botonEliminarHoja.setFont(MarcoPrincipal.tamanoLetra);
        botonCrearHoja = new JButton("Nueva");
        botonCrearHoja.setBackground(MarcoPrincipal.colorBotones);
        botonCrearHoja.setFont(MarcoPrincipal.tamanoLetra);
        botonAumentarTrazo = new JButton("▬");
        botonAumentarTrazo.setBackground(MarcoPrincipal.colorBotones);
        botonAumentarTrazo.setFont(MarcoPrincipal.tamanoLetra);
        botonDisminuirTrazo = new JButton("–");
        botonDisminuirTrazo.setBackground(MarcoPrincipal.colorBotones);
        botonDisminuirTrazo.setFont(MarcoPrincipal.tamanoLetra);
        botonColor = new JButton("Color");
        botonColor.setBackground(colorApunte);
        botonColor.setFont(MarcoPrincipal.tamanoLetra);
        botonTrazarLinea = new JButton("/");
        botonTrazarLinea.setBackground(MarcoPrincipal.colorBotones);
        botonTrazarLinea.setFont(MarcoPrincipal.tamanoLetra);
        botonTrazarCuadro = new JButton("□");
        botonTrazarCuadro.setBackground(MarcoPrincipal.colorBotones);
        botonTrazarCuadro.setFont(MarcoPrincipal.tamanoLetra);
        botonTrazarCirculo = new JButton("○");
        botonTrazarCirculo.setBackground(MarcoPrincipal.colorBotones);
        botonTrazarCirculo.setFont(MarcoPrincipal.tamanoLetra);
        
        if(flagEscribir){
            modoEscribirBorrar = "Borrar";
        }else modoEscribirBorrar = "Escribir";
        botonEscribirBorrar = new JButton(modoEscribirBorrar);
        botonEscribirBorrar.setBackground(MarcoPrincipal.colorBotones);
        botonEscribirBorrar.setFont(MarcoPrincipal.tamanoLetra);
        botonRenombrarApunte = new JButton("Renombrar");
        botonRenombrarApunte.setBackground(MarcoPrincipal.colorBotones);
        botonRenombrarApunte.setFont(MarcoPrincipal.tamanoLetra);
        botonGuardarApunte = new JButton("Guargar");
        botonGuardarApunte.setBackground(MarcoPrincipal.colorBotones);
        botonGuardarApunte.setFont(MarcoPrincipal.tamanoLetra);
        
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
        
        botonColor.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evento){
                if(colorApunte == Color.BLACK){
                    colorApunte = Color.RED;
                    botonColor.setBackground(colorApunte);
                }else if(colorApunte == Color.RED || colorApunte!= Color.BLACK){
                    colorApunte = Color.BLACK;
                    botonColor.setBackground(colorApunte);
                }
            }
        });
        
        botonEscribirBorrar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evento){
                tipoDeTrazo = 'T';
                flagEscribir = !flagEscribir;
                if(flagEscribir){
                    modoEscribirBorrar = "Borrar";
                }else modoEscribirBorrar = "Escribir";
                botonEscribirBorrar.setText(modoEscribirBorrar);
            }
        });
        
        botonTrazarLinea.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evento){
                tipoDeTrazo = 'L';
                modoEscribirBorrar = "Borrar";
                botonEscribirBorrar.setText(modoEscribirBorrar);
            }
        });
        
        botonTrazarCuadro.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evento){
                tipoDeTrazo = 'C';
                modoEscribirBorrar = "Borrar";
                botonEscribirBorrar.setText(modoEscribirBorrar);
            }
        });
        
        botonTrazarCirculo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evento){
                
            }
        });
        
        botonRenombrarApunte.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evento){
                MarcoPrincipal.nombre = JOptionPane.showInputDialog("Renombra el Apunte").toUpperCase();
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
                hacerIntro = new InformeDeApunte(ancho,alto);
                hacerIntro.setVisible(true);
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
        panelOpciones.add(botonColor);
        panelOpciones.add(botonEscribirBorrar);
        panelOpciones.add(botonTrazarLinea);
        panelOpciones.add(botonTrazarCuadro);
        panelOpciones.add(botonTrazarCirculo);
        panelGuardado.add(botonRenombrarApunte);
        panelGuardado.add(botonGuardarApunte);
    }
    
    public void btnMarcoTrabajoPerformed(ActionEvent e){
        this.dispose();
    }
    
    public static char getTipoDeTrazo(){
        return tipoDeTrazo;
    }
    
}
