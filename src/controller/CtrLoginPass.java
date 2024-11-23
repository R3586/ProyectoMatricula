package controller;

import java.awt.event.*;
import javax.swing.*;
import view.FrmLoginPass;
import model.ModelLoginPass;
import utilily.Recursos;
import view.FrmLogin;
import view.FrmPrincipal_std;

public class CtrLoginPass {
    FrmLoginPass vista;
    ModelLoginPass modelo;
    Recursos r;
    
    public CtrLoginPass(FrmLoginPass vista) {         
        this.vista = vista;
        modelo = new ModelLoginPass(); 
        r = new Recursos();
    }
        
    public void continuar() {        
        String usuario = vista.txtUsuario.getText();
        String contraseña = String.valueOf(vista.txtContraseña.getText());   
        String confirmar = String.valueOf(vista.txtConfirmar.getText());
        
        if (contraseña == null ? confirmar != null : !contraseña.equals(confirmar)){
            JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else {
            boolean alumno = modelo.agregarContraseña(usuario, confirmar);
            if (alumno == false) {
                JOptionPane.showMessageDialog(null, "La contraseña debe tener 8 caracteres mínimo.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                vista.dispose();
                FrmPrincipal_std std = new FrmPrincipal_std();
                std.setVisible(true);
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
        vista.txtContraseña.setText("*******");
        vista.txtContraseña.setForeground(r.gris);
    }    
    
    boolean esVisible = false;
    public void mostrarOcultar(){        
        if (esVisible) {
            vista.txtContraseña.setEchoChar('*');
            vista.txtConfirmar.setEchoChar('*');
            vista.btnMostrar.setText("Mostrar");
        } else {
            vista.txtContraseña.setEchoChar((char) 0);
            vista.txtConfirmar.setEchoChar((char) 0);
            vista.btnMostrar.setText("Ocultar");
        }
        esVisible = !esVisible;
    }
    
    public void volver(){
        vista.dispose();
        FrmLogin login = new FrmLogin();
        login.setVisible(true);
    }    
}
