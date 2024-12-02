package Controlador;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.*;
import Modelo.ModelLogin;
import Vista.FrmLogin;
import Vista.FrmLoginPass;
import Vista.FrmPrincipal;
import Vista.FrmPrincipal_std;
import utilidad.Recursos;

public class CtrLogin {    
    public static String CodUsuario, NomUsuario, ApeUsuario, Usuario;
    public static boolean admin;
    FrmLogin vista;
    ModelLogin modelo;
    Recursos r;
    
    public CtrLogin(FrmLogin vista) {         
        this.vista = vista;
        modelo = new ModelLogin();
        r = new Recursos();
    }
    
    public void entrar() {        
        String usuario = vista.txtUsuario.getText();
        String contraseña = String.valueOf(vista.txtContraseña.getText());   
        String tipoUsuario = modelo.validarLogin(usuario, contraseña);
        
        if (null == tipoUsuario) {
            vista.lb1Mensaje.setText("Credenciales incorrectas");
            limpiar();
        } else {            
            CodUsuario = modelo.getCodUsuario();
            NomUsuario = modelo.getNomUsuario();
            ApeUsuario = modelo.getApeUsuario();
            Usuario    = modelo.getUsuario();
            switch (tipoUsuario) {
                case "admin" -> {          
                        vista.dispose();
                        admin = true;
                        FrmPrincipal principal = new FrmPrincipal();
                        principal.MenuPrincipal();
                        principal.setVisible(true);
                    }
                case "docente" -> {          
                        vista.dispose();
                        admin = false;
                        FrmPrincipal principal = new FrmPrincipal();
                        principal.MenuPrincipal();
                        principal.setVisible(true);
                    }
                case "alumno" -> {
                        vista.dispose();
                        FrmPrincipal_std std = new FrmPrincipal_std();
                        std.MenuPrincipal();
                        std.setVisible(true);
                    }
                case "alumno-noPass" -> {
                        vista.dispose();
                        FrmLoginPass pass = new FrmLoginPass();
                        pass.setVisible(true);
                    }
            }
        }
    }
    
    public void marcar(JTextField txt, String seleccionado) {
        txt.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txt.getText().equals(seleccionado)) {
                    txt.setText("");
                    txt.setForeground(r.negro);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (txt.getText().isEmpty()) {
                    txt.setText(seleccionado);
                    txt.setForeground(r.negro);
                }
            }
        });
    }    
    
    public void limpiar(){    
        vista.lb1Mensaje.setForeground(r.rojo);
        vista.txtUsuario.setText("@unfv.edu.pe");
        vista.txtUsuario.setForeground(r.gris);
        vista.txtContraseña.setText("********");
        vista.txtContraseña.setForeground(r.gris);
    }    
    
    boolean esVisible = false;
    public void mostrarOcultar(){        
        if (esVisible) {
            vista.txtContraseña.setEchoChar('*');
            vista.btnMostrar.setText("Mostrar");
        } else {
            vista.txtContraseña.setEchoChar((char) 0);
            vista.btnMostrar.setText("Ocultar");
        }
        esVisible = !esVisible;
        vista.txtContraseña.setFont(r.arialS);
    }
    
    public void salir() {
        System.exit(0);
    }
}