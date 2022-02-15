package com.ed.proyectolibreta;

import javax.swing.JFrame;

public class Principal {
    public static void main(String[] args) {
        MarcoPrincipal libreta = new MarcoPrincipal();
        libreta.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        libreta.setVisible(true);
    }
}
