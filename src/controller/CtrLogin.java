package controller;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.*;
import model.ModelLogin;
import view.FrmLogin;
import view.FrmPrincipal;
import view.FrmPrincipal_std;

public class CtrLogin {
    public FrmLogin vista;
    public ModelLogin modelo;
    
    public CtrLogin(FrmLogin vista) {         
        this.vista = vista;
        modelo = new ModelLogin();  
    }
        
    public void Entrar() {        
        String usuario = vista.txtUsuario.getText();
        String contraseña = String.valueOf(vista.txtContraseña.getText());   
        String tipoUsuario = modelo.validarLogin(usuario, contraseña);

        if (null == tipoUsuario) {
            vista.lb1Mensaje.setText("Credenciales incorrectas");
            limpiar();
        } else switch (tipoUsuario) {
            case "alumno" -> {
                    vista.dispose();
                    FrmPrincipal_std principal_std = new FrmPrincipal_std();
                    principal_std.setVisible(true);
                }
            case "docente" -> {                    
                    vista.dispose();
                    FrmPrincipal principal = new FrmPrincipal();
                    principal.setVisible(true);
                }
        }
    }
    public void marcador(JTextField textField, String marcador) {
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(marcador)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(marcador);
                    textField.setForeground(Color.BLACK);
                }
            }
        });
    }    
    public void limpiar(){    
        vista.lb1Mensaje.setForeground(java.awt.Color.RED);
        vista.txtUsuario.setText("@unfv.edu.pe");
        vista.txtUsuario.setForeground(Color.GRAY);
        vista.txtContraseña.setText("********");
        vista.txtContraseña.setForeground(Color.GRAY);
    }    
    public void Salir() {
        System.exit(0);
    }
}