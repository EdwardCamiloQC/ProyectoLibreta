package com.ed.proyectolibreta;

import static com.ed.proyectolibreta.MarcoTrabajo.indicePagina;
import static com.ed.proyectolibreta.MarcoTrabajo.paginas;
import com.itextpdf.awt.PdfGraphics2D;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class InformeDeApunte extends JFrame {

    private static String resumen;
    PanelControl laminaEdicionIntro;
    JButton botonListo;
    JTextArea textoIntro;
    PdfTemplate bocetoActual;
    Graphics2D graficoBoceto;
    String rutaGuardar;

    Document archivoApunte;
    Paragraph tituloApunte;
    
    Paragraph presentacion;
    Font fuenteLetraTitulo;
    Font fuenteLetraIntro;
    
    short alto, ancho;

    public InformeDeApunte(short ancho, short alto) {
        init(ancho, alto);
    }

    private void init(short ancho, short alto) {
        this.setResizable(false);
        this.setTitle("Intro");
        this.setBounds(0, 0, 500, 500);
        
        this.ancho = ancho;
        this.alto = alto;

        laminaEdicionIntro = new PanelControl(0, 0, 500, 500, Color.LIGHT_GRAY);
        this.add(laminaEdicionIntro);
        laminaEdicionIntro.setLayout(new BorderLayout());
        textoIntro = new JTextArea();
        textoIntro.setLineWrap(true);
        textoIntro.setBackground(new Color(217,209,186));
        botonListo = new JButton("Listo");
        botonListo.setBackground(MarcoPrincipal.colorBotones);
        botonListo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                btnListoActionPerformed(evento);
            }
        });
        laminaEdicionIntro.add(botonListo, BorderLayout.NORTH);
        laminaEdicionIntro.add(textoIntro, BorderLayout.CENTER);
        
        archivoApunte = new Document(new Rectangle(ancho-ancho/8,alto));
        this.rutaGuardar = "C:\\Users\\edwar\\Documents\\prueba.pdf";
        fuenteLetraTitulo = new Font();
        fuenteLetraIntro = new Font();
    }

    private void btnListoActionPerformed(ActionEvent e) {
        try {
            rutaGuardar = "C:\\Users\\edwar\\Documents\\" + MarcoPrincipal.nombre + ".pdf";
            PdfWriter writer = PdfWriter.getInstance(archivoApunte, new FileOutputStream(rutaGuardar));
            archivoApunte.open();
            fuenteLetraTitulo.setSize(60);
            tituloApunte = new Paragraph(MarcoPrincipal.nombre, fuenteLetraTitulo);
            fuenteLetraIntro.setSize(20);
            presentacion = new Paragraph(textoIntro.getText(), fuenteLetraIntro);
            tituloApunte.setAlignment(1);
            presentacion.setAlignment(0);
            archivoApunte.add(tituloApunte);
            archivoApunte.add(presentacion);

            PdfContentByte boceto;
            for (char indice = 0; indice < indicePagina; indice++) {
                archivoApunte.newPage();
                boceto = writer.getDirectContent();
                bocetoActual = boceto.createTemplate(ancho - ancho / 8, alto);
                graficoBoceto = new PdfGraphics2D(bocetoActual, ancho - ancho / 8, alto);
                paginas.get(indice).paint(graficoBoceto);
                graficoBoceto.dispose();
                boceto.addTemplate(bocetoActual, 0, 0);
            }
            archivoApunte.close();
        }catch (Exception ex){
            System.err.println(ex);
        }
        this.dispose();
    }

}
